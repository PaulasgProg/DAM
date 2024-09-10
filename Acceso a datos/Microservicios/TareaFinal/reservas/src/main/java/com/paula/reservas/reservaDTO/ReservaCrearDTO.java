package com.paula.reservas.reservaDTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservaCrearDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int habitacionId;

}
