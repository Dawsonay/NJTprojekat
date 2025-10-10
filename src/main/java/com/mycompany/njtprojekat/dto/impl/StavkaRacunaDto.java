/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.Dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class StavkaRacunaDto implements Dto {

    private Integer rb;

    @NotNull(message = "cena je obavezna")
    private double cena;
    @NotNull(message = "iznos je obavezan")
    private double iznos;
    @Min(value=1,message = "kolicina mora biti bar 1")
    private int kolicina;
    @NotNull(message = "usluga je obavezna")
    private Integer idUsluga;

    public StavkaRacunaDto() {
    }

    public StavkaRacunaDto(Integer rb, double cena, double iznos, int kolicina, Integer idUsluga) {
        this.rb = rb;
        this.cena = cena;
        this.iznos = iznos;
        this.kolicina = kolicina;
        this.idUsluga = idUsluga;
    }

    public Integer getRb() {
        return rb;
    }

    public void setRb(Integer rb) {
        this.rb = rb;
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

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Integer getIdUsluga() {
        return idUsluga;
    }

    public void setIdUsluga(Integer idUsluga) {
        this.idUsluga = idUsluga;
    }

   
}
