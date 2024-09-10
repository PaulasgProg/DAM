package com.paula.pedidos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AutorLibros {
    private String nombreAutor;
    private List<String> nombreLibros;
}
