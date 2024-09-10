package com.paula.comentarios;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comentarios")
@TypeAlias("ComentarioEntity")
public class ComentarioEntity {

    @Id
    private String _id;
    private int usuarioId;
    private int hotelId;
    private int reservaId;
    private double puntuacion;
    private String comentario;
    private String fechaCreacion;

}
