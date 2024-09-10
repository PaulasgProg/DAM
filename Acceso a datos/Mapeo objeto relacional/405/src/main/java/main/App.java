package main;

import org.hibernate.Session;

import entidades.Cliente;
import repositorio.AlquilerRepositorio;
import repositorio.ClienteRepositorio;

public class App {

	public static void main(String[] args) {
		System.out.println("Ejercicio 405");
		
		Session s=HibernateUtil.get().openSession();
		
		ClienteRepositorio clienteRepositorio=new ClienteRepositorio(s);
		AlquilerRepositorio alquilerRepositorio=new AlquilerRepositorio(s);
		
		Cliente cliente=clienteRepositorio.obtenerCliente(2);
		System.out.println(cliente.toString());
		
		Cliente nuevoCliente = new Cliente("123456", "Cliente1", "Cliente1@cliente.es");
		clienteRepositorio.anhadirCliente(nuevoCliente);
		System.out.println(nuevoCliente.toString());
		
		nuevoCliente.setDni("789456");
		clienteRepositorio.modificarcliente(nuevoCliente);
		System.out.println(nuevoCliente.toString());
		
		clienteRepositorio.borrarCliente(nuevoCliente);
		
		// Ya está prestado daría error
		alquilerRepositorio.alquilar(3, 1);
		
		alquilerRepositorio.devolver(3);
		
		// No daría error porque ahora ya se ha devuelto
		alquilerRepositorio.alquilar(3, 1);
		
		s.close();
		System.out.println("Finalizando la conexion a MySQL");
	}
}
