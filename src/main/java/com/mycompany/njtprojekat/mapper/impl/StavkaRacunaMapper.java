/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.mapper.impl;

import com.mycompany.njtprojekat.dto.impl.StavkaRacunaDto;
import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.entity.impl.StavkaRacuna;
import com.mycompany.njtprojekat.entity.impl.Usluga;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StavkaRacunaMapper implements DtoEntityMapper<StavkaRacunaDto, StavkaRacuna> {

    @Override
    public StavkaRacunaDto toDto(StavkaRacuna entity) {
        if (entity == null) {
            return null;
        }

        StavkaRacunaDto dto = new StavkaRacunaDto();
        dto.setRb(entity.getRb());
        dto.setKolicina(entity.getKolicina());
        dto.setCena(entity.getCena());
        dto.setIznos(entity.getIznos());
        dto.setIdRacun(entity.getRacun().getIdRacun());

        if (entity.getUsluga() != null) {
            dto.setIdUsluga(entity.getUsluga().getIdUsluga());
        }

        return dto;
    }

    @Override
    public StavkaRacuna toEntity(StavkaRacunaDto dto) {
        if (dto == null) {
            return null;
        }

        StavkaRacuna entity = new StavkaRacuna();
        entity.setRb(dto.getRb());
        entity.setKolicina(dto.getKolicina());
        entity.setCena(dto.getCena());
        entity.setIznos(dto.getIznos());
//        entity.setRacun(dto.getIdRacun());
        if (dto.getIdUsluga() != null) {
            Usluga usluga = new Usluga();
            usluga.setIdUsluga(dto.getIdUsluga());
            entity.setUsluga(usluga);
        }

        return entity;
    }

}
