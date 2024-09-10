package com.paula.autores;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @GetMapping("/{id}")
    public ResponseEntity<String> obtenerNombrePorId(@PathVariable Long id) {
        return ResponseEntity.ok(autorService.obtenerNombrePorId(id).getNombre());
    }

    @GetMapping("/pornombre/{nombre}")
    public ResponseEntity<Long> findAutorPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(autorService.findAutorPorNombre(nombre).getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizarAutor(@PathVariable Long id, @RequestBody Autor autorActualizado) {
        return ResponseEntity.ok(autorService.actualizarAutor(id, autorActualizado));
    }

    @PostMapping
    public ResponseEntity<Autor> agregarAutor(@RequestBody Autor autorNuevAutor) {
        return ResponseEntity.ok(autorService.agregarAutor(autorNuevAutor));
    }
}
