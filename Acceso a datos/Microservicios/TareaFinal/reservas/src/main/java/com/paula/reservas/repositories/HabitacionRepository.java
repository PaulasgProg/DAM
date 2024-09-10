package com.paula.reservas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paula.reservas.entities.Habitacion;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

    Habitacion findByHabitacionId(int habitacionId);

}
