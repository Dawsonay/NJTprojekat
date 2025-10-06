/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.controller;

import com.mycompany.njtprojekat.dto.impl.MehanicarDto;
import com.mycompany.njtprojekat.dto.impl.UslugaDto;
import com.mycompany.njtprojekat.dto.impl.UslugaDto;
import com.mycompany.njtprojekat.servis.UslugaServis;
import com.mycompany.njtprojekat.servis.VoziloServis;
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
@RequestMapping("/api/usluga")
public class UslugaController {
    private final UslugaServis servis;

    public UslugaController(UslugaServis servis) {
        this.servis = servis;
    }
    
    @GetMapping
    @Operation(summary ="Retrieve all Usluga entities. ")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = UslugaDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<UslugaDto>> getAll(){
        return new ResponseEntity<>(servis.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UslugaDto> getById(
            @NotNull(message = "Ne bi trebao da bude null ili empty")
            @PathVariable(value="id") Integer id
    ){
        try{
            return new ResponseEntity<>(servis.findById(id), HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nema toga");
        }
    }
    
    @PostMapping
    @Operation(summary = "Create a new Usluga entity")
    @ApiResponse(responseCode = "201", description = "Usluga successfully created",
            content = {
                @Content(schema = @Schema(implementation = UslugaDto.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<UslugaDto> addUsluga(
            @Valid @RequestBody UslugaDto voziloDto
    ) {
        try {
            UslugaDto saved = servis.create(voziloDto);

            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greška pri kreiranju usluge: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
        try {
            servis.deleteById(id);
            return new ResponseEntity<>("Usluga uspesno obrisana",HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>("Ne postoji vozilo sa id: " + id ,HttpStatus.NOT_FOUND );

        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Azuriranje postojece usluge")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = UslugaDto.class), mediaType = "application/json")})
    public ResponseEntity<UslugaDto> updateUsluga(
            @PathVariable Integer id, 
            @Valid @RequestBody UslugaDto uslugaDto
    ){
        try{
            uslugaDto.setIdUsluga(id);
            UslugaDto updated = servis.update(uslugaDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Desila se greska prilikom update usluge");
        }
    }
}
