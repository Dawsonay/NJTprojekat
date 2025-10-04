/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.Dto;
import com.mycompany.njtprojekat.entity.impl.*;
import jakarta.persistence.*;
import com.mycompany.njtprojekat.entity.MyEntity;
import jakarta.validation.constraints.*;


public class VrstaVozilaDto implements Dto{
    private Integer idVrsta;
    
    @NotBlank(message = "Naziv vrste vozila je obavezan")
    @Size(min = 2, max = 50, message = "Naziv mora imati izmedju 2 i 50 karaktera")
    private String naziv;
    
    @Positive(message = "Maksimalna nosivost mora biti veća od 0")
    private double maxNosivost;
    
    @NotBlank(message = "Namena vozila je obavezna")
    @Size(min = 3, max = 100, message = "Namena mora imati izmedju 3 i 100 karaktera")
    private String namena;

    public VrstaVozilaDto() {
    }

    public VrstaVozilaDto(Integer idVrsta, String naziv, double maxNosivost, String namena) {
        this.idVrsta = idVrsta;
        this.naziv = naziv;
        this.maxNosivost = maxNosivost;
        this.namena = namena;
    }

    public Integer getIdVrsta() {
        return idVrsta;
    }

    public void setIdVrsta(Integer idVrsta) {
        this.idVrsta = idVrsta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getMaxNosivost() {
        return maxNosivost;
    }

    public void setMaxNosivost(double maxNosivost) {
        this.maxNosivost = maxNosivost;
    }

    public String getNamena() {
        return namena;
    }

    public void setNamena(String namena) {
        this.namena = namena;
    }
    
    
}
