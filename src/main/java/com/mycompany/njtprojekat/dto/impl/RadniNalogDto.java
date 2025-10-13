/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.Dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

import java.util.List;

public class RadniNalogDto implements Dto {
    private Integer idRadniNalog;
    
    @NotNull(message = "Datum otvaranja je obavezan")
    private LocalDate datumOtvaranja;
    
    private LocalDate datumZatvaranja;
    
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
    @NotNull(message = "Radni nalog mora imati vozilo")
    @Valid
    private Integer idVozilo;
    
    @NotEmpty(message = "Radni nalog mora sadržati bar jednu stavku")
    @Size(min = 1, message = "Radni nalog mora imati bar jednu stavku")
    @Valid
    private List<StavkaRadnogNalogaDto> stavke;

    public RadniNalogDto() {
    }

    public RadniNalogDto(Integer idRadniNalog, LocalDate datumOtvaranja, LocalDate datumZatvaranja, String opisKvara, String status, Integer idMehanicar, Integer idVozilo, List<StavkaRadnogNalogaDto> stavke) {
        this.idRadniNalog = idRadniNalog;
        this.datumOtvaranja = datumOtvaranja;
        this.datumZatvaranja = datumZatvaranja;
        this.opisKvara = opisKvara;
        this.status = status;
        this.idMehanicar = idMehanicar;
        this.idVozilo = idVozilo;
        this.stavke = stavke;
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

    public Integer getIdMehanicar() {
        return idMehanicar;
    }

    public void setIdMehanicar(Integer idMehanicar) {
        this.idMehanicar = idMehanicar;
    }

    public List<StavkaRadnogNalogaDto> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRadnogNalogaDto> stavke) {
        this.stavke = stavke;
    }

    public void setIdVozilo(Integer idVozilo) {
        this.idVozilo = idVozilo;
    }

    public Integer getIdVozilo() {
        return idVozilo;
    }

    
    

   
}
