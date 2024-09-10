package com.paula.reservas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.paula.reservas.entities.Habitacion;
import com.paula.reservas.habitacionDTO.HabitacionActualizarDTO;
import com.paula.reservas.habitacionDTO.HabitacionDTO;
import com.paula.reservas.services.HabitacionService;
import com.paula.reservas.services.ReservaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservas/habitacion")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionService habitacionService;
    private final ReservaService reservaService;

    @PostMapping("/{username}-{password}")
    public ResponseEntity<String> crearHabitacion(@RequestBody HabitacionDTO habitacionDTO,
            @PathVariable String username, @PathVariable String password) {
        if (reservaService.login(username, password)) {
            String habitacion = habitacionService.crearHabitacion(habitacionDTO);
            return ResponseEntity.ok(habitacion);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario introducido no coincide");
        }
    }

    @PatchMapping("/{username}-{password}")
    public ResponseEntity<String> actualizarHabitacion(@RequestBody HabitacionActualizarDTO habitacionActualizarDTO,
            @PathVariable String username, @PathVariable String password) {
        if (reservaService.login(username, password)) {
            String habitacion = habitacionService.actualizarHabitacion(habitacionActualizarDTO);
            return ResponseEntity.ok(habitacion);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario introducido no coincide");
        }
    }

    @DeleteMapping("/{id}:{username}-{password}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable Integer id, @PathVariable String username,
            @PathVariable String password) {
        if (reservaService.login(username, password)) {
            Habitacion habitacion = habitacionService.eliminarHabitacion(id);
            if (habitacion != null) {
                return ResponseEntity.ok("Habitación con id:" + id + " eliminada");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al eliminar la habitación");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario introducido no coincide");
        }
    }
}
