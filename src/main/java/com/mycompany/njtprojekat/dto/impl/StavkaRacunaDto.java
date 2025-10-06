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

    @NotNull(message = "Količina je obavezna")
    @Positive(message = "Količina mora biti veća od 0")
    private Integer kolicina;

    @NotNull(message = "Cena je obavezna")
    @Positive(message = "Cena mora biti veća od 0")
    private double cena;

    @PositiveOrZero(message = "Iznos ne sme biti negativan")
    private double iznos;

    @NotNull(message = "Usluga je obavezna")
    private Integer idUsluga;
    
    
    private Integer idRacun;

    public StavkaRacunaDto() {
    }

    public StavkaRacunaDto(Integer rb, Integer kolicina, double cena, double iznos, Integer idUsluga, Integer idRacun) {
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.idUsluga = idUsluga;
        this.idRacun=idRacun;
    }

    public Integer getRb() {
        return rb;
    }

    public void setRb(Integer rb) {
        this.rb = rb;
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

    public Integer getIdUsluga() {
        return idUsluga;
    }

    public void setIdUsluga(Integer idUsluga) {
        this.idUsluga = idUsluga;
    }

    public Integer getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(Integer idRacun) {
        this.idRacun = idRacun;
    }
    
}
