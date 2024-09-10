package com.paula.reservas.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "reserva")
@NoArgsConstructor
@RequiredArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id")
    private int reservaId;

    @NonNull
    @Column(name = "usuario_id")
    private int usuarioId;

    @NonNull
    @Column(name = "fecha_inicio", columnDefinition = "DATE")
    private LocalDate fechaInicio;

    @NonNull
    @Column(name = "fecha_fin", columnDefinition = "DATE")
    private LocalDate fechaFin;

    @NonNull
    @Column(length = 20)
    private String estado;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

}
