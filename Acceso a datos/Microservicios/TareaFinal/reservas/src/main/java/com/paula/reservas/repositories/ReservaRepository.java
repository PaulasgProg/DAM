package com.paula.reservas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paula.reservas.entities.Reserva;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    Reserva findByReservaId(int reservaId);

    List<Reserva> findByUsuarioId(int usuarioId);

    List<Reserva> findByEstado(String estado);
}
