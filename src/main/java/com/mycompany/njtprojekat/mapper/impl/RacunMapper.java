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
import java.util.List;
import java.util.stream.Collectors;

public class RacunMapper implements DtoEntityMapper<RacunDto, Racun> {

    private final StavkaRacunaMapper stavkaRacunaMapper = new StavkaRacunaMapper();

    @Override
    public RacunDto toDto(Racun racun) {
        if (racun == null) {
            return null;
        }

        List<StavkaRacunaDto> stavkeDTO = null;
        if (racun.getStavke() != null) {
            stavkeDTO = racun.getStavke().stream()
                    .map(stavkaRacunaMapper::toDto)
                    .collect(Collectors.toList());
        }

        return new RacunDto(
                racun.getIdRacun(),
                racun.getDatum(),
                racun.getUkupanIznos(),
                racun.getMehanicar() != null ? racun.getMehanicar().getIdMehanicar() : null,
                racun.getVozilo() != null ? racun.getVozilo().getIdVozilo() : null,
                stavkeDTO
        );
    }

    @Override
    public Racun toEntity(RacunDto dto) {
        if (dto == null) {
            return null;
        }

        Mehanicar mehanicar = dto.getIdMehanicar() != null ? new Mehanicar(dto.getIdMehanicar()) : null;
        Vozilo vozilo = dto.getIdVozilo() != null ? new Vozilo(dto.getIdVozilo()) : null;

        Racun racun = new Racun();
        racun.setIdRacun(dto.getIdRacun());
        racun.setDatum(dto.getDatum());
        racun.setUkupanIznos(dto.getUkupanIznos());
        racun.setMehanicar(mehanicar);
        racun.setVozilo(vozilo);

        if (dto.getStavke() != null) {
            List<StavkaRacuna> stavke = dto.getStavke().stream()
                    .map(stavkaRacunaMapper::toEntity)
                    .peek(s -> s.setRacun(racun))
                    .collect(Collectors.toList());
            racun.setStavke(stavke);
        }
    
        return racun;
    }
}

