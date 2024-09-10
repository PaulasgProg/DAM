package com.paula.libros.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "prestamos")
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "libro", "socio" })
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_prestamo;

    @NonNull
    @Column(columnDefinition = "DATE")
    private LocalDate fecha_prestamo;

    @NonNull
    @Column(columnDefinition = "DATE")
    private LocalDate fecha_devolucion;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "socio_id")
    private Socio socio;

    public void agregarLibro(Libro lib) {
        this.libro = lib;
        libro.agregarPrestamo(this);
    }

    public void agregarSocio(Socio soc) {
        this.socio = soc;
        socio.agregarPrestamo(this);
    }

    public Prestamo(LocalDate of) {
        this.fecha_prestamo = of;
    }

}
