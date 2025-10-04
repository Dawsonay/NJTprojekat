/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.entity.impl;

import jakarta.persistence.*;
import com.mycompany.njtprojekat.entity.MyEntity;

@Entity
@Table(name="usluga")
public class Usluga implements MyEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsluga;
    private String naziv;
    private String opis;
    private double cena;

    public Usluga() {
    }

    public Usluga(Integer idUsluga, String naziv, String opis, double cena) {
        this.idUsluga = idUsluga;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public Usluga(Integer idUsluga) {
        this.idUsluga = idUsluga;
    }

    public Integer getIdUsluga() {
        return idUsluga;
    }

    public void setIdUsluga(Integer idUsluga) {
        this.idUsluga = idUsluga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    
}
