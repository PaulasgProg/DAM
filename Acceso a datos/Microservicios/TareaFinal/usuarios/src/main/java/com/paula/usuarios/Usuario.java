package com.paula.usuarios;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "usuario")
@NoArgsConstructor
@RequiredArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @NonNull
    @Column(length = 100)
    private String nombre;

    @NonNull
    @Column(length = 255)
    private String correo_electronico;

    @NonNull
    @Column(length = 255)
    private String direccion;

    @NonNull
    @Column(length = 255)
    private String contrasena;

}
