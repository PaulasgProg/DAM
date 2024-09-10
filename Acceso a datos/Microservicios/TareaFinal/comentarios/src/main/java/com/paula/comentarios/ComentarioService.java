package com.paula.comentarios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.paula.comentarios.dtos.ComentarioDTO;
import com.paula.comentarios.dtos.ComentarioInput;
import com.paula.comentarios.dtos.UsuarioDTO;

import lombok.RequiredArgsConstructor;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    private final String URLUSUARIO = "http://localhost:8702/usuarios";
    private final String URLRESERVAS = "http://localhost:8701/reservas";

    public Boolean login(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String urlUsuario = URLUSUARIO + "/validar";

        UsuarioDTO usuarioDTO = new UsuarioDTO(username, password);

        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(urlUsuario, usuarioDTO, Boolean.class);

        return responseEntity.getBody();
    }

    public Integer obtenerIdUsuario(String username) {
        String urlUsuarioObtenerid = URLUSUARIO + "/info/nombre/{nombre}";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> idStr = restTemplate.getForEntity(urlUsuarioObtenerid, String.class,
                username);
        return Integer.parseInt(idStr.getBody());

    }

    public Integer obtenerIdHotel(String nombre, String username, String password) {
        String urlHotelObtenerid = String.format(URLRESERVAS + "/hotel/id/%s:%s-%s", nombre, username, password);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Integer> idStr = restTemplate.postForEntity(urlHotelObtenerid, null,
                    Integer.class);

            return idStr.getBody();
        } catch (RestClientException e) {
            return null;
        }

    }

    public String obtenerNombreHotel(Integer idHotel, String username, String password) {
        String urlHotelObtenernombre = String.format(URLRESERVAS + "/hotel/nombre/%d:%s-%s", idHotel, username,
                password);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> idStr = restTemplate.postForEntity(urlHotelObtenernombre, null,
                String.class);

        return idStr.getBody();

    }

    public Boolean checkReserva(ComentarioInput comentarioDTO, String username, String password) {

        Integer idUsuario = obtenerIdUsuario(username);
        Integer idHotel = obtenerIdHotel(comentarioDTO.getNombreHotel(), username, password);
        Integer idReserva = comentarioDTO.getReservaId();

        String urlCheck = String.format(URLRESERVAS + "/check/%d-%d-%d", idUsuario, idHotel, idReserva);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Boolean> idStr = restTemplate.getForEntity(urlCheck, Boolean.class);
        return idStr.getBody();
    }

    public Boolean existeComentario(ComentarioInput comentarioDTO, String username, String password) {
        Integer idUsuario = obtenerIdUsuario(username);
        Integer idHotel = obtenerIdHotel(comentarioDTO.getNombreHotel(), username, password);
        Integer idReserva = comentarioDTO.getReservaId();

        Optional<ComentarioEntity> comentarioEntity = comentarioRepository.findByReservaIdUsuarioIdHotelId(idUsuario,
                idHotel, idReserva);
        return comentarioEntity.isPresent();
    }

    public Boolean crearComentario(ComentarioInput comentarioDTO, String username, String password) {
        try {
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
            ComentarioEntity comentarioEntity = new ComentarioEntity(null, obtenerIdUsuario(username),
                    obtenerIdHotel(comentarioDTO.getNombreHotel(), username, password),
                    comentarioDTO.getReservaId(),
                    comentarioDTO.getPuntuacion(),
                    comentarioDTO.getComentario(),
                    fecha);
            comentarioRepository.save(comentarioEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean eliminarComentarios() {
        List<ComentarioEntity> comentarioEntities = comentarioRepository.findAll();
        for (ComentarioEntity comentarioEntity : comentarioEntities) {
            comentarioRepository.delete(comentarioEntity);
        }
        if (comentarioRepository.findAll().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean eliminarComentarioDeUsuario(String idComentario) {
        Optional<ComentarioEntity> comentario = comentarioRepository.findById(idComentario);
        if (comentario.isEmpty()) {
            return false;
        } else {
            comentarioRepository.delete(comentario.get());
            return true;
        }
    }

    public List<ComentarioDTO> listarComentariosHotel(String nombre, String username, String password) {
        Integer idHotel = obtenerIdHotel(nombre, username, password);
        if (idHotel != null) {
            List<ComentarioEntity> lista = comentarioRepository.findByHotelId(idHotel);

            return lista.stream().map(comentarioEntity -> {
                String nombreHotel = nombre;
                int reservaId = comentarioEntity.getReservaId();
                double puntuacion = comentarioEntity.getPuntuacion();
                String comentario = comentarioEntity.getComentario();
                return new ComentarioDTO(nombreHotel, reservaId, puntuacion, comentario);
            }).collect(Collectors.toList());
        } else {
            return null;
        }

    }

    public List<ComentarioDTO> listarComentariosUsuario(String username, String password) {
        Integer idUsuario = obtenerIdUsuario(username);
        List<ComentarioEntity> lista = comentarioRepository.findByUsuarioId(idUsuario);

        return lista.stream().map(comentarioEntity -> {
            String nombreHotel = obtenerNombreHotel(comentarioEntity.getHotelId(), username, password);
            int reservaId = comentarioEntity.getReservaId();
            double puntuacion = comentarioEntity.getPuntuacion();
            String comentario = comentarioEntity.getComentario();
            return new ComentarioDTO(nombreHotel, reservaId, puntuacion, comentario);
        }).collect(Collectors.toList());

    }

    public List<ComentarioDTO> mostrarComentariosUsuarioReserva(Integer idReserva, String username, String password) {
        Integer idUsuario = obtenerIdUsuario(username);

        List<ComentarioEntity> list = comentarioRepository.findByReservaIdUsuarioId(idUsuario, idReserva);

        return list.stream().map(comentarioEntity -> {
            String nombreHotel = obtenerNombreHotel(comentarioEntity.getHotelId(), username, password);
            int reservaId = comentarioEntity.getReservaId();
            double puntuacion = comentarioEntity.getPuntuacion();
            String comentario = comentarioEntity.getComentario();
            return new ComentarioDTO(nombreHotel, reservaId, puntuacion, comentario);
        }).collect(Collectors.toList());
    }

    public Double puntuacionMediaHotel(String nombreHotel, String username, String password) {
        Integer idHotel = obtenerIdHotel(nombreHotel, username, password);
        if (idHotel != null) {
            Double media = comentarioRepository.puntiacionMediaHotel(idHotel);
            return media;
        }

        return null;
    }

    public Double puntuacionesMediasUsuario(String username, String password) {
        Integer idUsuario = obtenerIdUsuario(username);
        Double media = comentarioRepository.puntiacionMediasUsuario(idUsuario);
        return media;
    }
}
