/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.Dto;
import com.mycompany.njtprojekat.entity.impl.*;
import com.mycompany.njtprojekat.entity.MyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RadniNalogDto implements Dto {
    private Integer idRadniNalog;
    
    @NotNull(message = "Datum otvaranja je obavezan")
    @PastOrPresent(message = "Datum otvaranja ne može biti u budućnosti")
    private Date datumOtvaranja;
    
    @PastOrPresent(message = "Datum zatvaranja ne može biti u budućnosti")
    private Date datumZatvaranja;
    
    @NotBlank(message = "Opis kvara je obavezan")
    @Size(min = 5, max = 500, message = "Opis kvara mora imati između 5 i 500 karaktera")
    private String opisKvara;
    
    @NotBlank(message = "Status radnog naloga je obavezan")
    @Pattern(
        regexp = "^(OTVOREN|U_TOKU|ZATVOREN)$",
        message = "Status mora biti OTVOREN, U_TOKU ili ZATVOREN"
    )
    private String status;
    
    @NotNull(message = "Radni nalog mora imati mehaničara")
    @Valid
    private Integer idMehanicar;
    
    @NotNull(message = "Radni nalog mora sadržati bar jednu stavku")
    @Size(min = 1, message = "Radni nalog mora imati bar jednu stavku")
    @Valid
    private List<StavkaRadnogNalogaDto> stavke;

    public RadniNalogDto() {
        stavke = new ArrayList<>();
    }

    public RadniNalogDto(Integer idRadniNalog, Date datumOtvaranja, Date datumZatvaranja, String opisKvara, String status, Integer mehanicar, List<StavkaRadnogNalogaDto> stavke) {
        this.idRadniNalog = idRadniNalog;
        this.datumOtvaranja = datumOtvaranja;
        this.datumZatvaranja = datumZatvaranja;
        this.opisKvara = opisKvara;
        this.status = status;
        this.idMehanicar = mehanicar;
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

    public Integer getIdMehanicar() {
        return idMehanicar;
    }

    public void setIdMehanicar(Integer mehanicar) {
        this.idMehanicar = mehanicar;
    }

    public List<StavkaRadnogNalogaDto> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRadnogNalogaDto> stavke) {
        this.stavke = stavke;
    }
    
}
