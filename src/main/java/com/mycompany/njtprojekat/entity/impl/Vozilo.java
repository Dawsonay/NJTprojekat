/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.entity.impl;

import jakarta.persistence.*;
import com.mycompany.njtprojekat.entity.MyEntity;

@Entity
@Table(name="vozilo")
public class Vozilo implements MyEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVozilo;
    private String brojSasije;
    private String model;
    private String registarskiBroj;
    private String tipGoriva;
    private Integer godiste;
    
    @ManyToOne
    @JoinColumn(name="idVrsta")
    private VrstaVozila vrsta;

    public Vozilo() {
    }

    public Vozilo(Integer idVozilo, String brojSasije, String model, String registarskiBroj, String tipGoriva, Integer godiste, VrstaVozila vrsta) {
        this.idVozilo = idVozilo;
        this.brojSasije = brojSasije;
        this.model = model;
        this.registarskiBroj = registarskiBroj;
        this.tipGoriva = tipGoriva;
        this.godiste = godiste;
        this.vrsta = vrsta;
    }

    public Vozilo(Integer idVozilo) {
        this.idVozilo = idVozilo;
    }

    public Integer getIdVozilo() {
        return idVozilo;
    }

    public void setIdVozilo(Integer idVozilo) {
        this.idVozilo = idVozilo;
    }

    public String getBrojSasije() {
        return brojSasije;
    }

    public void setBrojSasije(String brojSasije) {
        this.brojSasije = brojSasije;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(String registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    public String getTipGoriva() {
        return tipGoriva;
    }

    public void setTipGoriva(String tipGoriva) {
        this.tipGoriva = tipGoriva;
    }

    public Integer getGodiste() {
        return godiste;
    }

    public void setGodiste(Integer godiste) {
        this.godiste = godiste;
    }

    public VrstaVozila getVrsta() {
        return vrsta;
    }

    public void setVrsta(VrstaVozila vrsta) {
        this.vrsta = vrsta;
    }
    
}
