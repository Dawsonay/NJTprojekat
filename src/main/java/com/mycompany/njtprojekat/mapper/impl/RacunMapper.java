package com.mycompany.njtprojekat.mapper.impl;



import com.mycompany.njtprojekat.dto.impl.RacunDto;
import com.mycompany.njtprojekat.dto.impl.StavkaRacunaDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.entity.impl.Vozilo;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RacunMapper implements DtoEntityMapper<RacunDto, Racun> {

    private final StavkaRacunaMapper itemMapper;

    public RacunMapper(StavkaRacunaMapper itemMapper) {
        this.itemMapper = itemMapper;
    }
    
    
    @Override
    public RacunDto toDto(Racun e) {
        List stavke = e.getStavke()
                .stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
        return new RacunDto(
                e.getIdRacun(),
                e.getDatum(),
                e.getUkupanIznos(),
                e.getMehanicar().getIdMehanicar(),
                e.getVozilo().getIdVozilo(),
                stavke
        );
    }

    @Override
    public Racun toEntity(RacunDto t) {
        Racun o = new Racun();
        o.setIdRacun(t.getIdRacun());
        o.setDatum(t.getDatum());
        o.setUkupanIznos(t.getUkupanIznos());
        if(t.getIdMehanicar()!=null){
            o.setMehanicar(new Mehanicar(t.getIdMehanicar()));
        }
        if(t.getIdVozilo()!=null){
            o.setVozilo(new Vozilo(t.getIdVozilo()));
        }
        if(t.getStavke()!=null){
            t.getStavke().forEach(d -> o.addItem(itemMapper.toEntity(d)));
        }
        return o;
    }
}