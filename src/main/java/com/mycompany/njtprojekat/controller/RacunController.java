package com.mycompany.njtprojekat.controller;

import com.mycompany.njtprojekat.dto.impl.RacunDto;
import com.mycompany.njtprojekat.servis.RacunServis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/racun")
@Tag(name = "Racun")
public class RacunController {

    private final RacunServis service;

    @Autowired
    public RacunController(RacunServis racunServis) {
        this.service = racunServis;
    }

    @GetMapping
    public ResponseEntity<List<RacunDto>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RacunDto> getById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // POST - novi račun sa stavkama
    @PostMapping
    @Operation(summary = "Kreiraj racun sa svim stavkama u jednoj transakciji")
    public ResponseEntity<RacunDto> create(@Valid @RequestBody @NotNull RacunDto dto) {
        try {
            RacunDto saved = service.create(dto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>("Racun obrisan", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Ažuriraj postojeći račun (i stavke)")
    public ResponseEntity<RacunDto> update(@PathVariable Integer id, @Valid @RequestBody @NotNull RacunDto dto) {
        try {
            RacunDto updated = service.update(id, dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
