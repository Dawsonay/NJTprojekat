package com.mycompany.njtprojekat.controller;

import com.mycompany.njtprojekat.dto.impl.MehanicarDto;
import com.mycompany.njtprojekat.dto.impl.MehanicarUpdateResponse;
import com.mycompany.njtprojekat.servis.MehanicarServis;
import com.mycompany.njtprojekat.security.jwt.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final JwtUtil jwtUtil;
    private final UserDetailsService mehanicarUserDetailsService;

    public MehanicarController(
            MehanicarServis mehanicarServis,
            JwtUtil jwtUtil,
            UserDetailsService mehanicarUserDetailsService
    ) {
        this.mehanicarServis = mehanicarServis;
        this.jwtUtil = jwtUtil;
        this.mehanicarUserDetailsService = mehanicarUserDetailsService;
    }

    @GetMapping
    @Operation(summary = "Prikazuje sve mehanicare")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = MehanicarDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<MehanicarDto>> getAll() {
        return ResponseEntity.ok(mehanicarServis.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MehanicarDto> getById(
            @NotNull(message = "ID ne sme biti null") @PathVariable Integer id
    ) {
        try {
            return ResponseEntity.ok(mehanicarServis.findById(id));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mehanicar nije pronadjen.");
        }
    }

    @PostMapping
    @Operation(summary = "Kreira novog mehanicara")
    @ApiResponse(responseCode = "201", description = "Mehanicar uspesno kreiran",
            content = @Content(schema = @Schema(implementation = MehanicarDto.class)))
    public ResponseEntity<MehanicarDto> addMehanicar(@Valid @RequestBody MehanicarDto mehanicarDto) {
        try {
            MehanicarDto saved = mehanicarServis.create(mehanicarDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska pri kreiranju: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            mehanicarServis.deleteById(id);
            return ResponseEntity.ok("Mehanicar uspesno obrisan.");
        } catch (Exception ex) {
            return new ResponseEntity<>("Ne postoji mehanicar sa ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Azurira postojecu evidenciju mehanicara")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = MehanicarUpdateResponse.class), mediaType = "application/json")
    })
    public ResponseEntity<MehanicarUpdateResponse> updateMehanicar(
            @PathVariable Integer id,
            @Valid @RequestBody MehanicarDto mehanicarDto
    ) {
        try {
            mehanicarDto.setIdMehanicar(id);

            MehanicarDto updated = mehanicarServis.update(mehanicarDto);

            String token = null;

            if (mehanicarDto.getPassword() != null && !mehanicarDto.getPassword().isBlank()) {
                UserDetails userDetails = mehanicarUserDetailsService.loadUserByUsername(updated.getUsername());
                token = jwtUtil.generateToken(userDetails);
            }

            MehanicarUpdateResponse response = new MehanicarUpdateResponse(updated, token);
            return ResponseEntity.ok(response);

        } catch (ResponseStatusException e) {
            throw e; 
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Greska prilikom azuriranja: " + ex.getMessage());
        }
    }
}
