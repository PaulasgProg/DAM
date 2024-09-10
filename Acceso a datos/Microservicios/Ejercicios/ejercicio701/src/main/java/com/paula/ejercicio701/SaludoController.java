package com.paula.ejercicio701;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/saludo")
@RequiredArgsConstructor
public class SaludoController {

    private final SaludoService saludoService;

    @GetMapping
    public ResponseEntity<String> saludar(@RequestParam String nombre){
        String saludo=saludoService.saludarUsuario(nombre);
        return ResponseEntity.ok(saludo);
    }

}
