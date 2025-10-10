package com.mycompany.njtprojekat.entity.impl;

import com.mycompany.njtprojekat.entity.MyEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "stavkaracuna")
public class StavkaRacuna implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rb;

    @Column(nullable = false)
    private double cena;

    @Column(nullable = false)
    private double iznos;

    @Column(nullable = false)
    private int kolicina;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "racun", nullable = false)
    private Racun racun;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usluga", nullable = false)
    private Usluga usluga;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Integer rb) {
        this.rb = rb;
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

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    
}
