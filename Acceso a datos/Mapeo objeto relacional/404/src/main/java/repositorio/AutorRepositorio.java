package repositorio;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Autores;

public class AutorRepositorio implements Repositoro<Autores> {

	private Session sesion;
	
	public AutorRepositorio(Session sesion) {
		this.sesion = sesion;
	}
	
	@Override
	public void insertarUno(Autores t) {
		Transaction tr=sesion.beginTransaction();
		sesion.save(t);
		tr.commit();
		
	}

	@Override
	public void borrar(Autores t) {
		Transaction tr=sesion.beginTransaction();
		sesion.delete(t);
		tr.commit();
		
	}

	@Override
	public List<Autores> encontrarTodos() {
		Query q=sesion.createNamedQuery("SELECT a FROM autores a");
		List<Autores> listaAutores=q.getResultList();
		return listaAutores;
	}

	@Override
	public Autores encontrarUnoPorString(String nombre) {
		Query q=sesion.createNamedQuery("SELECT a FROM autores a WHERE nombre:=nombre");
		q.setParameter("nombre", nombre);
		Autores autor=(Autores) q.getSingleResult();
		return autor;
	}

	@Override
	public void actualizar(Autores t) {
		Transaction tr=sesion.beginTransaction();
		sesion.update(t);
		tr.commit();
		
	}

}
