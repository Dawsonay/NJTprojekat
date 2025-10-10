/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.servis;

import com.mycompany.njtprojekat.dto.impl.RacunDto;
import com.mycompany.njtprojekat.dto.impl.RadniNalogDto;
import com.mycompany.njtprojekat.dto.impl.StavkaRacunaDto;
import com.mycompany.njtprojekat.dto.impl.StavkaRadnogNalogaDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.entity.impl.RadniNalog;
import com.mycompany.njtprojekat.entity.impl.StavkaRacuna;
import com.mycompany.njtprojekat.entity.impl.StavkaRadnogNaloga;
import com.mycompany.njtprojekat.entity.impl.Usluga;
import com.mycompany.njtprojekat.entity.impl.Vozilo;
import com.mycompany.njtprojekat.mapper.impl.RacunMapper;
import com.mycompany.njtprojekat.mapper.impl.RadniNalogMapper;
import com.mycompany.njtprojekat.repository.impl.RacunRepository;
import com.mycompany.njtprojekat.repository.impl.RadniNalogRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RadniNalogServis {

    private final RadniNalogRepository orders;
    private final RadniNalogMapper mapper;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public RadniNalogServis(RadniNalogRepository orders, RadniNalogMapper mapper) {
        this.orders = orders;
        this.mapper = mapper;
    }

    public List<RadniNalogDto> findAll() {
        return orders.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public RadniNalogDto findById(Integer id) throws Exception {
        return mapper.toDto(orders.findById(id));
    }

    @Transactional
    public RadniNalogDto create(RadniNalogDto dto) throws Exception {
        RadniNalog order = new RadniNalog();
        order.setDatumOtvaranja(dto.getDatumOtvaranja());
        order.setDatumZatvaranja(dto.getDatumZatvaranja());

        order.setOpisKvara(dto.getOpisKvara());
        order.setStatus(dto.getStatus());

        if (dto.getIdMehanicar() == null) {
            throw new Exception("mehanicar je obavezan");
        }
        order.setMehanicar(em.getReference(Mehanicar.class, dto.getIdMehanicar()));

        if (dto.getIdVozilo()== null) {
            throw new Exception("vozilo je obavezno");
        }
        order.setVozilo(em.getReference(Vozilo.class, dto.getIdVozilo()));
        
        if (dto.getStavke() == null || dto.getStavke().isEmpty()) {
            throw new Exception("racun mora imati bar jednu stavku!");
        }
        for (StavkaRadnogNalogaDto it : dto.getStavke()) {
            StavkaRadnogNaloga oi = new StavkaRadnogNaloga();
            Usluga p = em.getReference(Usluga.class, it.getIdUsluga());
            oi.setUsluga(p);
            oi.setKolicina(it.getKolicina());
            oi.setCenaPoJedinici(it.getCenaPoJedinici());
            oi.setUkupnaCena(it.getUkupnaCena());
            oi.setOpisZadatka(it.getOpisZadatka());

            order.addItem(oi);
        }
        orders.save(order);
        return mapper.toDto(order);

    }

    // 🔹 Brisanje računa
    @Transactional
    public void deleteById(Integer id) {
        orders.deleteById(id);
    }

    @Transactional
    public RadniNalogDto update(Integer id, RadniNalogDto dto) throws Exception {
        RadniNalog existing = orders.findById(id);
        if (existing == null) {
            throw new Exception("Radni nalog ne postoji!");
        }

        // 🔹 Ažuriraj osnovna polja
        existing.setDatumZatvaranja(dto.getDatumZatvaranja());
        existing.setStatus(dto.getStatus());
        existing.setOpisKvara(dto.getOpisKvara());
        existing.setVozilo(em.getReference(Vozilo.class, dto.getIdVozilo()));

        // 🔹 Mapa postojećih stavki po rb
        List<StavkaRadnogNaloga> postojece = existing.getStavke();
        java.util.Map<Integer, StavkaRadnogNaloga> mapaPostojecih = postojece.stream()
                .collect(Collectors.toMap(StavkaRadnogNaloga::getRb, s -> s));
        
        // 🔹 Nova lista stavki (posle ažuriranja)
        List<StavkaRadnogNaloga> noveStavke = dto.getStavke().stream().map(stavkaDto -> {
            StavkaRadnogNaloga s;
            if (stavkaDto.getRb() != null && mapaPostojecih.containsKey(stavkaDto.getRb())) {
                // već postoji → ažuriraj postojeću
                s = mapaPostojecih.get(stavkaDto.getRb());
            } else {
                // nova stavka
                s = new StavkaRadnogNaloga();
                s.setRadniNalog(existing);
            }
            s.setKolicina(stavkaDto.getKolicina());
            s.setUkupnaCena(stavkaDto.getUkupnaCena());
            s.setOpisZadatka(stavkaDto.getOpisZadatka());
            s.setCenaPoJedinici(stavkaDto.getCenaPoJedinici());

            s.setUsluga(em.getReference(Usluga.class, stavkaDto.getIdUsluga()));
            return s;
        }).collect(Collectors.toList());

        // 🔹 Obriši stavke koje više nisu prisutne u DTO
        List<Integer> noviRb = dto.getStavke().stream()
                .map(StavkaRadnogNalogaDto::getRb)
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
