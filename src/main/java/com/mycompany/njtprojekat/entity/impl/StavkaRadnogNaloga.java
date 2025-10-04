/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.entity.impl;

import com.mycompany.njtprojekat.entity.MyEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name="stavkaradnognaloga")
public class StavkaRadnogNaloga implements MyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rb;
    @ManyToOne
    @JoinColumn(name = "radninalog", nullable = false)
    private RadniNalog radniNalog;
    private Integer kolicina;
    private double cenaPoJedinici;
    private double ukupnaCena;
    private String opisZadatka;
    @ManyToOne
    @JoinColumn(name = "usluga", nullable = false)
    private Usluga usluga;

    public StavkaRadnogNaloga() {
    }

    public StavkaRadnogNaloga(Integer rb, RadniNalog radniNalog, Integer kolicina, double cenaPoJedinici, double ukupnaCena, String opisZadatka,Usluga usluga) {
        this.rb = rb;
        this.radniNalog = radniNalog;
        this.kolicina = kolicina;
        this.cenaPoJedinici = cenaPoJedinici;
        this.ukupnaCena = ukupnaCena;
        this.opisZadatka=opisZadatka;
        this.usluga = usluga;
    }

    public Integer getRb() {
        return rb;
    }

    public void setRb(Integer rb) {
        this.rb = rb;
    }

    public RadniNalog getRadniNalog() {
        return radniNalog;
    }

    public void setRadniNalog(RadniNalog radniNalog) {
        this.radniNalog = radniNalog;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public double getCenaPoJedinici() {
        return cenaPoJedinici;
    }

    public void setCenaPoJedinici(double cenaPoJedinici) {
        this.cenaPoJedinici = cenaPoJedinici;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    public String getOpisZadatka() {
        return opisZadatka;
    }

    public void setOpisZadatka(String opisZadatka) {
        this.opisZadatka = opisZadatka;
    }
    
}
