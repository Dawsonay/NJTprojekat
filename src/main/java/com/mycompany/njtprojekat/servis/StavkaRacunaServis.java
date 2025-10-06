/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.servis;

import com.mycompany.njtprojekat.dto.impl.StavkaRacunaDto;
import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.entity.impl.StavkaRacuna;
import com.mycompany.njtprojekat.mapper.impl.RacunMapper;
import com.mycompany.njtprojekat.mapper.impl.StavkaRacunaMapper;
import com.mycompany.njtprojekat.repository.impl.RacunRepository;
import com.mycompany.njtprojekat.repository.impl.StavkaRacunaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StavkaRacunaServis {
    private final StavkaRacunaRepository stavkaRacunaRepository;
    private final  StavkaRacunaMapper stavkaRacunaMapper;

    @Autowired
    public StavkaRacunaServis(StavkaRacunaRepository stavkaRacunaRepository, StavkaRacunaMapper stavkaRacunaMapper) {
        this.stavkaRacunaRepository = stavkaRacunaRepository;
        this.stavkaRacunaMapper = stavkaRacunaMapper;
    }

    public List<StavkaRacunaDto> findAll() {
        return stavkaRacunaRepository.findAll().stream().map(stavkaRacunaMapper::toDto).collect(Collectors.toList());
    }

    public StavkaRacunaDto findById(Integer id) throws Exception {
        return stavkaRacunaMapper.toDto(stavkaRacunaRepository.findById(id));
    }

    public StavkaRacunaDto create(StavkaRacunaDto stavkaRacunaDto) {
        StavkaRacuna stavkaRacuna = stavkaRacunaMapper.toEntity(stavkaRacunaDto);
        stavkaRacunaRepository.save(stavkaRacuna);
        return stavkaRacunaMapper.toDto(stavkaRacuna);
    }

    public void deleteById(Integer id) {
        stavkaRacunaRepository.deleteById(id);
    }

    public StavkaRacunaDto update(StavkaRacunaDto stavkaRacunaDto) {
        StavkaRacuna updated = stavkaRacunaMapper.toEntity(stavkaRacunaDto);
        stavkaRacunaRepository.save(updated);
        return stavkaRacunaMapper.toDto(updated);
    }
}
