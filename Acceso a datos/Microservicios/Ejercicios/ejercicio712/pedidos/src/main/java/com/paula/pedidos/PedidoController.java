package com.paula.pedidos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public Pedido realizarPedido(@RequestBody AutorLibros autorLibros) {
        return pedidoService.realizarPedido(autorLibros);
    }

    @GetMapping("/{id}")
    public AutorLibros obtenerPedido(@PathVariable String id) {
        return pedidoService.obtenerPedido(id);
    }

}
