package repositorio;

import javax.persistence.Query;

import org.hibernate.Session;

import entidades.Cliente;

public class RepositorioCliente {
	
	private Session sesion;

	public RepositorioCliente(Session sesion) {
		super();
		this.sesion = sesion;
	}
	public Cliente getClienteId(int idCliente) {
		Query q= this.sesion.createQuery("SELECT c from Cliente c WHERE c.idCliente=:idCliente");
		q.setParameter("idCliente", idCliente);
		
		Cliente c=(Cliente) q.getSingleResult();
		
		return c;
	}
	

}
