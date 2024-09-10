package repositorios;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.*;

public class PedidoRepositorio {
	
	private Session sesion;

	public PedidoRepositorio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PedidoRepositorio(Session sesion) {
		super();
		this.sesion = sesion;
	}
	
	/*Mostrar todos los pedidos de la base de datos.
	Mostrar todos los pedidos de un cliente.
	Añadir un pedido y sus productos.
	Borrar un pedido con sus productos.*/
	
	public List<Pedido> listapedidos(){
		Transaction t=this.sesion.beginTransaction();
		Query q=this.sesion.createQuery("SELECT p from pedidos p");
		
		List<Pedido> lista=q.getResultList();
		return lista;
	}

}
