package com.mycompany.njtprojekat.mapper.impl;

import com.mycompany.njtprojekat.dto.impl.StavkaRacunaDto;
import com.mycompany.njtprojekat.entity.impl.StavkaRacuna;
import com.mycompany.njtprojekat.entity.impl.Usluga;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class StavkaRacunaMapper implements DtoEntityMapper<StavkaRacunaDto, StavkaRacuna> {

    @Override
    public StavkaRacunaDto toDto(StavkaRacuna e) {
        return new StavkaRacunaDto(
                e.getRb(),
                e.getCena(),
                e.getIznos(),
                e.getKolicina(),
                e.getUsluga().getIdUsluga()
        );
    }

    @Override
    public StavkaRacuna toEntity(StavkaRacunaDto t) {
        StavkaRacuna oi = new StavkaRacuna();
        oi.setRb(t.getRb());
        oi.setCena(t.getCena());
        oi.setIznos(t.getIznos());
        oi.setKolicina(t.getKolicina());
        oi.setUsluga(new Usluga(t.getIdUsluga()));
        return oi;
    }
}
