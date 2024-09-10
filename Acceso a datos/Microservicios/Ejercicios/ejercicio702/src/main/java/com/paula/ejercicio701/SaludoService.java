package com.paula.ejercicio701;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor se usa cuando hay DI (autowired)
public class SaludoService {

    public String saludarUsuario(String nombre){
        return "Hola, "+nombre+"!";
    }
    

    public String saludarUsuario(String nombre,String idioma){
        Map<String,String> idiomas=new HashMap<>();
        idiomas.put("Español", "Hola");
        idiomas.put("Ingles", "Hello");
        idiomas.put("Frances", "Bonjour");

        String cadena;
        if (idiomas.containsKey(idioma)) {
            cadena=idiomas.get(idioma)+", "+nombre+"!";
        } else {
            cadena="No se conoce el idioma";
        }

        return cadena;
    }
}

/*@Service
public class MyApiService {

    private final UserRepository userRepository;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public MyApiService(UserRepository userRepository, ProductService productService, OrderService orderService) {
        this.userRepository = userRepository;
        this.productService = productService;
        this.orderService = orderService;
    }

    // Métodos del servicio API REST
}
 */

 /*@Service
@RequiredArgsConstructor
public class MyApiService {

    private final UserRepository userRepository;
    private final ProductService productService;
    private final OrderService orderService;

    // Métodos del servicio API REST
}
 */