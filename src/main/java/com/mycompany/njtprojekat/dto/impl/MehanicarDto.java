/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.Dto;
import jakarta.validation.constraints.*;

public class MehanicarDto implements Dto {

    private Integer idMehanicar;

    @NotBlank(message = "Ime je obavezno")
    @Size(min = 2, max = 30, message = "Ime mora imati izmedju 2 i 30 karaktera")
    private String ime;

    @NotBlank(message = "Prezime je obavezno")
    @Size(min = 2, max = 30, message = "Prezime mora imati izmedju 2 i 30 karaktera")
    private String prezime;

    @NotBlank(message = "Korisnicko ime je obavezno")
    @Size(min = 4, max = 20, message = "Korisnicko ime mora imati izmedju 4 i 20 karaktera")
    private String username;

    // ⚙️ Više nije obavezno polje — može biti null ili prazan string ako se ne menja
    @Size(min = 8, max = 100, message = "Lozinka mora imati najmanje 8 karaktera")
    private String password;

    public MehanicarDto() {}

    public MehanicarDto(Integer idMehanicar, String ime, String prezime, String username, String password) {
        this.idMehanicar = idMehanicar;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public Integer getIdMehanicar() {
        return idMehanicar;
    }

    public void setIdMehanicar(Integer idMehanicar) {
        this.idMehanicar = idMehanicar;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
