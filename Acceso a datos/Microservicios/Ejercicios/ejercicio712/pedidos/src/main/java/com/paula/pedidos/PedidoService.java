package com.paula.pedidos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    private final String URLAUTOR = "http://localhost:8713/autor";
    private final String URLLIBRO = "http://localhost:8714/libro";

    public Pedido realizarPedido(AutorLibros sAutorLibros) {
        RestTemplate restTemplate = new RestTemplate();
        List<Long> idsLibros = new ArrayList<>();
        String urlAutor = URLAUTOR + "/pornombre/{nombre}";
        String urlLibros = URLLIBRO + "/portitulo/{titulo}";

        ResponseEntity<Long> responseEntity = restTemplate.getForEntity(urlAutor, Long.class,
                sAutorLibros.getNombreAutor());
        for (String libro : sAutorLibros.getNombreLibros()) {
            ResponseEntity<Long> responseLibro = restTemplate.getForEntity(urlLibros, Long.class, libro);
            idsLibros.add(responseLibro.getBody());
        }

        Long idAutor = responseEntity.getBody();

        Pedido pedido = new Pedido(idAutor, idsLibros, LocalDate.now());

        // Puedes realizar lógica adicional, como verificar la disponibilidad de los
        // libros, calcular el total, etc.

        return pedidoRepository.save(pedido);
    }

    public AutorLibros obtenerPedido(String id) {
        RestTemplate restTemplate = new RestTemplate();
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        AutorLibros autorLibros = new AutorLibros();
        List<String> nombreLibros = new ArrayList<>();

        String urlAutor = URLAUTOR + "/{id}";
        String urlLibros = URLLIBRO + "/{id}";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlAutor, String.class, pedido.getIdAutor());

        for (Long libro : pedido.getLibrosIds()) {
            ResponseEntity<String> responseLibro = restTemplate.getForEntity(urlLibros, String.class, libro);
            nombreLibros.add(responseLibro.getBody());
        }

        autorLibros.setNombreAutor(responseEntity.getBody());
        autorLibros.setNombreLibros(nombreLibros);

        return autorLibros;
    }

}
