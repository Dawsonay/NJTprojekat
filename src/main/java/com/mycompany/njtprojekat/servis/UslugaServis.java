/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.servis;
import com.mycompany.njtprojekat.dto.impl.MehanicarDto;
import com.mycompany.njtprojekat.dto.impl.UslugaDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.entity.impl.Usluga;
import com.mycompany.njtprojekat.mapper.impl.UslugaMapper;
import com.mycompany.njtprojekat.repository.impl.UslugaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UslugaServis {
     private final UslugaRepository uslugaRepository;
    private final UslugaMapper uslugaMapper;

    @Autowired
    public UslugaServis(UslugaRepository uslugaRepository, UslugaMapper uslugaMapper) {
        this.uslugaRepository = uslugaRepository;
        this.uslugaMapper = uslugaMapper;
    }

    public List<UslugaDto> findAll() {
        return uslugaRepository.findAll().stream().map(uslugaMapper::toDto).collect(Collectors.toList());
    }
    public UslugaDto findById(Integer id) throws Exception{
        return uslugaMapper.toDto(uslugaRepository.findById(id));
    }
    
    public UslugaDto create(UslugaDto uslugaDto) {
        Usluga usluga = uslugaMapper.toEntity(uslugaDto);
        uslugaRepository.save(usluga);
        return uslugaMapper.toDto(usluga);
    }

    public void deleteById(Integer id) {
        uslugaRepository.deleteById(id);
    }

    public UslugaDto update(UslugaDto uslugaDto) throws Exception {
        Usluga existing = uslugaRepository.findById(uslugaDto.getIdUsluga());
        
        Usluga updated = uslugaMapper.toEntity(uslugaDto);
        Usluga saved = uslugaRepository.saveU(updated);
        // 
        return uslugaMapper.toDto(saved);

    }
}
