package com.paula.libros.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paula.libros.dtos.Consulta1DTO;
import com.paula.libros.dtos.Consulta4DTO;
import com.paula.libros.dtos.Consulta6DTO;
import com.paula.libros.dtos.Consulta7DTO;
import com.paula.libros.dtos.Consulta8DTO;
import com.paula.libros.dtos.Consulta9DTO;
import com.paula.libros.entities.Libro;
import com.paula.libros.entities.Prestamo;
import com.paula.libros.entities.Socio;
import com.paula.libros.services.LibroService;
import com.paula.libros.services.PrestamoService;
import com.paula.libros.services.SocioService;

import lombok.RequiredArgsConstructor;

@Controller
public class AppController {
    @Autowired
    private PrestamoService prestamoService;
    @Autowired
    private LibroService libroService;
    @Autowired
    private SocioService socioService;

    @QueryMapping
    public List<Consulta1DTO> consulta1() {
        return libroService.consulta1();
    }

    @QueryMapping
    public List<Libro> consulta2() {
        return libroService.consulta2();
    }

    @QueryMapping
    public List<Consulta7DTO> consulta3() {
        return socioService.consulta3();
    }

    @QueryMapping
    public List<Consulta4DTO> consulta4() {
        return socioService.consulta4();
    }

    @QueryMapping
    public List<Libro> consulta5() {
        return libroService.consulta5();
    }

    @QueryMapping
    public List<Consulta6DTO> consulta6() {
        return socioService.consulta6();
    }

    @QueryMapping
    public List<Consulta7DTO> consulta7() {
        return libroService.consulta7();
    }

    @QueryMapping
    public List<Consulta8DTO> consulta8(@Argument String fecha1, @Argument String fecha2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate f1 = LocalDate.parse(fecha1, formatter);
        LocalDate f2 = LocalDate.parse(fecha2, formatter);
        return libroService.consulta8(f1, f2);
    }

    @QueryMapping
    public List<Consulta9DTO> consulta9(@Argument Integer idsocio) {
        return libroService.consulta9(idsocio);
    }

}
