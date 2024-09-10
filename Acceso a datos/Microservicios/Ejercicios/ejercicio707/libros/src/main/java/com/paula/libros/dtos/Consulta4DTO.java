package com.paula.libros.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Consulta4DTO {
    private String nombre;
    private LocalDate fechDevolucion;
}
