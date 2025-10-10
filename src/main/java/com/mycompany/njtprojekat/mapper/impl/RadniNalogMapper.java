/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.mapper.impl;

import com.mycompany.njtprojekat.dto.impl.RacunDto;
import com.mycompany.njtprojekat.dto.impl.RadniNalogDto;
import com.mycompany.njtprojekat.dto.impl.StavkaRadnogNalogaDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.entity.impl.RadniNalog;
import com.mycompany.njtprojekat.entity.impl.StavkaRadnogNaloga;
import com.mycompany.njtprojekat.entity.impl.Vozilo;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RadniNalogMapper implements DtoEntityMapper<RadniNalogDto, RadniNalog> {

    private final StavkaRadnogNalogaMapper itemMapper;

    public RadniNalogMapper(StavkaRadnogNalogaMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public RadniNalogDto toDto(RadniNalog e) {
        List stavke = e.getStavke()
                .stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
        return new RadniNalogDto(
                e.getIdRadniNalog(),
                e.getDatumOtvaranja(),
                e.getDatumZatvaranja(),
                e.getOpisKvara(),
                e.getStatus(),
                e.getMehanicar().getIdMehanicar(),
                e.getVozilo().getIdVozilo(),
                stavke
        );
    }

    @Override
    public RadniNalog toEntity(RadniNalogDto t) {
        RadniNalog o = new RadniNalog();
        o.setIdRadniNalog(t.getIdRadniNalog());
        o.setDatumOtvaranja(t.getDatumOtvaranja());
        o.setDatumZatvaranja(t.getDatumZatvaranja());

        o.setOpisKvara(t.getOpisKvara());
        o.setStatus(t.getStatus());

        if (t.getIdMehanicar() != null) {
            o.setMehanicar(new Mehanicar(t.getIdMehanicar()));
        }
        if (t.getIdVozilo()!= null) {
            o.setVozilo(new Vozilo(t.getIdVozilo()));
        }
        if (t.getStavke() != null) {
            t.getStavke().forEach(d -> o.addItem(itemMapper.toEntity(d)));
        }
        return o;
    }

}
