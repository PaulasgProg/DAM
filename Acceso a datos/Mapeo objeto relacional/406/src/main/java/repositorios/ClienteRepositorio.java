package repositorios;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Cliente;

public class ClienteRepositorio {

	private Session sesion;

	public ClienteRepositorio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteRepositorio(Session sesion) {
		super();
		this.sesion = sesion;
	}
	
	public Cliente obtenerCliente(String dni) {
		Transaction t=this.sesion.beginTransaction();
		Cliente cliente;
		try {
			Query q=this.sesion.createQuery("Select c from Cliente c where dni=:dni");
			q.setParameter("dni", dni);
			cliente=(Cliente) q.getSingleResult();
			
		} catch (Exception e) {
			cliente= new Cliente(-1);
		}
		
		t.commit();
		
		return cliente;
	}
}
