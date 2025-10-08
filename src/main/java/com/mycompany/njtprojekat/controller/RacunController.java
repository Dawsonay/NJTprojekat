/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.controller;

import com.mycompany.njtprojekat.dto.impl.RacunDto;
import com.mycompany.njtprojekat.servis.RacunServis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
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
@RequestMapping("/api/racun")
public class RacunController {

   private final RacunServis servis;

    public RacunController(RacunServis servis) {
        this.servis = servis;
    }

    @GetMapping
    @Operation(summary = "Retrieve all Racun entities")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = RacunDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<RacunDto>> getAll() {
        return ResponseEntity.ok(servis.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a Racun by its ID")
    public ResponseEntity<RacunDto> getById(
            @NotNull(message = "ID ne sme biti null")
            @PathVariable Integer id
    ) {
        try {
            RacunDto racun = servis.findById(id);
            return ResponseEntity.ok(racun);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Racun sa ID " + id + " nije pronadjen");
        }
    }

    @PostMapping
    @Operation(summary = "Create a new Racun entity")
    @ApiResponse(responseCode = "201", description = "Racun successfully created",
            content = {@Content(schema = @Schema(implementation = RacunDto.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<RacunDto> addRacun(@Valid @RequestBody RacunDto racunDto) {
        try {
            RacunDto saved = servis.create(racunDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greška pri kreiranju racuna: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Racun")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = RacunDto.class), mediaType = "application/json")
    })
    @ApiResponse(responseCode = "404", description = "Racun not found")
    public ResponseEntity<RacunDto> update(
            @PathVariable Integer id,
            @Valid @RequestBody RacunDto racunDto
    ) {
        try {
            racunDto.setIdRacun(id);
            RacunDto updated = servis.update(racunDto);
            return ResponseEntity.ok(updated);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Racun sa ID " + id + " nije pronadjen za update");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Racun by its ID")
    @ApiResponse(responseCode = "200", description = "Racun successfully deleted")
    @ApiResponse(responseCode = "404", description = "Racun not found")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            servis.deleteById(id);
            return ResponseEntity.ok("Racun sa ID " + id + " je uspesno obrisan");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Racun sa ID " + id + " nije pronadjen");
        }
    }
}
