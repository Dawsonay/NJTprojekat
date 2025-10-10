/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.controller;

import com.mycompany.njtprojekat.dto.impl.RadniNalogDto;
import com.mycompany.njtprojekat.servis.RadniNalogServis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/radninalog")
@Tag(name = "RadniNalog")
public class RadniNalogController {
     private final RadniNalogServis service;

    @Autowired
    public RadniNalogController(RadniNalogServis racunServis) {
        this.service = racunServis;
    }

    @GetMapping
    public ResponseEntity<List<RadniNalogDto>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RadniNalogDto> getById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // POST - novi račun sa stavkama
    @PostMapping
    @Operation(summary = "Kreiraj radni nalog sa svim stavkama u jednoj transakciji")
    public ResponseEntity<RadniNalogDto> create(@Valid @RequestBody @NotNull RadniNalogDto dto) {
        try {
            RadniNalogDto saved = service.create(dto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>("Radni nalog obrisan", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Ažuriraj postojeći radni nalog (i stavke)")
    public ResponseEntity<RadniNalogDto> update(@PathVariable Integer id, @Valid @RequestBody @NotNull RadniNalogDto dto) {
        try {
            RadniNalogDto updated = service.update(id, dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
