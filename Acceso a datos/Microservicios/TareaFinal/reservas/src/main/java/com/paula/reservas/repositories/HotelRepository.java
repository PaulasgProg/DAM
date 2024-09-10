package com.paula.reservas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paula.reservas.entities.Hotel;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    Hotel findByHotelId(int hotelId);

    List<Hotel> findByNombre(String nombre);
}
