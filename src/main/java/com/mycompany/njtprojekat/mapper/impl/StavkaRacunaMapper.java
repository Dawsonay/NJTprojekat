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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StavkaRacunaMapper implements DtoEntityMapper<StavkaRacunaDto, StavkaRacuna>{

     private final UslugaMapper uslugaMapper;
    
    @Autowired
    public StavkaRacunaMapper(UslugaMapper uslugaMapper) {
        this.uslugaMapper = uslugaMapper;
    }
    
    @Override
    public StavkaRacunaDto toDto(StavkaRacuna e) {
        if (e == null) {
            return null;
        }
        
        // NE konvertuj ceo racun, samo postavi null ili samo ID
        return new StavkaRacunaDto(
                e.getRb(),
                null,  // racun ostavljamo null da izbegnemo circular dependency
                e.getKolicina(),
                e.getCena(),
                e.getIznos(),
                e.getUsluga() != null ? uslugaMapper.toDto(e.getUsluga()) : null
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
                ? new Usluga(t.getUsluga().getIdUsluga())
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
