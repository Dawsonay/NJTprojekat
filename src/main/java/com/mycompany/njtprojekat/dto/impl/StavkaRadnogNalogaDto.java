/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.Dto;
import com.mycompany.njtprojekat.entity.impl.*;
import com.mycompany.njtprojekat.entity.MyEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;



public class StavkaRadnogNalogaDto implements Dto{
    private Integer rb;
    
    @NotNull(message = "Stavka mora biti povezana sa radnim nalogom")
    @Valid
    private RadniNalogDto radniNalog;
    
    @Positive(message = "Količina mora biti veća od 0")
    private Integer kolicina;
    
    @Positive(message = "Cena po jedinici mora biti veća od 0")
    private double cenaPoJedinici;
    
    @PositiveOrZero(message = "Ukupna cena ne sme biti negativna")
    private double ukupnaCena;
    
    @NotBlank(message = "Opis zadatka je obavezan")
    @Size(min = 5, max = 300, message = "Opis zadatka mora imati između 5 i 300 karaktera")
    private String opisZadatka;
    
    @NotNull(message = "Usluga je obavezna")
    @Valid
    private UslugaDto usluga;

    public StavkaRadnogNalogaDto() {
    }

    public StavkaRadnogNalogaDto(Integer rb, RadniNalogDto radniNalog, Integer kolicina, double cenaPoJedinici, double ukupnaCena, String opisZadatka,UslugaDto usluga) {
        this.rb = rb;
        this.radniNalog = radniNalog;
        this.kolicina = kolicina;
        this.cenaPoJedinici = cenaPoJedinici;
        this.ukupnaCena = ukupnaCena;
        this.opisZadatka=opisZadatka;
        this.usluga = usluga;
    }

    public Integer getRb() {
        return rb;
    }

    public void setRb(Integer rb) {
        this.rb = rb;
    }

    public RadniNalogDto getRadniNalog() {
        return radniNalog;
    }

    public void setRadniNalog(RadniNalogDto radniNalog) {
        this.radniNalog = radniNalog;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
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

    public UslugaDto getUsluga() {
        return usluga;
    }

    public void setUsluga(UslugaDto usluga) {
        this.usluga = usluga;
    }

    public String getOpisZadatka() {
        return opisZadatka;
    }

    public void setOpisZadatka(String opisZadatka) {
        this.opisZadatka = opisZadatka;
    }
    
}
