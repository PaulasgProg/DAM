package com.paula.libros.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paula.libros.entities.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Integer> {
    // Consulta 3: Contar cuántos libros ha prestado cada socio:
    @Query("select s.nombre, COUNT(p.libro.libro_id) from Socio s LEFT JOIN Prestamo p ON s.socio_id=p.socio.socio_id GROUP BY s.socio_id, s.nombre")
    List<Object[]> consulta3();

    // Consulta 4: Encontrar los socios que tienen libros vencidos (devolución
    // después de la fecha límite):
    @Query("select s.nombre, p.fecha_devolucion from Socio s JOIN Prestamo p ON p.socio.socio_id=s.socio_id WHERE p.fecha_devolucion>p.fecha_prestamo")
    List<Object[]> consulta4();

    // Consulta 6: Encontrar los socios que han prestado más de un libro:
    @Query("select s.socio_id, s.nombre, COUNT(p.libro.libro_id) from Socio s JOIN Prestamo p ON s.socio_id=p.socio.socio_id GROUP BY s.socio_id, s.nombre HAVING COUNT(p.libro.libro_id) > 1")
    List<Object[]> consulta6();

}
