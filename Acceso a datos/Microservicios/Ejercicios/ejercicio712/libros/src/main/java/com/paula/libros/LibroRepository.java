package com.paula.libros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Libro findByTitulo(String titulo);

    List<Libro> findByIdautor(Integer autor);
}
