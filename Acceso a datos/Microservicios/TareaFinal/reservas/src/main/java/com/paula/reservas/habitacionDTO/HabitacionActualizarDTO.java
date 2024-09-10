package com.paula.reservas.habitacionDTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HabitacionActualizarDTO {

    private int id;
    private int numeroHabitacion;
    private String tipo;
    private BigDecimal precio;
    private int idHotel;
    private Boolean disponible;
}
