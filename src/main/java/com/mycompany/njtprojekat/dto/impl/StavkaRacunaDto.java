/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.Dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;



public class StavkaRacunaDto implements Dto {
    private Integer rb;
    
    @NotNull(message = "Stavka mora biti povezana sa racunom")
    @Valid
    private RacunDto racun;
    
    @Positive(message = "Kolicina mora biti veća od 0")
    private Integer kolicina;
    
    @Positive(message = "Cena mora biti veća od 0")
    private double cena;
    
    @PositiveOrZero(message = "Iznos ne sme biti negativan")
    private double iznos;
    
    @NotNull(message = "Usluga je obavezna")
    @Valid
    private UslugaDto usluga;

    public StavkaRacunaDto() {
    }

    public StavkaRacunaDto(Integer rb, RacunDto racun, Integer kolicina, double cena, double iznos, UslugaDto usluga) {
        this.rb = rb;
        this.racun = racun;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.usluga = usluga;
    }

    public Integer getRb() {
        return rb;
    }

    public void setRb(Integer rb) {
        this.rb = rb;
    }

    public RacunDto getRacun() {
        return racun;
    }

    public void setRacun(RacunDto racun) {
        this.racun = racun;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public UslugaDto getUsluga() {
        return usluga;
    }

    public void setUsluga(UslugaDto usluga) {
        this.usluga = usluga;
    }
    
    
}
