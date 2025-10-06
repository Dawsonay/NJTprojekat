/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.mapper.impl;

import com.mycompany.njtprojekat.dto.impl.RacunDto;
import com.mycompany.njtprojekat.dto.impl.StavkaRacunaDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.entity.impl.StavkaRacuna;
import com.mycompany.njtprojekat.entity.impl.Vozilo;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RacunMapper implements DtoEntityMapper<RacunDto, Racun> {

    private final StavkaRacunaMapper stavkaMapper = new StavkaRacunaMapper();

    @Override
    public RacunDto toDto(Racun racun) {
        if (racun == null) {
            return null;
        }

        RacunDto dto = new RacunDto();
        dto.setIdRacun(racun.getIdRacun());
        dto.setDatum(racun.getDatum());
        dto.setUkupanIznos(racun.getUkupanIznos());

        if (racun.getMehanicar() != null) {
            dto.setIdMehanicar(racun.getMehanicar().getIdMehanicar());
        }

        if (racun.getVozilo() != null) {
            dto.setIdVozilo(racun.getVozilo().getIdVozilo());
        }

        if (racun.getStavke() != null) {
            List<StavkaRacunaDto> stavkeDto = racun.getStavke()
                    .stream()
                    .map(stavkaMapper::toDto)
                    .collect(Collectors.toList());
            dto.setStavke(stavkeDto);
        }

        return dto;
    }

    @Override
    public Racun toEntity(RacunDto dto) {
        if (dto == null) {
            return null;
        }

        Racun racun = new Racun();
        racun.setIdRacun(dto.getIdRacun());
        racun.setDatum(dto.getDatum());
        racun.setUkupanIznos(dto.getUkupanIznos());

        
        Mehanicar mehanicar = new Mehanicar();
        mehanicar.setIdMehanicar(dto.getIdMehanicar());
        racun.setMehanicar(mehanicar);

        Vozilo vozilo = new Vozilo();
        vozilo.setIdVozilo(dto.getIdVozilo());
        racun.setVozilo(vozilo);

        if (dto.getStavke() != null) {
            List<StavkaRacuna> stavke = dto.getStavke()
                    .stream()
                    .map(stavkaMapper::toEntity)
                    .collect(Collectors.toList());
            racun.setStavke(stavke);

            // Postavi referencu na račune unutar svake stavke
            for (StavkaRacuna s : stavke) {
                s.setRacun(racun);
            }
        }

        return racun;
    }
}
