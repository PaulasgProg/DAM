package com.paula.libros.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Consulta9DTO {

    private String titulo;
    private LocalDate fecha_prestamo;
    private LocalDate fecha_devolucion;
    private String nombre;

}
