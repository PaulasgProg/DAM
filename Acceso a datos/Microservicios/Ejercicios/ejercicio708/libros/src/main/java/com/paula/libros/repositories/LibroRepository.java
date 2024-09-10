package com.paula.libros.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paula.libros.entities.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    // Consulta 1: Listar todos los libros prestados, junto con los detalles de los
    // socios:
    @Query("select l,s.nombre,s.direccion from Libro l JOIN Prestamo p ON l.libro_id=p.libro.libro_id JOIN Socio s ON p.socio.socio_id=s.socio_id")
    List<Object[]> consulta1();

    // Consulta 2: Encontrar los libros prestados actualmente y que aún no han sido
    // devueltos:
    @Query("select l from Libro l JOIN Prestamo p ON l.libro_id=p.libro.libro_id where p.fecha_devolucion is null and p.fecha_prestamo is not null ")
    List<Libro> consulta2();

    // Consulta 5: Listar los libros que nunca han sido prestados:
    @Query("select l from Libro l LEFT JOIN Prestamo p ON l.libro_id=p.libro.libro_id WHERE p.libro.libro_id is null")
    List<Libro> consulta5();

    // Consulta 7: Listar los libros junto con la cantidad de veces que han sido
    // prestados:
    @Query("select l.titulo, COUNT(p.libro.libro_id) from Libro l LEFT JOIN Prestamo p ON l.libro_id=p.libro.libro_id GROUP BY l.libro_id, l.titulo")
    List<Object[]> consulta7();

    // Consulta 8: Encontrar los libros prestados en un rango de fechas específico:
    @Query("select l.titulo, p.fecha_prestamo, p.fecha_devolucion FROM Libro l JOIN Prestamo p ON l.libro_id=p.libro.libro_id WHERE p.fecha_prestamo BETWEEN :fecha1 and :fecha2")
    List<Object[]> consulta8(@Param("fecha1") LocalDate fecha1, @Param("fecha2") LocalDate fecha2);

    // Consulta 9: Buscar los libros prestados por un socio específico
    @Query("select l.titulo, p.fecha_prestamo, p.fecha_devolucion, s.nombre FROM Libro l JOIN Prestamo p ON p.libro.libro_id=l.libro_id JOIN Socio s ON p.socio.socio_id=s.socio_id WHERE s.socio_id=:numero")
    List<Object[]> consulta9(@Param("numero") Integer numero);

}
