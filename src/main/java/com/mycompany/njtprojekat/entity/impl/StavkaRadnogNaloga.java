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
    @Column(nullable = false)
    private int kolicina;
    @Column(nullable = false)
    private double cenaPoJedinici;
    @Column(nullable = false)
    private double ukupnaCena;
    @Column(nullable = false)
    private String opisZadatka;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "radninalog", nullable = false)
    private RadniNalog radniNalog;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usluga", nullable = false)
    private Usluga usluga;

    public StavkaRadnogNaloga() {
    }
    
    public StavkaRadnogNaloga(Integer rb) {
        this.rb = rb;
    }

    public Integer getRb() {
        return rb;
    }

    public void setRb(Integer rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
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

    public String getOpisZadatka() {
        return opisZadatka;
    }

    public void setOpisZadatka(String opisZadatka) {
        this.opisZadatka = opisZadatka;
    }

    public RadniNalog getRadniNalog() {
        return radniNalog;
    }

    public void setRadniNalog(RadniNalog radniNalog) {
        this.radniNalog = radniNalog;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    
    
}
