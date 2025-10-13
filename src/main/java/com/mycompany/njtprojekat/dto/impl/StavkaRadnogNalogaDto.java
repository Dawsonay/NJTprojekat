/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.Dto;
import jakarta.validation.constraints.*;



public class StavkaRadnogNalogaDto implements Dto{
    private Integer rb;
    
    @Positive(message = "Kolicina mora biti veca od 0")
    private int kolicina;
    
    @Positive(message = "Cena po jedinici mora biti veca od 0")
    private double cenaPoJedinici;
    
    @PositiveOrZero(message = "Ukupna cena ne sme biti negativna")
    private double ukupnaCena;
    
    @NotBlank(message = "Opis zadatka je obavezan")
    @Size(min = 5, max = 300, message = "Opis zadatka mora imati izmedju 5 i 300 karaktera")
    private String opisZadatka;
    
    @NotNull(message = "usluga je obavezna")
    private Integer idUsluga;

    public StavkaRadnogNalogaDto() {
    }

    public StavkaRadnogNalogaDto(Integer rb, int kolicina, double cenaPoJedinici, double ukupnaCena, String opisZadatka, Integer idUsluga) {
        this.rb = rb;
        this.kolicina = kolicina;
        this.cenaPoJedinici = cenaPoJedinici;
        this.ukupnaCena = ukupnaCena;
        this.opisZadatka = opisZadatka;
        this.idUsluga = idUsluga;
    }

    public Integer getRb() {
        return rb;
    }

    public void setRb(Integer rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
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

    public String getOpisZadatka() {
        return opisZadatka;
    }

    public void setOpisZadatka(String opisZadatka) {
        this.opisZadatka = opisZadatka;
    }

    public Integer getIdUsluga() {
        return idUsluga;
    }

    public void setIdUsluga(Integer idUsluga) {
        this.idUsluga = idUsluga;
    }

    

    

    

    
    
}
