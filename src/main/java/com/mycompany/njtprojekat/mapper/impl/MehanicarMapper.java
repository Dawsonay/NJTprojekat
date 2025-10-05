/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.mapper.impl;

import com.mycompany.njtprojekat.dto.impl.MehanicarDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class MehanicarMapper implements DtoEntityMapper<MehanicarDto, Mehanicar> {

    @Override
    public MehanicarDto toDto(Mehanicar e) {
        return new MehanicarDto(
                e.getIdMehanicar(),
                e.getIme(),
                e.getPrezime(),
                e.getUsername(),
                e.getPassword()
        );
    }

    @Override
    public Mehanicar toEntity(MehanicarDto t) {
        return new Mehanicar(t.getIdMehanicar(), t.getIme(), t.getPrezime(), t.getUsername(), t.getPassword());
    }

}
