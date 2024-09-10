package com.paula.comentarios.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {

    private String nombreHotel;
    private int reservaId;
    private double puntuacion;
    private String comentario;

}
