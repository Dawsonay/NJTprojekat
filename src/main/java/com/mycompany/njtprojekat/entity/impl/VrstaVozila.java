/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.entity.impl;

import jakarta.persistence.*;
import com.mycompany.njtprojekat.entity.MyEntity;

@Entity
@Table(name="vrstavozila")
public class VrstaVozila implements MyEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVrsta;
    private String naziv;
    private double maxNosivost;
    private String namena;

    public VrstaVozila() {
    }

    public VrstaVozila(Integer idVrsta, String naziv, double maxNosivost, String namena) {
        this.idVrsta = idVrsta;
        this.naziv = naziv;
        this.maxNosivost = maxNosivost;
        this.namena = namena;
    }

    public VrstaVozila(Integer idVrsta) {
        this.idVrsta = idVrsta;
    }

    public Integer getIdVrsta() {
        return idVrsta;
    }

    public void setIdVrsta(Integer idVrsta) {
        this.idVrsta = idVrsta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getMaxNosivost() {
        return maxNosivost;
    }

    public void setMaxNosivost(double maxNosivost) {
        this.maxNosivost = maxNosivost;
    }

    public String getNamena() {
        return namena;
    }

    public void setNamena(String namena) {
        this.namena = namena;
    }
    
    
}
