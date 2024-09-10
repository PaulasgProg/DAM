package com.paula.libros;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroService {
    /*
     * Agregar nuevo libro
     * Actualizar libros.
     * Dado un identificador devolver únicamente el nombre del libro.
     * Dado un nombre de un libro devolver el identificador.
     */

    private final LibroRepository libroRepository;

    public Libro agregarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro actualizarLibro(Long id, Libro libroActi) {
        libroActi.setId(id);
        return libroRepository.save(libroActi);
    }

    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    public Libro obtenerLibroPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    public List<Libro> obtenerLibrosPorAutor(Integer autor) {
        return libroRepository.findByIdautor(autor);
    }

}
