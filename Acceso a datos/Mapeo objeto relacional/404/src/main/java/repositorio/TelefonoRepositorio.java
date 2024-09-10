package repositorio;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Telefonos;

public class TelefonoRepositorio implements Repositoro<Telefonos>{
	
	private Session sesion;
	
	public TelefonoRepositorio(Session sesion) {
		this.sesion = sesion;
	}

	@Override
	public void insertarUno(Telefonos t) {
		Transaction tr=sesion.beginTransaction();
		sesion.save(t);
		tr.commit();		
	}

	@Override
	public void borrar(Telefonos t) {
		Transaction tr=sesion.beginTransaction();
		sesion.delete(t);
		tr.commit();	
	}

	@Override
	public List<Telefonos> encontrarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Telefonos encontrarUnoPorString(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(Telefonos t) {
		Transaction tr=sesion.beginTransaction();
		sesion.update(t);
		tr.commit();
		
	}

}
