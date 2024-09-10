package com.paula.productos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
public class ProductoService {

    private final List<Producto> productos=new ArrayList<>();
    private Long idCounter=1L;

    public List<Producto> listarProductos(){
        return productos;
    }

    public Producto obtenerProducto(Long id){
        return productos.stream()//para convertir la lista de productos (productos) en un flujo de elementos.
        .filter(producto -> producto.getId().equals(id))
            .findFirst().orElse(null);
            //para devolver el primer producto encontrado, o null si no se encuentra ningún producto con el id proporcionado.
    }

    public void agregarProducto(Producto producto){
        producto.setId(idCounter++);
        productos.add(producto);
    }

    public void eliminarProducto(Long id){
        Iterator<Producto> iterator=productos.iterator();
        while (iterator.hasNext()) {
            Producto producto=iterator.next();
            if (producto.getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }
}
