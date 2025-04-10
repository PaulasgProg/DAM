package com.paula.libros;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.paula.libros.entities.Libro;
import com.paula.libros.entities.Prestamo;
import com.paula.libros.entities.Socio;
import com.paula.libros.services.LibroService;
import com.paula.libros.services.PrestamoService;
import com.paula.libros.services.SocioService;

@SpringBootApplication
public class LibrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrosApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(LibroService libroService, SocioService socioService,
			PrestamoService prestamoService) {
		return args -> {
			ArrayList<Libro> datos = (ArrayList<Libro>) libroService.findAll();
			if (datos.size() > 0) {
				System.out.println("La base de datos ya estaba inicializada");
				return;
			}

			ArrayList<Libro> listaLibros = new ArrayList<>();
			ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
			ArrayList<Socio> listaSocios = new ArrayList<>();

			listaLibros.add(new Libro("El Señor de los Anillos", "J.R.R. Tolkien", 1954));
			listaLibros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", 1967));
			listaLibros.add(new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", 1997));
			listaLibros.add(new Libro("Libro X", "Autor X", 2002));
			listaLibros.add(new Libro("Libro Y", "Autora Y", 2007));

			for (Libro libro : listaLibros)
				libroService.guardar(libro);

			listaSocios.add(new Socio("Ana García", "Calle 321, Ciudad X", LocalDate.of(2022, 1, 1)));
			listaSocios.add(new Socio("Carlos Martínez", "Avenida 654, Ciudad Y", LocalDate.of(2022, 12, 15)));
			listaSocios.add(new Socio("Elena Pérez", "Calle 987, Ciudad Z", LocalDate.of(2022, 2, 10)));

			for (Socio socio : listaSocios)
				socioService.guardar(socio);

			listaPrestamos.add(new Prestamo(LocalDate.of(2022, 2, 1), LocalDate.of(2022, 3, 1)));
			listaPrestamos.add(new Prestamo(LocalDate.of(2022, 1, 15), LocalDate.of(2022, 2, 15)));
			listaPrestamos.add(new Prestamo(LocalDate.of(2022, 3, 10), LocalDate.of(2022, 4, 10)));
			listaPrestamos.add(new Prestamo(LocalDate.of(2022, 3, 10)));

			listaPrestamos.get(0).agregarLibro(listaLibros.get(0));
			listaPrestamos.get(0).agregarSocio(listaSocios.get(0));
			listaPrestamos.get(1).agregarLibro(listaLibros.get(1));
			listaPrestamos.get(1).agregarSocio(listaSocios.get(1));
			listaPrestamos.get(2).agregarLibro(listaLibros.get(2));
			listaPrestamos.get(2).agregarSocio(listaSocios.get(2));
			listaPrestamos.get(3).agregarLibro(listaLibros.get(4));
			listaPrestamos.get(3).agregarSocio(listaSocios.get(2));

			for (Prestamo prestamo : listaPrestamos)
				prestamoService.guardar(prestamo);

			System.out.println("Datos cargados");
		};
	}

}
