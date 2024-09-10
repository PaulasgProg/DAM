package com.paula.reservas.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "habitacion")
@NoArgsConstructor
@RequiredArgsConstructor
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habitacion_id")
    private int habitacionId;

    @NonNull
    @Column(name = "numero_habitacion")
    private int numeroHabitacion;

    @NonNull
    @Column(length = 50)
    private String tipo;

    @NonNull
    @Column(length = 10, precision = 2)
    private BigDecimal precio;

    @NonNull
    private Boolean disponible;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL)
    private List<Reserva> listaReservas = new ArrayList<>();

    public Habitacion(int numeroHabitacion, String tipo, BigDecimal precio, Hotel hotel, Boolean disponible) {
        this.precio = precio;
        this.numeroHabitacion = numeroHabitacion;
        this.tipo = tipo;
        this.hotel = hotel;
        this.disponible = disponible;
    }
}
