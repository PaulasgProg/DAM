package com.paula.libros;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/libro")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;

    @PostMapping
    public ResponseEntity<Libro> agregarLibro(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.agregarLibro(libro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libroActi) {
        return ResponseEntity.ok(libroService.actualizarLibro(id, libroActi));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.obtenerLibroPorId(id));

    }

    @GetMapping("/portitulo/{titulo}")
    public ResponseEntity<Long> obtenerLibroPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(libroService.obtenerLibroPorTitulo(titulo).getId());
    }

    @GetMapping("/porautor/{idAutor}")
    public ResponseEntity<List<Libro>> obtenerLibrosPorAutor(@PathVariable String idAutor) {
        return ResponseEntity.ok(libroService.obtenerLibrosPorAutor(Integer.parseInt(idAutor)));
    }

}
