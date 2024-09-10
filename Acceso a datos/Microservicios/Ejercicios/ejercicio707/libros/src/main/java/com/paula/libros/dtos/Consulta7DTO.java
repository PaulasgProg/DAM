package com.paula.libros.dtos;

import java.io.Serializable;

import com.paula.libros.entities.Libro;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Consulta7DTO implements Serializable {

    private String titulo;
    private Long total;

}
