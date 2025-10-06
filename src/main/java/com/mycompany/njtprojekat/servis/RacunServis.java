/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.servis;

import com.mycompany.njtprojekat.dto.impl.RacunDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.entity.impl.Racun;
import com.mycompany.njtprojekat.entity.impl.StavkaRacuna;
import com.mycompany.njtprojekat.entity.impl.Usluga;
import com.mycompany.njtprojekat.entity.impl.Vozilo;
import com.mycompany.njtprojekat.mapper.impl.RacunMapper;
import com.mycompany.njtprojekat.mapper.impl.UslugaMapper;
import com.mycompany.njtprojekat.repository.impl.RacunRepository;
import com.mycompany.njtprojekat.repository.impl.UslugaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RacunServis {

    private final RacunRepository racunRepository;
    private final RacunMapper racunMapper;

    @Autowired
    public RacunServis(RacunRepository racunRepository, RacunMapper racunMapper) {
        this.racunRepository = racunRepository;
        this.racunMapper = racunMapper;
    }

    public List<RacunDto> findAll() {
        return racunRepository.findAll().stream().map(racunMapper::toDto).collect(Collectors.toList());
    }

    public RacunDto findById(Integer id) throws Exception {
        return racunMapper.toDto(racunRepository.findById(id));
    }

    public RacunDto create(RacunDto racunDto) {
        Racun racun = racunMapper.toEntity(racunDto);
        racunRepository.save(racun);
        return racunMapper.toDto(racun);
    }

    public void deleteById(Integer id) {
        racunRepository.deleteById(id);
    }

    public RacunDto update(RacunDto racunDto) throws Exception {

        Racun updated = racunMapper.toEntity(racunDto);
        racunRepository.save(updated);
        return racunMapper.toDto(updated);
    }
}
