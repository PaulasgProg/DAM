package com.paula.libros.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paula.libros.entities.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo,Integer> {
    

}
