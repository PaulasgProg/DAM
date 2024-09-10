package com.paula.libros.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Consulta8DTO implements Serializable {

    private String titulo;
    private LocalDate fecha_prestamo;
    private LocalDate fecha_devolucion;

}
