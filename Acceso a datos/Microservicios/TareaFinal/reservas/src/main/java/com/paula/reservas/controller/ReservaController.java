package com.paula.reservas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paula.reservas.entities.Reserva;
import com.paula.reservas.reservaDTO.ReservaCrearDTO;
import com.paula.reservas.reservaDTO.ReservaIdEstadoDTO;
import com.paula.reservas.services.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/check/{idUsuario}-{idHotel}-{idReserva}")
    public ResponseEntity<Boolean> checkReserva(@PathVariable Integer idUsuario, @PathVariable Integer idHotel,
            @PathVariable Integer idReserva) {
        Reserva reserva = reservaService.checkReserva(idUsuario, idHotel, idReserva);
        if (reserva == null) {
            return ResponseEntity.ok(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }

    @PostMapping("/{username}-{password}")
    public ResponseEntity<String> crearReserva(@RequestBody ReservaCrearDTO reservaCrearDTO,
            @PathVariable String username, @PathVariable String password) {
        if (reservaService.login(username, password)) {
            String reserva = reservaService.crearReserva(reservaCrearDTO, username, password);
            return ResponseEntity.ok(reserva);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario introducido no coincide");
        }
    }

    @PatchMapping("/{username}-{password}")
    public ResponseEntity<String> cambiarEstado(@RequestBody ReservaIdEstadoDTO reservaIdEstadoDTO,
            @PathVariable String username, @PathVariable String password) {

        if (reservaService.login(username, password)) {
            String cadena = reservaService.cambiarEstado(reservaIdEstadoDTO);
            return ResponseEntity.ok(cadena);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario introducido no coincide");
        }

    }

    @GetMapping("/{username}-{password}")
    public ResponseEntity<List<ReservaCrearDTO>> listarReservasUsuario(@PathVariable String username,
            @PathVariable String password) {
        if (reservaService.login(username, password)) {
            return ResponseEntity.ok(reservaService.listarReservasUsuario(username, password));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/{estado}/{username}-{password}")
    public ResponseEntity<List<ReservaCrearDTO>> listarReservasSegunEstado(@PathVariable String estado,
            @PathVariable String username, @PathVariable String password) {
        if (reservaService.login(username, password)) {
            return ResponseEntity.ok(reservaService.listarReservasSegunEstado(estado));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
