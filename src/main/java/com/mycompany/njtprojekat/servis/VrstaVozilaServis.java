/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.servis;

import com.mycompany.njtprojekat.dto.impl.VoziloDto;
import com.mycompany.njtprojekat.dto.impl.VrstaVozilaDto;
import com.mycompany.njtprojekat.entity.impl.Vozilo;
import com.mycompany.njtprojekat.entity.impl.VrstaVozila;
import com.mycompany.njtprojekat.mapper.impl.VoziloMapper;
import com.mycompany.njtprojekat.mapper.impl.VrstaVozilaMapper;
import com.mycompany.njtprojekat.repository.impl.VoziloRepository;
import com.mycompany.njtprojekat.repository.impl.VrstaVozilaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VrstaVozilaServis {
    private final VrstaVozilaRepository vrstaVozilaRepository;
    private final VrstaVozilaMapper vrstaVozilaMapper;

    @Autowired
    public VrstaVozilaServis(VrstaVozilaRepository vrstaVozilaRepository, VrstaVozilaMapper vrstaVozilaMapper) {
        this.vrstaVozilaRepository = vrstaVozilaRepository;
        this.vrstaVozilaMapper = vrstaVozilaMapper;
    }

    public List<VrstaVozilaDto> findAll() {
        return vrstaVozilaRepository.findAll().stream().map(vrstaVozilaMapper::toDto).collect(Collectors.toList());
    }
    public VrstaVozilaDto findById(Integer id) throws Exception{
        return vrstaVozilaMapper.toDto(vrstaVozilaRepository.findById(id));
    }
    
    public VrstaVozilaDto create(VrstaVozilaDto vrstaVozilaDto) {
        VrstaVozila vrstaVozila = vrstaVozilaMapper.toEntity(vrstaVozilaDto);
        vrstaVozilaRepository.save(vrstaVozila);
        return vrstaVozilaMapper.toDto(vrstaVozila);
    }

    public void deleteById(Integer id) {
        vrstaVozilaRepository.deleteById(id);
    }

    public VrstaVozilaDto update(VrstaVozilaDto vrstaVozilaDto) {
        VrstaVozila updated = vrstaVozilaMapper.toEntity(vrstaVozilaDto);
        vrstaVozilaRepository.save(updated);
        return vrstaVozilaMapper.toDto(updated);
    }
}
