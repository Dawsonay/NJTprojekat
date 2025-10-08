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

import com.mycompany.njtprojekat.dto.impl.MehanicarDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.mapper.impl.MehanicarMapper;
import com.mycompany.njtprojekat.repository.impl.MehanicarRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // NOVI IMPORT
import org.springframework.stereotype.Service;

@Service
public class MehanicarServis {

    private final MehanicarRepository mehanicarRepository;
    private final MehanicarMapper mehanicarMapper;
    private final PasswordEncoder passwordEncoder; // NOVO POLJE: PasswordEncoder

    @Autowired
    public MehanicarServis(
            MehanicarRepository mehanicarRepository, 
            MehanicarMapper mehanicarMapper,
            PasswordEncoder passwordEncoder // Dodato u konstruktor
    ) {
        this.mehanicarRepository = mehanicarRepository;
        this.mehanicarMapper = mehanicarMapper;
        this.passwordEncoder = passwordEncoder; // Inicijalizacija
    }

    public List<MehanicarDto> findAll() {
        return mehanicarRepository.findAll().stream().map(mehanicarMapper::toDto).collect(Collectors.toList());
    }

    public MehanicarDto findById(Integer id) throws Exception {
        return mehanicarMapper.toDto(mehanicarRepository.findById(id));
    }

    public MehanicarDto create(MehanicarDto mehanicarDto) {
        Mehanicar mehanicar = mehanicarMapper.toEntity(mehanicarDto);
        
        // KLJUČNA IZMENA: Hashovanje šifre pre čuvanja u bazi
        mehanicar.setPassword(passwordEncoder.encode(mehanicar.getPassword()));
        
        mehanicarRepository.save(mehanicar);
        return mehanicarMapper.toDto(mehanicar);
    }

    public void deleteById(Integer id) {
        mehanicarRepository.deleteById(id);
    }

    public MehanicarDto update(MehanicarDto mehanicarDto) {
        Mehanicar existingMehanicar = null;
        try {
            // Pronađemo postojeći entitet da ne bismo pregazili hashovanu šifru
            existingMehanicar = mehanicarRepository.findById(mehanicarDto.getIdMehanicar());
        } catch (Exception e) {
            // Logika za obradu greške ako mehanicar nije pronađen
            // Ovde samo nastavljamo, Vaša Repozitorijumska metoda već baca izuzetak
        }
        
        Mehanicar updated = mehanicarMapper.toEntity(mehanicarDto);
        
        // Zadržavamo staru (hashovanu) šifru ako nije prosleđena nova
        // BITNO: Ako se u DTO šalje šifra u čistom tekstu, morate je hashovati ovde!
        if (mehanicarDto.getPassword() == null || mehanicarDto.getPassword().isEmpty()) {
            updated.setPassword(existingMehanicar != null ? existingMehanicar.getPassword() : null);
        } else {
            // Ako je nova šifra poslata, hashujemo je
            updated.setPassword(passwordEncoder.encode(mehanicarDto.getPassword()));
        }
        
        mehanicarRepository.save(updated);
        return mehanicarMapper.toDto(updated);
    }

}
