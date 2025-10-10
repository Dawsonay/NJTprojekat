/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.Dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class RacunDto implements Dto {

    private Integer idRacun;
    private LocalDate datum;
    private double ukupanIznos;
    @NotNull(message = "mehanicar je obavezan")
    private Integer idMehanicar;
    @NotNull(message = "vozilo je obavezno")
    private Integer idVozilo;
    @Valid
    @NotEmpty(message = "racun mora imati bar jednu stavku")
    private List<StavkaRacunaDto> stavke;

    public RacunDto() {
    }

    public RacunDto(Integer idRacun, LocalDate datum, double ukupanIznos, Integer idMehanicar, Integer idVozilo, List<StavkaRacunaDto> stavke) {
        this.idRacun = idRacun;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.idMehanicar = idMehanicar;
        this.idVozilo = idVozilo;
        this.stavke = stavke;
    }

    public Integer getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(Integer idRacun) {
        this.idRacun = idRacun;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Integer getIdMehanicar() {
        return idMehanicar;
    }

    public void setIdMehanicar(Integer idMehanicar) {
        this.idMehanicar = idMehanicar;
    }

    public Integer getIdVozilo() {
        return idVozilo;
    }

    public void setIdVozilo(Integer idVozilo) {
        this.idVozilo = idVozilo;
    }

    public List<StavkaRacunaDto> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRacunaDto> stavke) {
        this.stavke = stavke;
    }

    
}
