package com.mycompany.njtprojekat.servis;

import com.mycompany.njtprojekat.dto.impl.RacunDto;
import com.mycompany.njtprojekat.dto.impl.StavkaRacunaDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.entity.impl.StavkaRacuna;
import com.mycompany.njtprojekat.entity.impl.Usluga;
import com.mycompany.njtprojekat.entity.impl.Vozilo;
import com.mycompany.njtprojekat.mapper.impl.RacunMapper;
import com.mycompany.njtprojekat.repository.impl.RacunRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacunServis {

    private final RacunRepository orders;
    private final RacunMapper mapper;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public RacunServis(RacunRepository orders, RacunMapper mapper) {
        this.orders = orders;
        this.mapper = mapper;
    }

    public List<RacunDto> findAll() {
        return orders.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public RacunDto findById(Integer idRacun) throws Exception {
        return mapper.toDto(orders.findById(idRacun));
    }

    @Transactional
    public RacunDto create(RacunDto dto) throws Exception {
        Racun order = new Racun();
        order.setDatum(dto.getDatum());
        order.setUkupanIznos(dto.getUkupanIznos());
        if (dto.getIdMehanicar() == null) {
            throw new Exception("mehanicar je obavezan");
        }
        if (dto.getIdVozilo() == null) {
            throw new Exception("vozilo je obavezno");
        }
        order.setMehanicar(em.getReference(Mehanicar.class, dto.getIdMehanicar()));
        order.setVozilo(em.getReference(Vozilo.class, dto.getIdVozilo()));

        if (dto.getStavke() == null || dto.getStavke().isEmpty()) {
            throw new Exception("racun mora imati bar jednu stavku!");
        }
        for (StavkaRacunaDto it : dto.getStavke()) {
            StavkaRacuna oi = new StavkaRacuna();
            Usluga p = em.getReference(Usluga.class, it.getIdUsluga());
            oi.setUsluga(p);
            oi.setCena(it.getCena());
            oi.setIznos(it.getIznos());
            oi.setKolicina(it.getKolicina());
            order.addItem(oi);
        }
        orders.save(order);
        return mapper.toDto(order);

    }

    
    @Transactional
    public void deleteById(Integer idRacun) {
        orders.deleteById(idRacun);
    }

    @Transactional
    public RacunDto update(Integer id, RacunDto dto) throws Exception {
        Racun existing = orders.findById(id);
        if (existing == null) {
            throw new Exception("Racun ne postoji!");
        }

        
        existing.setDatum(dto.getDatum());
        existing.setUkupanIznos(dto.getUkupanIznos());
        existing.setVozilo(em.getReference(Vozilo.class, dto.getIdVozilo()));

        
        List<StavkaRacuna> postojece = existing.getStavke();
        java.util.Map<Integer, StavkaRacuna> mapaPostojecih = postojece.stream()
                .collect(Collectors.toMap(StavkaRacuna::getRb, s -> s));

        
        List<StavkaRacuna> noveStavke = dto.getStavke().stream().map(stavkaDto -> {
            StavkaRacuna s;
            if (stavkaDto.getRb() != null && mapaPostojecih.containsKey(stavkaDto.getRb())) {
               
                s = mapaPostojecih.get(stavkaDto.getRb());
            } else {
                
                s = new StavkaRacuna();
                s.setRacun(existing);
            }
            s.setCena(stavkaDto.getCena());
            s.setIznos(stavkaDto.getIznos());
            s.setKolicina(stavkaDto.getKolicina());
            s.setUsluga(em.getReference(Usluga.class, stavkaDto.getIdUsluga()));
            return s;
        }).collect(Collectors.toList());

       
        List<Integer> noviRb = dto.getStavke().stream()
                .map(StavkaRacunaDto::getRb)
                .filter(java.util.Objects::nonNull)
                .toList();

        postojece.removeIf(stara -> !noviRb.contains(stara.getRb()));

        existing.getStavke().clear();
        existing.getStavke().addAll(noveStavke);

        orders.save(existing);

        return mapper.toDto(existing);
    }

}
