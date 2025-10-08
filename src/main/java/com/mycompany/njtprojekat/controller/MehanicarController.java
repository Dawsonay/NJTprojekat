/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.njtprojekat.controller;

import com.mycompany.njtprojekat.dto.impl.MehanicarDto;
import com.mycompany.njtprojekat.servis.MehanicarServis;
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
@RequestMapping("/api/mehanicar")
public class MehanicarController {

    private final MehanicarServis mehanicarServis;

    public MehanicarController(MehanicarServis mehanicarServis) {
        this.mehanicarServis = mehanicarServis;
    }

    @GetMapping
    @Operation(summary = "Retrieve all Mehanicar entities. ")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = MehanicarDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<MehanicarDto>> getAll() {
        return new ResponseEntity<>(mehanicarServis.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MehanicarDto> getById(
            @NotNull(message = "Ne bi trebao da bude null ili empty")
            @PathVariable(value = "id") Integer id
    ) {
        try {
            return new ResponseEntity<>(mehanicarServis.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska pri trazenju mehanicara");
        }
    }

    @PostMapping
    @Operation(summary = "Create a new Mehanicar entity")
    @ApiResponse(responseCode = "201", description = "Mehanicar successfully created",
            content = {
                @Content(schema = @Schema(implementation = MehanicarDto.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<MehanicarDto> addMehanicar(
            @Valid @RequestBody MehanicarDto mehanicarDto
    ) {
        try {
            MehanicarDto saved = mehanicarServis.create(mehanicarDto);

            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greška pri kreiranju mehaničara: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
        try {
            mehanicarServis.deleteById(id);
            return new ResponseEntity<>("Mehanicar uspesno obrisan",HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>("Ne postoji mehanicar sa id: " + id ,HttpStatus.NOT_FOUND );

        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Azuriranje postojeceg mehanicara")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = MehanicarDto.class), mediaType = "application/json")})
    public ResponseEntity<MehanicarDto> updateMehanicar(
            @PathVariable Integer id, 
            @Valid @RequestBody MehanicarDto mehanicarDto
    ){
        try{
            mehanicarDto.setIdMehanicar(id);
            MehanicarDto updated = mehanicarServis.update(mehanicarDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Desila se greska prilikom update mehanicara");
        }
    }
    
}
