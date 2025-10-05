/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.njtprojekat.controller;

import com.mycompany.njtprojekat.dto.impl.MehanicarDto;
import com.mycompany.njtprojekat.dto.impl.VoziloDto;
import com.mycompany.njtprojekat.servis.MehanicarServis;
import com.mycompany.njtprojekat.servis.VoziloServis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/vozilo")
public class VoziloController {
    private final VoziloServis voziloServis;

    public VoziloController(VoziloServis voziloServis) {
        this.voziloServis = voziloServis;
    }
    
    @GetMapping
    @Operation(summary ="Retrieve all Servis entities. ")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = MehanicarDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<VoziloDto>> getAll(){
        return new ResponseEntity<>(voziloServis.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VoziloDto> getById(
            @NotNull(message = "Ne bi trebao da bude null ili empty")
            @PathVariable(value="id") Integer id
    ){
        try{
            return new ResponseEntity<>(voziloServis.findById(id), HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nema toga");
        }
    }
}
