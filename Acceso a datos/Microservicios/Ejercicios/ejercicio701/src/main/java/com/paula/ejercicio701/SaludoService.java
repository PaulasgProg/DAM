package com.paula.ejercicio701;

import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor se usa cuando hay DI (autowired)
public class SaludoService {

    public String saludarUsuario(String nombre){
        return "Hola, "+nombre+"!";
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