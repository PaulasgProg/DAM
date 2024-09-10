package repositorio;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Actor;
import modelo.Actriz;
import modelo.Persona;

public class RepositorioPersona {
	/*Guardar un nuevo objeto de tipo Persona la base de datos
Buscar objetos de tipo Persona por identificador
Buscar objetos en la Persona base de datos de tipos que nacieron después de un año determinado.
Buscar objetos del Persona, cuyo valor del campo numeroMejorActriz sea mayor a un determinado valor.
Buscar objetos del Persona, cuyo valor del campo numeroOscars sea mayor a un determinado valor.*/
	
	private Session sesion;

	public RepositorioPersona(Session sesion) {
		super();
		this.sesion = sesion;
	}

	public RepositorioPersona() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void guardar(Persona persona) {
		this.sesion.save(persona);
	}
	
	public Persona personaPorId(int id) {
		Transaction t=this.sesion.beginTransaction();
		Query q=this.sesion.createQuery("SELECT p from Persona p WHERE p.idPersona=:id");
		
		q.setParameter("id", id);
		Persona p=(Persona) q.getSingleResult();
		return p;
	}
	public void addNumeroMA(int num,Actriz actriz) {
		actriz.setNumeroMejorActriz(num);
		this.sesion.update(actriz);
	}
	
	public void addNumeroO(int num,Actor actor) {
		actor.setNumeroOscars(num);
		this.sesion.update(actor);
	}
	
	public List<Persona> numeroMA(int num) {
		Transaction t=this.sesion.beginTransaction();
		Query q=this.sesion.createQuery("SELECT p from Personas p where p.numeroMejorActriz>:num");
		List<Persona> lista= q.getResultList();
		
		return lista;
	}
	public List<Persona> numeroO(int num) {
		Transaction t=this.sesion.beginTransaction();
		Query q=this.sesion.createQuery("SELECT p from Personas p where p.numeroOscars>:num");
		List<Persona> lista= q.getResultList();
		
		return lista;
	}

}
