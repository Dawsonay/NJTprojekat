///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
//package com.mycompany.njtprojekat.controller;
//
//
//
//import com.mycompany.njtprojekat.dto.impl.StavkaRacunaDto;
//import com.mycompany.njtprojekat.servis.StavkaRacunaServis;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/stavkaracuna")
//@CrossOrigin(origins = "*")
//public class StavkaRacunaController {
//
//    private final StavkaRacunaServis stavkaRacunaServis;
//
//    @Autowired
//    public StavkaRacunaController(StavkaRacunaServis stavkaRacunaServis) {
//        this.stavkaRacunaServis = stavkaRacunaServis;
//    }
//
//    // ✅ Vrati sve stavke
//    @GetMapping
//    public List<StavkaRacunaDto> getAll() {
//        return stavkaRacunaServis.findAll();
//    }
//
//    // ✅ Vrati jednu stavku po ID-u
//    @GetMapping("/{rb}")
//    public StavkaRacunaDto getOne(@PathVariable Integer rb) {
//        return stavkaRacunaServis.findById(rb);
//    }
//
//    // ✅ Dodaj novu stavku
//    @PostMapping
//    public void add(@RequestBody StavkaRacunaDto stavkaRacunaDto) {
//        stavkaRacunaServis.save(stavkaRacunaDto);
//    }
//
//    // ✅ Ažuriraj postojeću stavku
//    @PutMapping("/{rb}")
//    public void update(@PathVariable Integer rb, @RequestBody StavkaRacunaDto stavkaRacunaDto) {
//        stavkaRacunaDto.setRb(rb);
//        stavkaRacunaServis.update(stavkaRacunaDto);
//    }
//
//    // ✅ Obrisi stavku
//    @DeleteMapping("/{rb}")
//    public void delete(@PathVariable Integer rb) {
//        stavkaRacunaServis.delete(rb);
//    }
//}
