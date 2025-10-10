package com.mycompany.njtprojekat.servis;

import com.mycompany.njtprojekat.dto.impl.RacunDto;
import com.mycompany.njtprojekat.dto.impl.StavkaRacunaDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.entity.impl.StavkaRacuna;
import com.mycompany.njtprojekat.entity.impl.Usluga;
import com.mycompany.njtprojekat.entity.impl.Vozilo;
import com.mycompany.njtprojekat.mapper.impl.RacunMapper;
import com.mycompany.njtprojekat.mapper.impl.StavkaRacunaMapper;
import com.mycompany.njtprojekat.repository.impl.RacunRepository;
import com.mycompany.njtprojekat.repository.impl.StavkaRacunaRepository;
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

    // 🔹 Jedan račun po ID-u
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

//    @Transactional
//    public RacunDto update(Integer id, RacunDto dto) throws Exception {
//        Racun existing = orders.findById(id);
//        if (existing == null) {
//            throw new Exception("Račun ne postoji!");
//        }
//
//        existing.setDatum(dto.getDatum());
//        existing.setUkupanIznos(dto.getUkupanIznos());
//        existing.setIdMehanicar(dto.getIdMehanicar());
//        existing.setIdVozilo(dto.getIdVozilo());
//
//        List<StavkaRacuna> stareStavke = stavkaRepo.findByRacun(id);
//
//        // 🔹 nove stavke iz DTO
//        List<StavkaRacuna> noveStavke = dto.getStavke().stream()
//                .map(stavkaMapper::toEntity)
//                .peek(s -> s.setRacun(existing))
//                .collect(Collectors.toList());
//
//        // obriši stavke koje više ne postoje
//        List<Integer> noviRb = noveStavke.stream()
//                .map(StavkaRacuna::getRb)
//                .toList();
//
//        for (StavkaRacuna stara : stareStavke) {
//            if (!noviRb.contains(stara.getRb())) {
//                stavkaRepo.delete(stara);
//            }
//        }
//
//        // upiši nove stavke (save radi i insert i update)
//        for (StavkaRacuna nova : noveStavke) {
//            nova.setRacun(existing);
//            stavkaRepo.save(nova);
//        }
//
//        orders.save(existing);
//
//        RacunDto updated = mapper.toDto(existing);
//        updated.setStavke(dto.getStavke());
//        return updated;
//    }
    // 🔹 Brisanje računa
    @Transactional
    public void deleteById(Integer idRacun) {
        orders.deleteById(idRacun);
    }

    @Transactional
    public RacunDto update(Integer id, RacunDto dto) throws Exception {
        Racun existing = orders.findById(id);
        if (existing == null) {
            throw new Exception("Račun ne postoji!");
        }

        // 🔹 Ažuriraj osnovna polja
        existing.setDatum(dto.getDatum());
        existing.setUkupanIznos(dto.getUkupanIznos());
        existing.setVozilo(em.getReference(Vozilo.class, dto.getIdVozilo()));

        // 🔹 Mapa postojećih stavki po rb
        List<StavkaRacuna> postojece = existing.getStavke();
        java.util.Map<Integer, StavkaRacuna> mapaPostojecih = postojece.stream()
                .collect(Collectors.toMap(StavkaRacuna::getRb, s -> s));

        // 🔹 Nova lista stavki (posle ažuriranja)
        List<StavkaRacuna> noveStavke = dto.getStavke().stream().map(stavkaDto -> {
            StavkaRacuna s;
            if (stavkaDto.getRb() != null && mapaPostojecih.containsKey(stavkaDto.getRb())) {
                // već postoji → ažuriraj postojeću
                s = mapaPostojecih.get(stavkaDto.getRb());
            } else {
                // nova stavka
                s = new StavkaRacuna();
                s.setRacun(existing);
            }
            s.setCena(stavkaDto.getCena());
            s.setIznos(stavkaDto.getIznos());
            s.setKolicina(stavkaDto.getKolicina());
            s.setUsluga(em.getReference(Usluga.class, stavkaDto.getIdUsluga()));
            return s;
        }).collect(Collectors.toList());

        // 🔹 Obriši stavke koje više nisu prisutne u DTO
        List<Integer> noviRb = dto.getStavke().stream()
                .map(StavkaRacunaDto::getRb)
                .filter(java.util.Objects::nonNull)
                .toList();

        postojece.removeIf(stara -> !noviRb.contains(stara.getRb()));

        // 🔹 Dodaj i ažurirane stavke
        existing.getStavke().clear();
        existing.getStavke().addAll(noveStavke);

        orders.save(existing);

        return mapper.toDto(existing);
    }

}
