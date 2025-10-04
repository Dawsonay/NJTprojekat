/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.entity.impl;

import com.mycompany.njtprojekat.entity.MyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stavkaracuna")
public class StavkaRacuna implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rb;
    @ManyToOne
    @JoinColumn(name = "racun", nullable = false)
    private Racun racun;
    private Integer kolicina;
    private double cena;
    private double iznos;
    @ManyToOne
    @JoinColumn(name = "usluga", nullable = false)
    private Usluga usluga;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Integer rb, Racun racun, Integer kolicina, double cena, double iznos, Usluga usluga) {
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

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
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

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }
    
    
}
