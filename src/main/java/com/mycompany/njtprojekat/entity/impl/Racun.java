/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.entity.impl;

import com.mycompany.njtprojekat.entity.MyEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "racun")
public class Racun implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRacun;
    private Date datum;
    private double ukupanIznos;
    @ManyToOne
    @JoinColumn(name = "mehanicar", nullable = false)
    private Mehanicar mehanicar;
    @ManyToOne
    @JoinColumn(name = "vozilo", nullable = false)
    private Vozilo vozilo;
    @OneToMany(mappedBy = "racun", cascade = CascadeType.ALL)
    private List<StavkaRacuna> stavke;

    public Racun() {
        stavke = new ArrayList<>();
    }

    public Racun(Integer idRacun, Date datum, double ukupanIznos, Mehanicar mehanicar, Vozilo vozilo) {
        this.idRacun = idRacun;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.mehanicar = mehanicar;
        this.vozilo = vozilo;
        this.stavke = new ArrayList<>();
    }

    public Racun(Integer idRacun) {
        this.idRacun = idRacun;
    }

    public Integer getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(Integer idRacun) {
        this.idRacun = idRacun;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Mehanicar getMehanicar() {
        return mehanicar;
    }

    public void setMehanicar(Mehanicar mehanicar) {
        this.mehanicar = mehanicar;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRacuna> stavke) {
        this.stavke = stavke;
    }
    
}
