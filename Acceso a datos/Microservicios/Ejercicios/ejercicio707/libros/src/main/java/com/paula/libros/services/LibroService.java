package com.paula.libros.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.paula.libros.dtos.Consulta1DTO;
import com.paula.libros.dtos.Consulta7DTO;
import com.paula.libros.dtos.Consulta8DTO;
import com.paula.libros.dtos.Consulta9DTO;
import com.paula.libros.entities.Libro;
import com.paula.libros.repositories.LibroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;

    public void guardar(Libro libro) {
        libroRepository.save(libro);
    }

    public List<Consulta1DTO> consulta1() {
        List<Object[]> resultado = libroRepository.consulta1();
        return resultado.stream().map(obj -> new Consulta1DTO((Libro) obj[0], (String) obj[1], (String) obj[2]))
                .collect(Collectors.toList());
    }

    public List<Libro> consulta2() {
        List<Libro> resultado = libroRepository.consulta2();
        return resultado;
    }

    public List<Libro> consulta5() {
        return libroRepository.consulta5();
    }

    public List<Consulta7DTO> consulta7() {
        List<Object[]> res = libroRepository.consulta7();
        return res.stream().map(obj -> new Consulta7DTO((String) obj[0], (Long) obj[1])).collect(Collectors.toList());
    }

    public List<Consulta8DTO> consulta8(LocalDate fecha1, LocalDate fecha2) {
        List<Object[]> res = libroRepository.consulta8(fecha1, fecha2);
        return res.stream().map(obj -> new Consulta8DTO((String) obj[0], (LocalDate) obj[1], (LocalDate) obj[2]))
                .collect(Collectors.toList());
    }

    public List<Consulta9DTO> consulta9(Integer socio) {
        List<Object[]> res = libroRepository.consulta9(socio);
        return res.stream()
                .map(obj -> new Consulta9DTO((String) obj[0], (LocalDate) obj[1], (LocalDate) obj[2], (String) obj[3]))
                .collect(Collectors.toList());
    }
}
