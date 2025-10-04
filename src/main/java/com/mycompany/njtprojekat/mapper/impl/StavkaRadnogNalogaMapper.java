/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.mapper.impl;

import com.mycompany.njtprojekat.dto.Dto;
import com.mycompany.njtprojekat.dto.impl.StavkaRadnogNalogaDto;
import com.mycompany.njtprojekat.entity.impl.RadniNalog;
import com.mycompany.njtprojekat.entity.impl.StavkaRadnogNaloga;
import com.mycompany.njtprojekat.entity.impl.Usluga;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;

public class StavkaRadnogNalogaMapper implements DtoEntityMapper<StavkaRadnogNalogaDto, StavkaRadnogNaloga>{
    @Override
    public StavkaRadnogNalogaDto toDto(StavkaRadnogNaloga e) {
        if (e == null) return null;

        return new StavkaRadnogNalogaDto(
                e.getRb(),
                null,
                e.getKolicina(),
                e.getCenaPoJedinici(),
                e.getUkupnaCena(),
                e.getOpisZadatka(),
                e.getUsluga() != null ? new com.mycompany.njtprojekat.dto.impl.UslugaDto(
                        e.getUsluga().getIdUsluga(),
                        e.getUsluga().getNaziv(),
                        e.getUsluga().getOpis(),
                        e.getUsluga().getCena()
                ) : null
        );
    }

    @Override
    public StavkaRadnogNaloga toEntity(StavkaRadnogNalogaDto t) {
        if (t == null) return null;

        RadniNalog radniNalog = t.getRadniNalog() != null && t.getRadniNalog().getIdRadniNalog() != null
                ? new RadniNalog(t.getRadniNalog().getIdRadniNalog(), null, null, null, null, null, null)
                : null;

        Usluga usluga = t.getUsluga() != null && t.getUsluga().getIdUsluga() != null
                ? new Usluga(t.getUsluga().getIdUsluga())
                : null;

        return new StavkaRadnogNaloga(
                t.getRb(),
                radniNalog,
                t.getKolicina(),
                t.getCenaPoJedinici(),
                t.getUkupnaCena(),
                t.getOpisZadatka(),
                usluga
        );
    }
}
