/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.controller;

import com.mycompany.njtprojekat.dto.impl.VrstaVozilaDto;
import com.mycompany.njtprojekat.servis.VoziloServis;
import com.mycompany.njtprojekat.servis.VrstaVozilaServis;
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
@RequestMapping("/api/vrstavozila")
public class VrstaVozilaController {
    private final VrstaVozilaServis servis;

    public VrstaVozilaController(VrstaVozilaServis servis) {
        this.servis = servis;
    }
    
    @GetMapping
    @Operation(summary ="Retrieve all Servis entities. ")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = VrstaVozilaDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<VrstaVozilaDto>> getAll(){
        return new ResponseEntity<>(servis.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VrstaVozilaDto> getById(
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
    @Operation(summary = "Create a new Vrsta vozila entity")
    @ApiResponse(responseCode = "201", description = "Vrsta vozila successfully created",
            content = {
                @Content(schema = @Schema(implementation = VrstaVozilaDto.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<VrstaVozilaDto> addVrstaVozila(
            @Valid @RequestBody VrstaVozilaDto vrstaVozilaDto
    ) {
        try {
            VrstaVozilaDto saved = servis.create(vrstaVozilaDto);

            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska pri kreiranju vrsta vozila: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
        try {
            servis.deleteById(id);
            return new ResponseEntity<>("Vrsta vozila uspesno obrisan",HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>("Ne postoji vrsta vozila sa id: " + id ,HttpStatus.NOT_FOUND );

        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Azuriranje postojece vrste vozila")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VrstaVozilaDto.class), mediaType = "application/json")})
    public ResponseEntity<VrstaVozilaDto> updateVrstaVozila(
            @PathVariable Integer id, 
            @Valid @RequestBody VrstaVozilaDto vrstaVozilaDto
    ){
        try{
            vrstaVozilaDto.setIdVrsta(id);
            VrstaVozilaDto updated = servis.update(vrstaVozilaDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Desila se greska prilikom update vrsta vozila");
        }
    }
}
