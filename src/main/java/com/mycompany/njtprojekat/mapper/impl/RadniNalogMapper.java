/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.mapper.impl;

import com.mycompany.njtprojekat.dto.impl.RadniNalogDto;
import com.mycompany.njtprojekat.dto.impl.StavkaRadnogNalogaDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.entity.impl.RadniNalog;
import com.mycompany.njtprojekat.entity.impl.StavkaRadnogNaloga;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;
import java.util.List;
import java.util.stream.Collectors;

public class RadniNalogMapper implements DtoEntityMapper<RadniNalogDto, RadniNalog>{
    private final StavkaRadnogNalogaMapper stavkaMapper = new StavkaRadnogNalogaMapper();

    @Override
    public RadniNalogDto toDto(RadniNalog e) {
        if (e == null) return null;

        List<StavkaRadnogNalogaDto> stavkeDto = e.getStavke().stream()
                .map(stavka -> {
                    StavkaRadnogNalogaDto dto = stavkaMapper.toDto(stavka);
                    dto.setRadniNalog(null); // da izbegnemo beskonačnu rekurziju
                    return dto;
                })
                .collect(Collectors.toList());

        return new RadniNalogDto(
                e.getIdRadniNalog(),
                e.getDatumOtvaranja(),
                e.getDatumZatvaranja(),
                e.getOpisKvara(),
                e.getStatus(),
                e.getMehanicar() != null ? e.getMehanicar().getIdMehanicar() : null,
                stavkeDto
        );
    }

    @Override
    public RadniNalog toEntity(RadniNalogDto t) {
        if (t == null) return null;

        Mehanicar mehanicar = t.getIdMehanicar() != null ? new Mehanicar(t.getIdMehanicar()) : null;

        List<StavkaRadnogNaloga> stavke = t.getStavke().stream()
                .map(stavkaDto -> {
                    StavkaRadnogNaloga stavka = stavkaMapper.toEntity(stavkaDto);
                    stavka.setRadniNalog(new RadniNalog(t.getIdRadniNalog(), null, null, null, null, mehanicar, null));
                    return stavka;
                })
                .collect(Collectors.toList());

        return new RadniNalog(
                t.getIdRadniNalog(),
                t.getDatumOtvaranja(),
                t.getDatumZatvaranja(),
                t.getOpisKvara(),
                t.getStatus(),
                mehanicar,
                stavke
        );
    }
    

}
