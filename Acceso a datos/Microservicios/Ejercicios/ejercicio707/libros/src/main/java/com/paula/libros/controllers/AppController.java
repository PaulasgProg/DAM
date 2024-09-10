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

    @MutationMapping
    public String cargarDatos() {
        ArrayList<Libro> listaLibros = new ArrayList<>();
        ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
        ArrayList<Socio> listaSocios = new ArrayList<>();

        listaLibros.add(new Libro("El Señor de los Anillos", "J.R.R. Tolkien", 1954));
        listaLibros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", 1967));
        listaLibros.add(new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", 1997));
        listaLibros.add(new Libro("Libro X", "Autor X", 2002));
        listaLibros.add(new Libro("Libro Y", "Autora Y", 2007));

        for (Libro libro : listaLibros)
            libroService.guardar(libro);

        listaSocios.add(new Socio("Ana García", "Calle 321, Ciudad X", LocalDate.of(2022, 1, 1)));
        listaSocios.add(new Socio("Carlos Martínez", "Avenida 654, Ciudad Y", LocalDate.of(2022, 12, 15)));
        listaSocios.add(new Socio("Elena Pérez", "Calle 987, Ciudad Z", LocalDate.of(2022, 2, 10)));

        for (Socio socio : listaSocios)
            socioService.guardar(socio);

        listaPrestamos.add(new Prestamo(LocalDate.of(2022, 2, 1), LocalDate.of(2022, 3, 1)));
        listaPrestamos.add(new Prestamo(LocalDate.of(2022, 1, 15), LocalDate.of(2022, 2, 15)));
        listaPrestamos.add(new Prestamo(LocalDate.of(2022, 3, 10), LocalDate.of(2022, 4, 10)));
        listaPrestamos.add(new Prestamo(LocalDate.of(2022, 3, 10)));

        listaPrestamos.get(0).agregarLibro(listaLibros.get(0));
        listaPrestamos.get(0).agregarSocio(listaSocios.get(0));
        listaPrestamos.get(1).agregarLibro(listaLibros.get(1));
        listaPrestamos.get(1).agregarSocio(listaSocios.get(1));
        listaPrestamos.get(2).agregarLibro(listaLibros.get(2));
        listaPrestamos.get(2).agregarSocio(listaSocios.get(2));
        listaPrestamos.get(3).agregarLibro(listaLibros.get(4));
        listaPrestamos.get(3).agregarSocio(listaSocios.get(2));

        for (Prestamo prestamo : listaPrestamos)
            prestamoService.guardar(prestamo);

        return "Datos cargados correctamente";
    }

}
