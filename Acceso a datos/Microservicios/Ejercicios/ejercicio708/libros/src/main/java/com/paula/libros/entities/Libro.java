package com.paula.libros.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "libros")
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "listaPrestamos")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "libro_id")
    private int libro_id;

    @NonNull
    @Column(name = "titulo", length = 100)
    private String titulo;

    @NonNull
    @Column(name = "autor", length = 50)
    private String autor;

    @NonNull
    @Column(name = "ano_publicacion")
    private int ano_publicacion;

    @OneToMany(mappedBy = "libro")
    private List<Prestamo> listaPrestamos = new ArrayList<>();

    public void agregarPrestamo(Prestamo prestamo) {
        this.listaPrestamos.add(prestamo);
    }
}
