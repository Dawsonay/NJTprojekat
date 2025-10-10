/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.mapper.impl;

import com.mycompany.njtprojekat.dto.Dto;
import com.mycompany.njtprojekat.dto.impl.StavkaRadnogNalogaDto;
import com.mycompany.njtprojekat.entity.impl.RadniNalog;
import com.mycompany.njtprojekat.entity.impl.StavkaRadnogNaloga;
import com.mycompany.njtprojekat.entity.impl.Usluga;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class StavkaRadnogNalogaMapper implements DtoEntityMapper<StavkaRadnogNalogaDto, StavkaRadnogNaloga> {

    @Override
    public StavkaRadnogNalogaDto toDto(StavkaRadnogNaloga e) {
        return new StavkaRadnogNalogaDto(
                e.getRb(),
                e.getKolicina(),
                e.getCenaPoJedinici(),
                e.getUkupnaCena(),
                e.getOpisZadatka(),
                e.getUsluga().getIdUsluga()
        );
    }

    @Override
    public StavkaRadnogNaloga toEntity(StavkaRadnogNalogaDto t) {
        StavkaRadnogNaloga oi = new StavkaRadnogNaloga();
        oi.setRb(t.getRb());
        oi.setKolicina(t.getKolicina());
        oi.setCenaPoJedinici(t.getCenaPoJedinici());
        oi.setUkupnaCena(t.getUkupnaCena());
        oi.setOpisZadatka(t.getOpisZadatka());

        oi.setUsluga(new Usluga(t.getIdUsluga()));
        return oi;
    }

}
