package com.paula.libros.dtos;

import java.io.Serializable;

import com.paula.libros.entities.Libro;

import lombok.Data;

@Data
public class Consulta1DTO implements Serializable {

    private int libro_id;
    private String titulo;
    private String autor;
    private int ano_publicacion;
    private String nombre;
    private String direccion;

    public Consulta1DTO(Libro l, String nombre, String direccion) {
        this.libro_id = l.getLibro_id();
        this.titulo = l.getTitulo();
        this.autor = l.getAutor();
        this.ano_publicacion = l.getAno_publicacion();
        this.nombre = nombre;
        this.direccion = direccion;
    }
}
