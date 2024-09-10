package repositorio;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Pelicula;

public class RepositorioPelicula {
	/*Agregar Peliculas
Eliminar Peliculas
Buscar registros por título
Buscando registros por identificador
Devolver todos los registros*/
	
	private Session sesion;

	public RepositorioPelicula(Session sesion) {
		super();
		this.sesion = sesion;
	}
	
	public void addPelicula(Pelicula pelicula) {
		this.sesion.save(pelicula);
	}
	
	public void deletePelicula(Pelicula pelicula) {
		this.sesion.delete(pelicula);
	}
	
	public List<Pelicula> buscarPeliculaTitulo(String nombre){
		Transaction t=this.sesion.beginTransaction();
		Query q=this.sesion.createQuery("SELECT p from Pelicula p WHERE p.titulo=:nombre");
		q.setParameter("nombre", nombre);
		
		List<Pelicula> listaPeliculas=q.getResultList();
		
		return listaPeliculas;
	}
	
	public Pelicula buscarPeliculaId(int id) {
		Transaction t=this.sesion.beginTransaction();
		Query q=this.sesion.createQuery("SELECT p from Pelicula p WHERE p.idPelicula=:id");
		q.setParameter("id", id);
		
		Pelicula p=(Pelicula) q.getSingleResult();
		
		return p;
	}
	
	public List<Pelicula> todosregistros(){
		Transaction t=this.sesion.beginTransaction();
		Query q=this.sesion.createQuery("SELECT p from Pelicula p");
		
		List<Pelicula> listaPeliculas=q.getResultList();
		return listaPeliculas;
	}

}
