/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.mapper.impl;

import com.mycompany.njtprojekat.dto.impl.UslugaDto;
import com.mycompany.njtprojekat.entity.impl.Usluga;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class UslugaMapper implements DtoEntityMapper<UslugaDto, Usluga>{

     @Override
    public UslugaDto toDto(Usluga e) {
        if (e == null) {
            return null;
        }

        return new UslugaDto(
                e.getIdUsluga(),
                e.getNaziv(),
                e.getOpis(),
                e.getCena()
        );
    }

    @Override
    public Usluga toEntity(UslugaDto t) {
        if (t == null) {
            return null;
        }

        Usluga usluga = new Usluga();
        usluga.setIdUsluga(t.getIdUsluga());
        usluga.setNaziv(t.getNaziv());
        usluga.setOpis(t.getOpis());
        usluga.setCena(t.getCena());

        return usluga;
    }

}
