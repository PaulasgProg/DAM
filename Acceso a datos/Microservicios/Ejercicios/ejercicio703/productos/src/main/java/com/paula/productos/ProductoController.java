package com.paula.productos;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService){
        this.productoService=productoService;
    }

    @PostMapping
    public ResponseEntity<String> agregarProducto(@RequestBody Producto producto){
        productoService.agregarProducto(producto);
        return ResponseEntity.ok().body("Producto añadido");
    }

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos(){
        List<Producto> lista=productoService.listarProductos();
        return ResponseEntity.ok(lista);
    }

    //VARIABLE DE GETMAPPING Y LA DEL PARAMETRO DEL MÉTODO TIENE QUE SER IGUALEES
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id){
        Producto producto=productoService.obtenerProducto(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        Producto producto=productoService.obtenerProducto(id);
        if (producto!=null) {
            productoService.eliminarProducto(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
