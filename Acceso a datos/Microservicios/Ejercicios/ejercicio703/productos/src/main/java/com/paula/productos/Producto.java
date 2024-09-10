package com.paula.productos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //genera metodos automaticamente @entity es parte del framework de persistencia JPA
        // (Java Persistence API). Se utiliza para marcar una clase como una entidad persistente, 
        //lo que significa que los objetos de esa clase se pueden almacenar y recuperar de una base de datos utilizando JPA.
@AllArgsConstructor// Genera un constructor que acepta todos los campos de la clase como argumentos
@NoArgsConstructor//Genera un constructor sin argumentos que inicializa todos los campos de la clase con sus valores predeterminados.
public class Producto {

    private Long id;
    private String nombre;
    private Double precio;
}
