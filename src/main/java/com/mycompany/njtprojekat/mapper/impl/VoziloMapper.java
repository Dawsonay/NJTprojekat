/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.mapper.impl;

import com.mycompany.njtprojekat.dto.impl.VoziloDto;
import com.mycompany.njtprojekat.dto.impl.VrstaVozilaDto;
import com.mycompany.njtprojekat.entity.impl.Vozilo;
import com.mycompany.njtprojekat.entity.impl.VrstaVozila;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class VoziloMapper implements DtoEntityMapper<VoziloDto, Vozilo>{

     @Override
    public VoziloDto toDto(Vozilo e) {
        if (e == null) {
            return null;
        }

        VrstaVozilaDto vrstaDto = null;
        if (e.getVrsta() != null) {
            vrstaDto = new VrstaVozilaDto(
                    e.getVrsta().getIdVrsta(),
                    e.getVrsta().getNaziv(),
                    e.getVrsta().getMaxNosivost(),
                    e.getVrsta().getNamena()
            );
        }

        return new VoziloDto(
                e.getIdVozilo(),
                e.getBrojSasije(),
                e.getModel(),
                e.getRegistarskiBroj(),
                e.getTipGoriva(),
                e.getGodiste(),
                vrstaDto.getIdVrsta()
        );
    }

    @Override
    public Vozilo toEntity(VoziloDto t) {
        if (t == null) {
            return null;
        }

        VrstaVozila vrsta = t.getIdVrsta() != null
                ? new VrstaVozila(t.getIdVrsta())
                : null;

        Vozilo vozilo = new Vozilo();
        vozilo.setIdVozilo(t.getIdVozilo());
        vozilo.setBrojSasije(t.getBrojSasije());
        vozilo.setModel(t.getModel());
        vozilo.setRegistarskiBroj(t.getRegistarskiBroj());
        vozilo.setTipGoriva(t.getTipGoriva());
        vozilo.setGodiste(t.getGodiste());
        vozilo.setVrsta(vrsta);

        return vozilo;
    }

}
