package com.paula.reservas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.paula.reservas.entities.Habitacion;
import com.paula.reservas.entities.Reserva;
import com.paula.reservas.repositories.HabitacionRepository;
import com.paula.reservas.repositories.ReservaRepository;
import com.paula.reservas.reservaDTO.ReservaCrearDTO;
import com.paula.reservas.reservaDTO.ReservaIdEstadoDTO;
import com.paula.reservas.reservaDTO.UsuarioDTO;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private HabitacionRepository habitacionRepository;

    private final String[] listaEstados = { "Pendiente", "Cancelada", "Confirmada" };

    private final String URLUSUARIO = "http://localhost:8702/usuarios";

    public Boolean login(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String urlUsuario = URLUSUARIO + "/validar";

        UsuarioDTO usuarioDTO = new UsuarioDTO(username, password);

        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(urlUsuario, usuarioDTO, Boolean.class);

        return responseEntity.getBody();
    }

    public Reserva checkReserva(int idUsuario, int idHotel, int idReserva) {
        Reserva reserva = reservaRepository.findByReservaId(idReserva);
        if (reserva != null) {
            if (reserva.getUsuarioId() == idUsuario) {
                if (reserva.getHabitacion().getHotel().getHotelId() == idHotel) {
                    return reserva;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    public String crearReserva(ReservaCrearDTO reservaCrearDTO, String username, String password) {

        String urlUsuarioObtenerid = URLUSUARIO + "/info/nombre/{nombre}";

        Habitacion habitacion = habitacionRepository.findByHabitacionId(reservaCrearDTO.getHabitacionId());
        if (habitacion != null) {
            if (habitacion.getDisponible()) {

                if (reservaCrearDTO.getFechaInicio().isAfter(reservaCrearDTO.getFechaFin())) {
                    return "Error con las fechas introducidas";
                } else {
                    UsuarioDTO usuarioDTO = new UsuarioDTO(username, password);
                    RestTemplate restTemplate = new RestTemplate();
                    ResponseEntity<String> idStr = restTemplate.getForEntity(urlUsuarioObtenerid, String.class,
                            usuarioDTO.getNombre());
                    Reserva reserva = new Reserva(Integer.parseInt(idStr.getBody()), reservaCrearDTO.getFechaInicio(),
                            reservaCrearDTO.getFechaFin(), listaEstados[2], habitacion);
                    habitacion.setDisponible(false);
                    habitacionRepository.save(habitacion);
                    reservaRepository.save(reserva);
                    return "Se ha creado la reserva correctamente";
                }
            } else {
                return "La habitación indicada no está disponible";
            }
        } else {
            return "La habitación introducida no existe";
        }

    }

    public String cambiarEstado(ReservaIdEstadoDTO reservaIdEstadoDTO) {

        Reserva reserva = reservaRepository.findByReservaId(reservaIdEstadoDTO.getReservaId());
        String estado = reservaIdEstadoDTO.getEstado();

        if (reserva != null) {
            for (String string : listaEstados) {
                if (estado.equals(string)) {
                    reserva.setEstado(estado);
                    reservaRepository.save(reserva);
                    if (estado.equals("Cancelada")) {
                        Habitacion habitacion = habitacionRepository
                                .findByHabitacionId(reserva.getHabitacion().getHabitacionId());
                        habitacion.setDisponible(true);
                        habitacionRepository.save(habitacion);
                    }
                    return "Se ha cambiado el estado correctamente";
                }
            }
            return "Error al introducir el estado de la reserva (Cancelada,Pendiente o Confirmada)";
        } else {
            return "La reserva indicada no existe";
        }

    }

    public List<ReservaCrearDTO> listarReservasUsuario(String username, String password) {
        String urlUsuarioObtenerid = URLUSUARIO + "/info/nombre/{nombre}";
        UsuarioDTO usuarioDTO = new UsuarioDTO(username, password);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> idStr = restTemplate.getForEntity(urlUsuarioObtenerid, String.class,
                usuarioDTO.getNombre());

        List<Reserva> reserva = reservaRepository.findByUsuarioId(Integer.parseInt(idStr.getBody()));
        List<ReservaCrearDTO> reservadto = new ArrayList<>();
        for (Reserva reserva2 : reserva) {
            ReservaCrearDTO nueva = new ReservaCrearDTO(reserva2.getFechaInicio(), reserva2.getFechaFin(),
                    reserva2.getHabitacion().getHabitacionId());
            reservadto.add(nueva);
        }
        return reservadto;
    }

    public List<ReservaCrearDTO> listarReservasSegunEstado(String estado) {

        List<Reserva> reserva = reservaRepository.findByEstado(estado);
        List<ReservaCrearDTO> reservadto = new ArrayList<>();
        for (Reserva reserva2 : reserva) {
            ReservaCrearDTO nueva = new ReservaCrearDTO(reserva2.getFechaInicio(), reserva2.getFechaFin(),
                    reserva2.getHabitacion().getHabitacionId());
            reservadto.add(nueva);
        }
        return reservadto;
    }

}
