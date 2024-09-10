package com.paula.pedidos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "pedido")
public class Pedido {
    @Id
    private String id;
    @NonNull
    private Long idAutor;
    @NonNull
    private List<Long> librosIds;
    @NonNull
    private LocalDate fechaPedido;
}
