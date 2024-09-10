package repositorio;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Cliente;

public class ClienteRepositorio {
	private Session sesion;

	public ClienteRepositorio(Session sesion) {
		super();
		this.sesion = sesion;
	}
	
	/*obtenerCliente(idCliente): devuelve el objeto del cliente solicitado.
añadirCliente(cliente): añade el cliente a la BD.
modificarCliente(cliente): modifica el cliente en la BD.
borrarCliente(cliente): borra un cliente en el caso de que no tenga ningún libro alquilado. 
Devuelve un booleano en función de si el cliente ha sido borrado o no.*/
	
	public Cliente obtenerCliente(int idCliente) {
		Query q=this.sesion.createQuery("SELECT c from Cliente c where c.idCliente=:idCliente");
		q.setParameter("idCliente", idCliente);
		
		Cliente cliente=(Cliente) q.getSingleResult();
		return cliente;
	}
	public void anhadirCliente(Cliente cliente) {
		Transaction t=this.sesion.beginTransaction();
		sesion.save(cliente);
		t.commit();
	}
	public void modificarcliente(Cliente cliente) {
		Transaction trx = this.sesion.beginTransaction();
		
		this.sesion.update(cliente);
		
		trx.commit();
	}
	public Boolean borrarCliente(Cliente cliente) {
		try {
			Transaction t = this.sesion.beginTransaction();
			
			this.sesion.delete(cliente);
			t.commit();
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

}
