package com.mycompany.njtprojekat.entity.impl;

import com.mycompany.njtprojekat.entity.MyEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "racun")
public class Racun implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRacun;

    @Column(nullable = false)
    private LocalDate datum;

    @Column(name = "ukupan_iznos")
    private Double ukupanIznos;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mehanicar", nullable = false)
    private Mehanicar mehanicar;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vozilo", nullable = false)
    private Vozilo vozilo;

    // Dvosmerna veza sa stavkama
    @OneToMany(mappedBy = "racun", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StavkaRacuna> stavke = new ArrayList<>();

    public Racun() {
    }

    public Racun(Integer idRacun) {
        this.idRacun = idRacun;
    }

    public Racun(Integer idRacun, LocalDate datum, Double ukupanIznos, Mehanicar mehanicar, Vozilo vozilo) {
        this.idRacun = idRacun;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.mehanicar = mehanicar;
        this.vozilo = vozilo;
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

    public Double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(Double ukupanIznos) {
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
    
    
    public void addItem(StavkaRacuna stavka) {
        stavka.setRacun(this);
        this.stavke.add(stavka);
        
    }

    public void removeItem(StavkaRacuna stavka) {
        stavka.setRacun(null);
        this.stavke.remove(stavka);
    }
}
