/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.servis;

import com.mycompany.njtprojekat.dto.impl.MehanicarDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.mapper.impl.MehanicarMapper;
import com.mycompany.njtprojekat.repository.impl.MehanicarRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MehanicarServis {

    private final MehanicarRepository mehanicarRepository;
    private final MehanicarMapper mehanicarMapper;

    @Autowired
    public MehanicarServis(MehanicarRepository mehanicarRepository, MehanicarMapper mehanicarMapper) {
        this.mehanicarRepository = mehanicarRepository;
        this.mehanicarMapper = mehanicarMapper;
    }

    public List<MehanicarDto> findAll() {
        return mehanicarRepository.findAll().stream().map(mehanicarMapper::toDto).collect(Collectors.toList());
    }

    public MehanicarDto findById(Integer id) throws Exception {
        return mehanicarMapper.toDto(mehanicarRepository.findById(id));
    }

    public MehanicarDto create(MehanicarDto mehanicarDto) {
        Mehanicar mehanicar = mehanicarMapper.toEntity(mehanicarDto);
        mehanicarRepository.save(mehanicar);
        return mehanicarMapper.toDto(mehanicar);
    }

    public void deleteById(Integer id) {
        mehanicarRepository.deleteById(id);
    }

    public MehanicarDto update(MehanicarDto mehanicarDto) {
        Mehanicar updated = mehanicarMapper.toEntity(mehanicarDto);
        mehanicarRepository.save(updated);
        return mehanicarMapper.toDto(updated);
    }

}
