package com.paula.comentarios.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioInput {
    private String nombreHotel;
    private int reservaId;
    private double puntuacion;
    private String comentario;

}
