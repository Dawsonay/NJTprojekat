/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.mapper.impl;

import com.mycompany.njtprojekat.dto.impl.VrstaVozilaDto;
import com.mycompany.njtprojekat.entity.impl.VrstaVozila;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class VrstaVozilaMapper implements DtoEntityMapper<VrstaVozilaDto, VrstaVozila>{

    @Override
    public VrstaVozilaDto toDto(VrstaVozila e) {
        if (e == null) {
            return null;
        }

        return new VrstaVozilaDto(
                e.getIdVrsta(),
                e.getNaziv(),
                e.getMaxNosivost(),
                e.getNamena()
        );
    }

    @Override
    public VrstaVozila toEntity(VrstaVozilaDto t) {
        if (t == null) {
            return null;
        }

        VrstaVozila vrsta = new VrstaVozila();
        vrsta.setIdVrsta(t.getIdVrsta());
        vrsta.setNaziv(t.getNaziv());
        vrsta.setMaxNosivost(t.getMaxNosivost());
        vrsta.setNamena(t.getNamena());

        return vrsta;
    }

}
