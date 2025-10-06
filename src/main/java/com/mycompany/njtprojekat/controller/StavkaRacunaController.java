/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.controller;

import com.mycompany.njtprojekat.dto.impl.StavkaRacunaDto;
import com.mycompany.njtprojekat.servis.RacunServis;
import com.mycompany.njtprojekat.servis.StavkaRacunaServis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/stavkaracuna")
public class StavkaRacunaController {
     private final StavkaRacunaServis servis;

    public StavkaRacunaController(StavkaRacunaServis servis) {
        this.servis = servis;
    }

    @GetMapping
    @Operation(summary = "Retrieve all Stavka racuna entities")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = StavkaRacunaDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<StavkaRacunaDto>> getAll() {
        return ResponseEntity.ok(servis.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a Stavka racuna by its ID")
    public ResponseEntity<StavkaRacunaDto> getById(
            @NotNull(message = "ID ne sme biti null")
            @PathVariable Integer id
    ) {
        try {
            StavkaRacunaDto stavkaRacuna = servis.findById(id);
            return ResponseEntity.ok(stavkaRacuna);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stavka racuna sa ID " + id + " nije pronadjen");
        }
    }

    @PostMapping
    @Operation(summary = "Create a new Stavka racuna entity")
    @ApiResponse(responseCode = "201", description = "Stavka racuna successfully created",
            content = {@Content(schema = @Schema(implementation = StavkaRacunaDto.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<StavkaRacunaDto> addStavkaRacuna(@Valid @RequestBody StavkaRacunaDto stavkaRacunaDto) {
        try {
            StavkaRacunaDto saved = servis.create(stavkaRacunaDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greška pri kreiranju stavke racuna: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Stavka racuna")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = StavkaRacunaDto.class), mediaType = "application/json")
    })
    @ApiResponse(responseCode = "404", description = "Stavka racuna not found")
    public ResponseEntity<StavkaRacunaDto> update(
            @PathVariable Integer id,
            @Valid @RequestBody StavkaRacunaDto stavkaRacunaDto
    ) {
        try {
            stavkaRacunaDto.setRb(id);
            StavkaRacunaDto updated = servis.update(stavkaRacunaDto);
            return ResponseEntity.ok(updated);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stavka racuna sa ID " + id + " nije pronadjen za update");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Stavka racuna by its ID")
    @ApiResponse(responseCode = "200", description = "Stavka racuna successfully deleted")
    @ApiResponse(responseCode = "404", description = "Stavka racuna not found")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            servis.deleteById(id);
            return ResponseEntity.ok("Stavka racuna sa ID " + id + " je uspesno obrisan");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stavka racuna sa ID " + id + " nije pronadjen");
        }
    }
}
