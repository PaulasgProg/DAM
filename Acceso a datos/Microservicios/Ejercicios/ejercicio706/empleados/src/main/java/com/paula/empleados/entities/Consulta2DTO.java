package com.paula.empleados.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta2DTO {
    private String letra;
    private double salario;
    private Long idDepartamento;
}