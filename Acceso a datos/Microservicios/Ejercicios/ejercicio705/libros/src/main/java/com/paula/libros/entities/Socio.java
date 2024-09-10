package com.paula.libros.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "socios")
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "listaPrestamos")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int socio_id;

    @NonNull
    @Column(name = "nombre", length = 50)
    private String nombre;

    @NonNull
    @Column(name = "direccion", length = 100)
    private String direccion;

    @NonNull
    @Column(name = "fecha_inscripcion", columnDefinition = "DATE")
    private LocalDate fecha_inscripcion;

    @OneToMany(mappedBy = "socio")
    private List<Prestamo> listaPrestamos = new ArrayList<>();

    public void agregarPrestamo(Prestamo prestamo) {
        this.listaPrestamos.add(prestamo);
    }

}
