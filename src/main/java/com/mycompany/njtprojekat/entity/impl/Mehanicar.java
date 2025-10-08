/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.entity.impl;

import jakarta.persistence.*;
import com.mycompany.njtprojekat.entity.MyEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// @OneToMany(mappedBy="restaurant", cascade=CascadeType.ALL)
@Entity
@Table(name = "mehanicar",
        uniqueConstraints = {
            @UniqueConstraint(name = "uk_mehanicar_username", columnNames = "username")})
public class Mehanicar implements MyEntity, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMehanicar;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Mehanicar() {
    }

    public Mehanicar(Integer idMehanicar, String ime, String prezime, String username, String password) {
        this.idMehanicar = idMehanicar;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public Mehanicar(Integer idMehanicar) {
        this.idMehanicar = idMehanicar;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_MEHANICAR")); 
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Uvek true, osim ako nemate logiku za isticanje naloga
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Uvek true, osim ako nemate logiku za zaključavanje naloga
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Uvek true, osim ako nemate logiku za isticanje šifre
    }

    @Override
    public boolean isEnabled() {
        return true; // Uvek true, osim ako nemate logiku za privremeno gašenje naloga
    }

}
