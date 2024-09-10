package main;

import java.util.Scanner;

import org.hibernate.Session;

import entidades.Cliente;
import entidades.Factura;
import repositorio.RepositorioCliente;
import repositorio.RepositorioFactura;

public class App {
	


	public static void main(String[] args) {
		System.out.println("Ejercicio 407");
		
		Session s=HibernateUtil.get().openSession();
		
		RepositorioCliente rc=new RepositorioCliente(s);
		RepositorioFactura RF=new RepositorioFactura(s);
		
		
		Scanner sc=new Scanner(System.in);
		
		mostrarMenu();
		
		int opcion=sc.nextInt();
		
		switch (opcion) {
			case 1:
				RF.mostrarFacturas();
			
				break;
			case 2:
				System.out.println("introduce el idCliente");
				int id =sc.nextInt();
				Cliente c=rc.getClienteId(id);
				Factura f=new Factura(null, null, null, c);
				break;
			case 3:
	
				break;
			case 4:
			
				break;
			case 5:
			
				break;
		

			default:
				break;
		}

	}
	private static void mostrarMenu() {
		
		
		System.out.println("1. Mostrar todas las facturas\n2. Añadir factura\n"
				+ "3. Modificar factura\n4. Borrar factura\n5. Salir");	
		
	}
	
	

}
