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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RacunMapper implements DtoEntityMapper<RacunDto, Racun> {

    @Override
    public RacunDto toDto(Racun racun) {
        if (racun == null) {
            return null;
        }
        
        // NE konvertuj stavke ovde - to će raditi servis sloj ako treba
        return new RacunDto(
                racun.getIdRacun(),
                racun.getDatum(),
                racun.getUkupanIznos(),
                racun.getMehanicar() != null ? racun.getMehanicar().getIdMehanicar() : null,
                racun.getVozilo() != null ? racun.getVozilo().getIdVozilo() : null,
                null  // stavke ostavljamo null ovde
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
        
        return racun;
    }
}

