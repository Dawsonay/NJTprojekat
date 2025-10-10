package com.mycompany.njtprojekat.controller;

import com.mycompany.njtprojekat.dto.impl.JwtResponse;
import com.mycompany.njtprojekat.dto.impl.LoginRequest;
import com.mycompany.njtprojekat.dto.impl.MehanicarDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.security.jwt.JwtUtil;
import com.mycompany.njtprojekat.mapper.impl.MehanicarMapper;
import com.mycompany.njtprojekat.servis.MehanicarServis;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final MehanicarServis mehanicarService;
    private final MehanicarMapper mehanicarMapper;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          MehanicarServis mehanicarService,
                          MehanicarMapper mehanicarMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.mehanicarService = mehanicarService;
        this.mehanicarMapper = mehanicarMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            // 🔹 Autentifikacija korisnika
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // 🔹 Ako je uspešno, uzmi UserDetails i generiši JWT
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails);

            // 🔹 Pronađi mehaničara u bazi po username-u
            Mehanicar mehanicar = mehanicarService.findByUsername(userDetails.getUsername());
            MehanicarDto mehanicarDto = mehanicarMapper.toDto(mehanicar);

            // 🔹 Vrati token + podatke o korisniku (DTO)
            return ResponseEntity.ok(new JwtResponse(jwt, mehanicarDto));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("❌ Pogrešno korisničko ime ili lozinka.");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("⚠️ Greška prilikom prijave: " + ex.getMessage());
        }
    }
}
