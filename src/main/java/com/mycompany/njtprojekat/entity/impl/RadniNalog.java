/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.entity.impl;

import com.mycompany.njtprojekat.entity.MyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "radninalog")
public class RadniNalog implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRadniNalog;
    @Column(nullable = false)
    private LocalDate datumOtvaranja;
    @Column
    private LocalDate datumZatvaranja;
    @Column(nullable = false)
    private String opisKvara;
    @Column(nullable = false)
    private String status;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mehanicar", nullable = false)
    private Mehanicar mehanicar;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vozilo", nullable = false)
    private Vozilo vozilo;
    @OneToMany(mappedBy = "radniNalog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StavkaRadnogNaloga> stavke = new ArrayList<>();

    public RadniNalog() {
        stavke = new ArrayList<>();
    }
    public RadniNalog(Integer idRadniNalog) {
        this.idRadniNalog = idRadniNalog;
    }

    public RadniNalog(Integer idRadniNalog, LocalDate datumOtvaranja, LocalDate datumZatvaranja, String opisKvara, String status, Mehanicar mehanicar, Vozilo vozilo) {
        this.idRadniNalog = idRadniNalog;
        this.datumOtvaranja = datumOtvaranja;
        this.datumZatvaranja = datumZatvaranja;
        this.opisKvara = opisKvara;
        this.status = status;
        this.mehanicar = mehanicar;
        this.vozilo = vozilo;
    }

    

    

    public Integer getIdRadniNalog() {
        return idRadniNalog;
    }

    public void setIdRadniNalog(Integer idRadniNalog) {
        this.idRadniNalog = idRadniNalog;
    }

    public LocalDate getDatumOtvaranja() {
        return datumOtvaranja;
    }

    public void setDatumOtvaranja(LocalDate datumOtvaranja) {
        this.datumOtvaranja = datumOtvaranja;
    }

    public LocalDate getDatumZatvaranja() {
        return datumZatvaranja;
    }

    public void setDatumZatvaranja(LocalDate datumZatvaranja) {
        this.datumZatvaranja = datumZatvaranja;
    }

    public String getOpisKvara() {
        return opisKvara;
    }

    public void setOpisKvara(String opisKvara) {
        this.opisKvara = opisKvara;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Mehanicar getMehanicar() {
        return mehanicar;
    }

    public void setMehanicar(Mehanicar mehanicar) {
        this.mehanicar = mehanicar;
    }

    public List<StavkaRadnogNaloga> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRadnogNaloga> stavke) {
        this.stavke = stavke;
    }

    public void addItem(StavkaRadnogNaloga stavka) {
        stavka.setRadniNalog(this);
        this.stavke.add(stavka);
        
    }

    public void removeItem(StavkaRadnogNaloga stavka) {
        stavka.setRadniNalog(null);
        this.stavke.remove(stavka);
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }
    

}
