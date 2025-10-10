package com.mycompany.njtprojekat.servis;

import com.mycompany.njtprojekat.dto.impl.StavkaRacunaDto;
import com.mycompany.njtprojekat.entity.impl.StavkaRacuna;
import com.mycompany.njtprojekat.mapper.impl.StavkaRacunaMapper;
import com.mycompany.njtprojekat.repository.impl.StavkaRacunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StavkaRacunaServis {

    private final StavkaRacunaRepository stavkaRepo;
    private final StavkaRacunaMapper mapper;

    @Autowired
    public StavkaRacunaServis(StavkaRacunaRepository stavkaRepo, StavkaRacunaMapper mapper) {
        this.stavkaRepo = stavkaRepo;
        this.mapper = mapper;
    }

    public List<StavkaRacunaDto> findAll() {
        return stavkaRepo.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public StavkaRacunaDto findById(Integer rb) throws Exception {
        StavkaRacuna s = stavkaRepo.findById(rb);
        return mapper.toDto(s);
    }

    public void save(StavkaRacunaDto dto) {
        stavkaRepo.save(mapper.toEntity(dto));
    }

    public void update(StavkaRacunaDto dto) {
        stavkaRepo.save(mapper.toEntity(dto));
    }

    public void delete(Integer rb) {
        stavkaRepo.deleteById(rb);
    }
}
