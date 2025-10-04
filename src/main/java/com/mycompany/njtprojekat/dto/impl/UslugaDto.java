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


public class UslugaDto implements Dto{
    private Integer idUsluga;
    
    @NotBlank(message = "Naziv usluge je obavezan")
    @Size(min = 3, max = 100, message = "Naziv usluge mora imati između 3 i 100 karaktera")
    private String naziv;
    
    @NotBlank(message = "Opis usluge je obavezan")
    @Size(min = 5, max = 500, message = "Opis usluge mora imati između 5 i 500 karaktera")
    private String opis;
    
    @Positive(message = "Cena usluge mora biti veća od 0")
    private double cena;

    public UslugaDto() {
    }

    public UslugaDto(Integer idUsluga, String naziv, String opis, double cena) {
        this.idUsluga = idUsluga;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public Integer getIdUsluga() {
        return idUsluga;
    }

    public void setIdUsluga(Integer idUsluga) {
        this.idUsluga = idUsluga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    
}
