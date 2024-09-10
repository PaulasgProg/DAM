package com.paula.libros.services;

import org.springframework.stereotype.Service;

import com.paula.libros.entities.Prestamo;
import com.paula.libros.repositories.PrestamoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;

    public void guardar(Prestamo prestamo) {
        prestamoRepository.save(prestamo);
    }

}
