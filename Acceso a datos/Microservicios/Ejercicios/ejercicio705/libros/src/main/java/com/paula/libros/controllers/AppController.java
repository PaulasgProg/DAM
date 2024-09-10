package com.paula.libros.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/libros")
public class AppController {
    private final PrestamoService prestamoService;
    private final LibroService libroService;
    private final SocioService socioService;

    @GetMapping("/consulta1")
    public ResponseEntity<List<Consulta1DTO>> consulta1() {
        return ResponseEntity.ok(libroService.consulta1());
    }

    @GetMapping("/consulta2")
    public ResponseEntity<List<Libro>> consulta2() {
        return ResponseEntity.ok(libroService.consulta2());
    }

    @GetMapping("/consulta3")
    public ResponseEntity<List<Consulta7DTO>> consulta3() {
        return ResponseEntity.ok(socioService.consulta3());
    }

    @GetMapping("/consulta4")
    public ResponseEntity<List<Consulta4DTO>> consulta4() {
        return ResponseEntity.ok(socioService.consulta4());
    }

    @GetMapping("/consulta5")
    public ResponseEntity<List<Libro>> consulta5() {
        return ResponseEntity.ok(libroService.consulta5());
    }

    @GetMapping("/consulta6")
    public ResponseEntity<List<Consulta6DTO>> consulta6() {
        return ResponseEntity.ok(socioService.consulta6());
    }

    @GetMapping("/consulta7")
    public ResponseEntity<List<Consulta7DTO>> consulta7() {
        return ResponseEntity.ok(libroService.consulta7());
    }

    @GetMapping("/consulta8/{fecha1}-{fecha2}")
    public ResponseEntity<List<Consulta8DTO>> consulta8(@PathVariable String fecha1, @PathVariable String fecha2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate f1 = LocalDate.parse(fecha1, formatter);
        LocalDate f2 = LocalDate.parse(fecha2, formatter);
        return ResponseEntity.ok(libroService.consulta8(f1, f2));
    }

    @GetMapping("/consulta9/{idsocio}")
    public ResponseEntity<List<Consulta9DTO>> consulta9(@PathVariable Integer idsocio) {
        return ResponseEntity.ok(libroService.consulta9(idsocio));
    }

    @PatchMapping("/cargar")
    public ResponseEntity<String> cargarDatos() {
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

        return ResponseEntity.ok("Datos cargados correctamente");
    }

}
