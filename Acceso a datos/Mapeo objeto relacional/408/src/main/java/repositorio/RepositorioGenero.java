package repositorio;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Genero;

public class RepositorioGenero {
	/*Agregar nuevos géneros
Eliminar registros de un cierto género
Buscando registros por nombre
Buscando registros por identificador
Devolver todos los registros*/
	
	private Session sesion;

	public RepositorioGenero(Session sesion) {
		super();
		this.sesion = sesion;
	}
	
	public void addGenero(Genero genero2) {

		this.sesion.save(genero2);	
		
	}
	
	public void deleteReg(Genero genero) {
		this.sesion.delete(genero);
		
	}
	
	public List<Genero> buscarNombre(String nombre) {
		Transaction t=this.sesion.beginTransaction();
		Query q=this.sesion.createQuery("SELECT g from Genero g where g.nombre=:nombre");
		q.setParameter("nombre", nombre);
		
		List<Genero> listaGeneros=q.getResultList();
		t.commit();
		return listaGeneros;
		
	}
	
	public Genero buscarPorId(int id) {
		Transaction t=this.sesion.beginTransaction();
		Query q=this.sesion.createQuery("SELECT g from Genero g where g.idGenero=:id");
		q.setParameter("id", id);
		
		Genero genero= (Genero) q.getSingleResult();
		t.commit();
		return genero;
	}
	
	public void actualizar(Genero t) {
		this.sesion.update(t);
	}
	
	public List<Genero> todosRegistros(){
		Transaction t=this.sesion.beginTransaction();
		Query q=this.sesion.createQuery("SELECT g from Genero g");
		
		List<Genero> listaGeneros=q.getResultList();
		t.commit();
		return listaGeneros;
		
	}

}
