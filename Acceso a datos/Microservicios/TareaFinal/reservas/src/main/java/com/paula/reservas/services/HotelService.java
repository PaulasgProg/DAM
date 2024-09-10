package com.paula.reservas.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paula.reservas.entities.Hotel;
import com.paula.reservas.hotelDTO.HotelActualizarDTO;
import com.paula.reservas.hotelDTO.HotelCrearDTO;
import com.paula.reservas.repositories.HotelRepository;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public Hotel crearHotel(HotelCrearDTO hotelCrearDTO) {
        Hotel hotel = new Hotel(hotelCrearDTO.getNombre(), hotelCrearDTO.getDireccion());
        return hotelRepository.save(hotel);
    }

    public Hotel actualizarHotel(HotelActualizarDTO hotelActualizarDTO) {
        Hotel hotel = hotelRepository.findByHotelId(hotelActualizarDTO.getId());
        if (hotel != null) {
            hotel.setDireccion(hotelActualizarDTO.getDireccion());
            hotel.setNombre(hotelActualizarDTO.getNombre());

            return hotelRepository.save(hotel);
        } else {
            return null;
        }
    }

    public Hotel eliminarHotel(Integer id) {
        Hotel hotel = hotelRepository.findByHotelId(id);
        if (hotel != null) {
            hotelRepository.delete(hotel);
            return hotel;
        } else {
            return null;
        }
    }

    // En caso de que haya mas de un hotel con el mismo nombre devuelve el primero
    // que encuentre
    public Integer obtenerIdApartirNombre(String nombre) {
        List<Hotel> listahotel = hotelRepository.findByNombre(nombre);
        if (listahotel != null && !listahotel.isEmpty()) {
            Hotel hotel = listahotel.get(0);
            if (hotel == null) {
                return null;
            } else {
                return hotel.getHotelId();
            }
        } else {
            return null;
        }

    }

    public String obtenerNombreAPartirId(Integer id) {
        Hotel hotel = hotelRepository.findByHotelId(id);
        if (hotel != null) {
            return hotel.getNombre();
        } else {
            return null;
        }
    }

}
