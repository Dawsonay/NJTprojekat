/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.Dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class RacunDto implements Dto  {
    private Integer idRacun;

    @NotNull(message = "Datum računa je obavezan")
    @PastOrPresent(message = "Datum računa ne može biti u budućnosti")
    private LocalDate datum;

    @PositiveOrZero(message = "Ukupan iznos ne sme biti negativan")
    private double ukupanIznos;

    @NotNull(message = "Račun mora imati mehaničara")
    private Integer idMehanicar;

    @NotNull(message = "Račun mora biti vezan za vozilo")
    private Integer idVozilo;

//    @Valid
//    @NotNull(message = "Račun mora imati stavke")
//    @Size(min = 1, message = "Račun mora imati bar jednu stavku")
    private List<StavkaRacunaDto> stavke = new ArrayList<>();

    public RacunDto() {
    }

    public RacunDto(Integer idRacun, LocalDate datum, double ukupanIznos,
                    Integer idMehanicar, Integer idVozilo, List<StavkaRacunaDto> stavke) {
        this.idRacun = idRacun;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.idMehanicar = idMehanicar;
        this.idVozilo = idVozilo;
        this.stavke = stavke != null ? stavke : new ArrayList<>();
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
        this.stavke = stavke != null ? stavke : new ArrayList<>();
    }
}
