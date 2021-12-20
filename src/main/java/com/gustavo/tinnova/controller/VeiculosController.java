package com.gustavo.tinnova.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class VeiculosController {

    @GetMapping("/hello-world")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/veiculos")
    public ResponseEntity<String> getVeiculos() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/veiculos/find")
    public ResponseEntity<String> getEncontrarVeiculo(@RequestParam("q") String termo) {
        return ResponseEntity.ok("Hello World!"+termo);
    }

    @GetMapping("/veiculos/{id}")
    public ResponseEntity<String> getDetalheVeiculo() {
        return ResponseEntity.ok("Hello World!");
    }

    @PostMapping("/veiculos")
    public ResponseEntity<String> postVeiculos() {
        return ResponseEntity.ok("Hello World!");
    }

    @PutMapping("/veiculos/{id}")
    public ResponseEntity<String> putAtualizarVeiculo() {
        return ResponseEntity.ok("Hello World!");
    }

    @PatchMapping("/veiculos/{id}")
    public ResponseEntity<String> patchAtualizarVeiculo() {
        return ResponseEntity.ok("Hello World!");
    }

    @DeleteMapping("/veiculos/{id}")
    public ResponseEntity<String> deleteVeiculo() {
        return ResponseEntity.ok("Hello World!");
    }
}
