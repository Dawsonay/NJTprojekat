package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.impl.MehanicarDto;

public class JwtResponse {
    private String token;
    private MehanicarDto korisnik;

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse(String token, MehanicarDto korisnik) {
        this.token = token;
        this.korisnik = korisnik;
    }

    public String getToken() {
        return token;
    }

    public MehanicarDto getKorisnik() {
        return korisnik;
    }
}
