package main;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import entidades.Autores;
import entidades.Libros;
import entidades.Telefonos;
import repositorio.AutorRepositorio;
import repositorio.LibroRepositorio;
import repositorio.TelefonoRepositorio;

public class App {
	static Scanner sc;
	static AutorRepositorio autorRepositorio;
	static LibroRepositorio libroRepositorio;
	static TelefonoRepositorio telefonoRepositorio;

	public static void main(String[] args) {
		System.out.println("Ejercicio 404");
		
		sc=new Scanner(System.in);
		
		Session sesion= HibernateUtil.get().openSession();
		
		autorRepositorio=new AutorRepositorio(sesion);
		libroRepositorio=new LibroRepositorio(sesion);
		telefonoRepositorio=new TelefonoRepositorio(sesion);
		
		mostrarMenu();
		
		
		sesion.close();
		System.out.println("Finalizando la conexion a MySQL");

	}
	
	public static void mostrarMenu() {
		int opcion = -1;
		do {
			System.out.println("\n1. Insertar nueva fila\n2. Borrar fila\n3. Consultar\n4. Salir");
			opcion = sc.nextInt();
			switch (opcion) {
				case 1: {
					mostrarMenuInsertar();
					break;
				}
				case 2:{
					mostrarMenuBorrar();
					break;
				}
				case 3:{
					mostrarMenuConsultas();
					break;
				}
				case 4:{
					System.exit(0);
					break;
				}
			}
		}while(opcion != 4);
	}
	public static void mostrarMenuInsertar() {
		int opcion = -1;
		do {
			System.out.println("\n1. Insertar nuevo autor\n2. Insertar nuevo libro\n3. Enlazar autor-libro\n4. Insertar teléfono para un autor\n4. Atrás");
			opcion = sc.nextInt();
			switch (opcion) {
				case 1: {
					insertarAutor(false);
					break;
				}
				case 2:{
					insertarLibro(false);
					break;
				}
				case 3:{
					enlazarAutorLibro();
					break;
				}
				case 4:{
					insertarTelefonoAutor();
					break;
				}
				case 5:{
					break;
				}
			}
		}while(opcion < 1 || opcion > 5);
	}
	private static void insertarTelefonoAutor() {
		String DNI = pedirString("Introduzca el DNI del autor");
		String numTel = pedirString("Introduzca el número de telefono");
		
		
		Autores autor = autorRepositorio.encontrarUnoPorString(DNI);
		Telefonos telefono = new Telefonos(autor,numTel);
		
		autor.setTelefono(telefono);
		
		autorRepositorio.actualizar(autor);
		telefonoRepositorio.insertarUno(telefono);
		
	}

	public static void insertarLibro(boolean vieneDeAutor) {
		String titulo = pedirString("Introduzca el titulo del libro: ");
		System.out.println("Introduzca el precio del libro");
		double precio = sc.nextDouble();
		
		Libros libro = new Libros(titulo, precio);
		libroRepositorio.insertarUno(libro);
	}
	
	public static void insertarAutor(boolean vieneDeAutor) {
		String dni = pedirString("Introduzca el DNI del autor: ");
		String nombre = pedirString("Introduzca el nombre del autor: ");
		String localidad = pedirString("Introduzca el localidad del autor: ");
		
		Autores autor = new Autores(dni, nombre, localidad);
		autorRepositorio.insertarUno(autor);
	}
	
	public static void enlazarAutorLibro() {
		String dni = pedirString("Introduzca el DNI del autro");
		String titulo = pedirString("Título del libro");
		
		Autores autor = autorRepositorio.encontrarUnoPorString(dni);
		Libros libro = libroRepositorio.encontrarUnoPorString(titulo);
		
		autor.addListaLibros(libro);
		autorRepositorio.actualizar(autor);
		libroRepositorio.actualizar(libro);
	}
	
	public static void mostrarMenuBorrar() {
		
		int opcion = -1;
		do {
			System.out.println("\n1. Borrar autor\n2. Borar libro\n3. Atrás");
			opcion = sc.nextInt();
			switch (opcion) {
				case 1: {
					String dni = pedirString("Introduzca el DNI del autor");
					Autores autor = autorRepositorio.encontrarUnoPorString(dni);
					autorRepositorio.borrar(autor);
					break;
				}
				case 2:{
					String titulo = pedirString("Introduzca el título del libro");
					Libros libro = libroRepositorio.encontrarUnoPorString(titulo);
					libroRepositorio.borrar(libro);
					break;
				}
				case 3:{
					break;
				}
			}
		}while(opcion < 1 || opcion > 3);
	}
	
	public static void mostrarMenuConsultas() {
		System.out.println("\n1. Visualizar datos de un libro a partir del título\n2. Visualizar libros de un determinado autor\n3. Visualizar todos los libros\n4. Visualizar todos los autores y sus libros\n5. Atrás");
		int opcion = -1;
		do {
			opcion = sc.nextInt();
			switch (opcion) {
				case 1: {
					String titulo = pedirString("Introduzca el título de un libro");
					Libros libro = libroRepositorio.encontrarUnoPorString(titulo);
					System.out.println(libro.toString());
					break;
				}
				case 2:{
					String DNI = pedirString("Introduzca el DNI del autor");
					Autores autor = autorRepositorio.encontrarUnoPorString(DNI);
					for(Libros libro : autor.getListaLibros())
						System.out.println(libro.toString());
					
					break;
				}
				case 3:{
					List<Libros> listaLibros = libroRepositorio.encontrarTodos();
					for(Libros libro : listaLibros)
						System.out.println(libro.toString());
					break;
				}
				case 4:{
					List<Autores> listaAutores = autorRepositorio.encontrarTodos();
					for (Autores autor : listaAutores) {
						System.out.println(autor.toString() + "\nLibros:");
						for(Libros libro : autor.getListaLibros()) {
							System.out.println(libro.toString());
						}
					}
					break;
				}
				case 5:{
					mostrarMenu();
					break;
				}
			}
		}while(opcion < 1 || opcion > 5);
	}
	
	public static String pedirString(String mensaje) {
		System.out.println(mensaje);
		return sc.next();
	}
	
	public int pedirInt(String mensaje) {
		System.out.println(mensaje);
		return sc.nextInt();
	}

}
