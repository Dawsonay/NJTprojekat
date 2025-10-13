/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.dto.impl;

import com.mycompany.njtprojekat.dto.Dto;


public class MehanicarUpdateResponse implements Dto {
    private MehanicarDto mehanicar;
    private String token; 

    public MehanicarUpdateResponse(MehanicarDto mehanicar, String token) {
        this.mehanicar = mehanicar;
        this.token = token;
    }

    public MehanicarDto getMehanicar() {
        return mehanicar;
    }

    public void setMehanicar(MehanicarDto mehanicar) {
        this.mehanicar = mehanicar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

