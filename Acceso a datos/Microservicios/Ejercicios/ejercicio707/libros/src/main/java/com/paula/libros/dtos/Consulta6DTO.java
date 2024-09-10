package com.paula.libros.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Consulta6DTO {
    private int idSocio;
    private String nombre;
    private Long total;
}