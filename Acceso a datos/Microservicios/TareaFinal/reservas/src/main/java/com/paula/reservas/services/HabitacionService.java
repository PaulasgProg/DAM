package com.paula.reservas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paula.reservas.entities.Habitacion;
import com.paula.reservas.entities.Hotel;
import com.paula.reservas.habitacionDTO.HabitacionActualizarDTO;
import com.paula.reservas.habitacionDTO.HabitacionDTO;
import com.paula.reservas.repositories.HabitacionRepository;
import com.paula.reservas.repositories.HotelRepository;

@Service
public class HabitacionService {
    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private HotelRepository hotelRepository;

    private final String[] listaTipos = { "Individual", "Doble", "Triple", "Suite" };

    public String crearHabitacion(HabitacionDTO habitacionDTO) {
        int idHotel = habitacionDTO.getIdHotel();
        Hotel hotel = hotelRepository.findByHotelId(idHotel);
        if (hotel != null) {
            for (String string : listaTipos) {
                if (habitacionDTO.getTipo().equals(string)) {
                    Habitacion habitacion = new Habitacion(habitacionDTO.getNumeroHabitacion(), habitacionDTO.getTipo(),
                            habitacionDTO.getPrecio(), hotel, true);
                    habitacionRepository.save(habitacion);
                    return "Se ha creado correctamente";
                }
            }

        } else {
            return "No se ha encontrado el hotel indicado";
        }
        return "El tipo de habitación no es correcto.Debe ser Individual, Doble, Triple o Suite";
    }

    public String actualizarHabitacion(HabitacionActualizarDTO habitacionActualizada) {
        Habitacion habitacion = habitacionRepository.findByHabitacionId(habitacionActualizada.getId());
        if (habitacion != null) {
            int idHotel = habitacionActualizada.getIdHotel();
            Hotel hotel = hotelRepository.findByHotelId(idHotel);
            if (hotel != null) {
                for (String string : listaTipos) {
                    if (habitacionActualizada.getTipo().equals(string)) {
                        habitacion.setDisponible(habitacionActualizada.getDisponible());
                        habitacion.setHotel(hotel);
                        habitacion.setNumeroHabitacion(habitacionActualizada.getNumeroHabitacion());
                        habitacion.setTipo(habitacionActualizada.getTipo());
                        habitacion.setPrecio(habitacionActualizada.getPrecio());

                        habitacionRepository.save(habitacion);
                        return "Se ha actualizado correctamente la habitación con id:" + habitacionActualizada.getId();
                    }
                }
            } else {
                return "No se ha encontrado el hotel indicado";
            }
        } else {
            return "No existe el la habitación introducida";
        }
        return "El tipo de habitación no es correcto.Debe ser Individual, Doble, Triple o Suite";
    }

    public Habitacion eliminarHabitacion(Integer id) {
        Habitacion habitacion = habitacionRepository.findByHabitacionId(id);
        if (habitacion != null) {
            habitacionRepository.delete(habitacion);
            return habitacion;
        } else {
            return null;
        }
    }

}
