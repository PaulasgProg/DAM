package repositorio;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entidades.Libros;

public class LibroRepositorio implements Repositoro<Libros>{
	
	private Session sesion;
	

	public LibroRepositorio(Session sesion) {
		super();
		this.sesion = sesion;
	}

	@Override
	public void insertarUno(Libros t) {
		Transaction trx=sesion.beginTransaction();
		sesion.save(t);
		trx.commit();
		
	}

	@Override
	public void borrar(Libros t) {
		Transaction trx=sesion.beginTransaction();
		sesion.delete(t);
		trx.commit();
		
	}

	@Override
	public List<Libros> encontrarTodos() {
		Query q=sesion.createQuery("SELECT l FROM libros l");
		List<Libros> listaLibros =q.getResultList();
		
		return listaLibros;
	}

	@Override
	public Libros encontrarUnoPorString(String nombre) {
		Query q=sesion.createQuery("SELECT l FROM libros l WHERE l.titulo:=nombre");
		q.setParameter("nombre", nombre);
		
		Libros libro=(Libros) q.getSingleResult();
		return libro;
	}

	@Override
	public void actualizar(Libros t) {
		Transaction trx = sesion.beginTransaction();
		sesion.update(t);
		trx.commit();
		
	}

}
