/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.mapper.impl;

import com.mycompany.njtprojekat.dto.impl.RacunDto;
import com.mycompany.njtprojekat.dto.impl.StavkaRacunaDto;
import com.mycompany.njtprojekat.dto.impl.UslugaDto;
import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.entity.impl.StavkaRacuna;
import com.mycompany.njtprojekat.entity.impl.Usluga;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;

public class StavkaRacunaMapper implements DtoEntityMapper<StavkaRacunaDto, StavkaRacuna>{

    private final RacunMapper racunMapper = new RacunMapper();
    private final UslugaMapper uslugaMapper = new UslugaMapper();

    @Override
    public StavkaRacunaDto toDto(StavkaRacuna e) {
        if (e == null) {
            return null;
        }

        RacunDto racunDto = e.getRacun() != null ? racunMapper.toDto(e.getRacun()) : null;
        UslugaDto uslugaDto = e.getUsluga() != null ? uslugaMapper.toDto(e.getUsluga()) : null;

        return new StavkaRacunaDto(
                e.getRb(),
                racunDto,
                e.getKolicina(),
                e.getCena(),
                e.getIznos(),
                uslugaDto
        );
    }

    @Override
    public StavkaRacuna toEntity(StavkaRacunaDto t) {
        if (t == null) {
            return null;
        }

        Racun racun = t.getRacun() != null && t.getRacun().getIdRacun() != null
                ? new Racun(t.getRacun().getIdRacun())
                : null;

        Usluga usluga = t.getUsluga() != null && t.getUsluga().getIdUsluga() != null
                ? new Usluga(t.getUsluga().getIdUsluga()) // prazan entitet samo sa ID-em
                : null;

        StavkaRacuna stavka = new StavkaRacuna();
        stavka.setRb(t.getRb());
        stavka.setRacun(racun);
        stavka.setKolicina(t.getKolicina());
        stavka.setCena(t.getCena());
        stavka.setIznos(t.getIznos());
        stavka.setUsluga(usluga);

        return stavka;
    }

}
