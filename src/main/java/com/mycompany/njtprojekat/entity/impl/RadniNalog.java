/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.entity.impl;

import com.mycompany.njtprojekat.entity.MyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "radninalog")
public class RadniNalog implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRadniNalog;
    private Date datumOtvaranja;
    private Date datumZatvaranja;
    private String opisKvara;
    private String status;
    @ManyToOne
    @JoinColumn(name = "mehanicar", nullable = false)
    private Mehanicar mehanicar;
    @OneToMany(mappedBy = "radniNalog", cascade = CascadeType.ALL)
    private List<StavkaRadnogNaloga> stavke;

    public RadniNalog() {
        stavke = new ArrayList<>();
    }

    public RadniNalog(Integer idRadniNalog, Date datumOtvaranja, Date datumZatvaranja, String opisKvara, String status, Mehanicar mehanicar, List<StavkaRadnogNaloga> stavke) {
        this.idRadniNalog = idRadniNalog;
        this.datumOtvaranja = datumOtvaranja;
        this.datumZatvaranja = datumZatvaranja;
        this.opisKvara = opisKvara;
        this.status = status;
        this.mehanicar = mehanicar;
        this.stavke = stavke;
    }

    

    public Integer getIdRadniNalog() {
        return idRadniNalog;
    }

    public void setIdRadniNalog(Integer idRadniNalog) {
        this.idRadniNalog = idRadniNalog;
    }

    public Date getDatumOtvaranja() {
        return datumOtvaranja;
    }

    public void setDatumOtvaranja(Date datumOtvaranja) {
        this.datumOtvaranja = datumOtvaranja;
    }

    public Date getDatumZatvaranja() {
        return datumZatvaranja;
    }

    public void setDatumZatvaranja(Date datumZatvaranja) {
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
    
}
