package com.paula.reservas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paula.reservas.entities.Hotel;
import com.paula.reservas.hotelDTO.HotelActualizarDTO;
import com.paula.reservas.hotelDTO.HotelCrearDTO;
import com.paula.reservas.services.HotelService;
import com.paula.reservas.services.ReservaService;

import jakarta.ws.rs.core.Response;

@RestController
@RequestMapping("/reservas/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/{username}-{password}")
    public ResponseEntity<String> crearHotel(@RequestBody HotelCrearDTO hotelCrearDTO, @PathVariable String username,
            @PathVariable String password) {
        if (reservaService.login(username, password)) {
            Hotel hotel = hotelService.crearHotel(hotelCrearDTO);
            if (hotel != null) {
                return ResponseEntity.ok("Se ha creado el hotel correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al crear el hotel");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario introducido no coincide");
        }
    }

    @PatchMapping("/{username}-{password}")
    public ResponseEntity<String> actualizarHotel(@RequestBody HotelActualizarDTO hotelActualizarDTO,
            @PathVariable String username, @PathVariable String password) {
        if (reservaService.login(username, password)) {
            Hotel hotel = hotelService.actualizarHotel(hotelActualizarDTO);
            if (hotel != null) {
                return ResponseEntity.ok("Se ha modificado el hotel correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al modificar el hotel, el id introducido no existe");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario introducido no coincide");
        }
    }

    @DeleteMapping("/{id}:{username}-{password}")
    public ResponseEntity<String> eliminarHotel(@PathVariable Integer id, @PathVariable String username,
            @PathVariable String password) {
        if (reservaService.login(username, password)) {
            Hotel hotel = hotelService.eliminarHotel(id);
            if (hotel != null) {
                return ResponseEntity.ok("Se ha eliminado el hotel correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al eliminar el hotel");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario introducido no coincide");
        }
    }

    @PostMapping("/id/{nombre}:{username}-{password}")
    public ResponseEntity<Integer> obtenerIdApartirNombre(@PathVariable String nombre,
            @PathVariable String username, @PathVariable String password) {
        if (reservaService.login(username, password)) {
            Integer lista = hotelService.obtenerIdApartirNombre(nombre);
            return ResponseEntity.ok(lista);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

    @PostMapping("/nombre/{id}:{username}-{password}")
    public ResponseEntity<String> obtenerNombreAPartirId(@PathVariable Integer id, @PathVariable String username,
            @PathVariable String password) {
        if (reservaService.login(username, password)) {
            String nombre = hotelService.obtenerNombreAPartirId(id);
            if (nombre != null) {
                return ResponseEntity.ok(nombre);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("No se ha encontrado el hotel");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario introducido no coincide");
        }

    }

}
