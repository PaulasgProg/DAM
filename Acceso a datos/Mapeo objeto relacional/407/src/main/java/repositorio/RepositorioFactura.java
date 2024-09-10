package repositorio;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Factura;

public class RepositorioFactura {
	
	private Session sesion;

	public RepositorioFactura(Session sesion) {
		super();
		this.sesion = sesion;
	}
	
/*Mostrar todas las facturas de la base de datos. La información debe mostrarse de la siguiente forma:
ID: xxx
CLIENTE: nombre, dirección, telefonos (movil: xxx, otro: xxx, etc).
DESCRIPCION: xxx
PRECIO: xxx

Añadir una factura.
Modificar una factura.
Borrar una factura.*/
	
	public void añadirFactura(Factura factura) {
		Transaction t=this.sesion.beginTransaction();
		sesion.save(factura);
		t.commit();
	}
	
	public void modificarFactura(Factura f) {
		Transaction t=this.sesion.beginTransaction();
		sesion.update(f);
		t.commit();
	}
	public void borrarFactura(Factura f) {
		Transaction t=this.sesion.beginTransaction();
		sesion.delete(f);
		t.commit();
	}
	public void mostrarFacturas() {
		
		Query q=this.sesion.createQuery("SELECT f from Factura f");
		List<Factura> listafacturas=q.getResultList();
		
		for (Factura factura : listafacturas) {
			factura.toString();
		}
	}
	

}
