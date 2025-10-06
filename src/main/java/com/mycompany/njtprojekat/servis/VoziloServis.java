/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.servis;
import com.mycompany.njtprojekat.dto.impl.MehanicarDto;
import com.mycompany.njtprojekat.dto.impl.VoziloDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.entity.impl.Vozilo;
import com.mycompany.njtprojekat.mapper.impl.VoziloMapper;
import com.mycompany.njtprojekat.repository.impl.VoziloRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoziloServis {
    private final VoziloRepository voziloRepository;
    private final VoziloMapper voziloMapper;

    @Autowired
    public VoziloServis(VoziloRepository voziloRepository, VoziloMapper voziloMapper) {
        this.voziloRepository = voziloRepository;
        this.voziloMapper = voziloMapper;
    }

    public List<VoziloDto> findAll() {
        return voziloRepository.findAll().stream().map(voziloMapper::toDto).collect(Collectors.toList());
    }
    public VoziloDto findById(Integer id) throws Exception{
        return voziloMapper.toDto(voziloRepository.findById(id));
    }
    
    public VoziloDto create(VoziloDto voziloDto) {
        Vozilo vozilo = voziloMapper.toEntity(voziloDto);
        voziloRepository.save(vozilo);
        return voziloMapper.toDto(vozilo);
    }

    public void deleteById(Integer id) {
        voziloRepository.deleteById(id);
    }

    public VoziloDto update(VoziloDto voziloDto) {
        Vozilo updated = voziloMapper.toEntity(voziloDto);
        voziloRepository.save(updated);
        return voziloMapper.toDto(updated);
    }
}
