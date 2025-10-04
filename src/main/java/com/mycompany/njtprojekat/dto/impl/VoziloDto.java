/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.Dto;
import com.mycompany.njtprojekat.entity.impl.*;
import jakarta.persistence.*;
import com.mycompany.njtprojekat.entity.MyEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


public class VoziloDto implements Dto{
    private Integer idVozilo;
    
    @NotBlank(message = "Broj šasije je obavezan")
    @Size(min = 5, max = 50, message = "Broj šasije mora imati između 5 i 50 karaktera")
    private String brojSasije;
    
    @NotBlank(message = "Model vozila je obavezan")
    @Size(min = 2, max = 50, message = "Model mora imati između 2 i 50 karaktera")
    private String model;
    
    @NotBlank(message = "Registarski broj je obavezan")
    @Size(min = 5, max = 15, message = "Registarski broj mora imati između 5 i 15 karaktera")
    private String registarskiBroj;
    
    @NotBlank(message = "Tip goriva je obavezan")
    @Pattern(regexp = "^(Benzin|Dizel|Elektricni|Hibrid)$", message = "Tip goriva mora biti Benzin, Dizel, Elektricni ili Hibrid")
    private String tipGoriva;
    
    @Min(value = 1900, message = "Godina proizvodnje mora biti realna")
    @Max(value = 2100, message = "Godina proizvodnje mora biti realna")
    private Integer godiste;
    
    @NotNull(message = "Vrsta vozila je obavezna")
    @Valid
    private VrstaVozilaDto vrsta;

    public VoziloDto() {
    }

    public VoziloDto(Integer idVozilo, String brojSasije, String model, String registarskiBroj, String tipGoriva, Integer godiste, VrstaVozilaDto vrsta) {
        this.idVozilo = idVozilo;
        this.brojSasije = brojSasije;
        this.model = model;
        this.registarskiBroj = registarskiBroj;
        this.tipGoriva = tipGoriva;
        this.godiste = godiste;
        this.vrsta = vrsta;
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

    public VrstaVozilaDto getVrsta() {
        return vrsta;
    }

    public void setVrsta(VrstaVozilaDto vrsta) {
        this.vrsta = vrsta;
    }
    
}
