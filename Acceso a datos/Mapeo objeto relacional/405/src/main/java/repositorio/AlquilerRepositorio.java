package repositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Alquiler;
import entidades.Cliente;
import entidades.Libro;

public class AlquilerRepositorio {
	
	private Session sesion;

	public AlquilerRepositorio(Session sesion) {
		super();
		this.sesion = sesion;
	}
	
	/*esAlquilado(idLibro): devuelve un booleano en función de si el libro está alquilado.
alquilar(idLibro, idCliente): crea el alquiler de un libro. Se debe comprobar que el libro 
y el cliente existan y que el libro no esté ya alquilado.
devolver(idLibro): devuelve un libro si estaba alquilado.*/
	
	public Boolean esAlquilado(int idLibro) {
		Query q = this.sesion.createQuery("select a from Alquiler a where a.idAlquiler.libro.idLibro=:idLibro");
		
		q.setParameter("idLibro", idLibro);
		List<Alquiler> listaAlquilados=new ArrayList<Alquiler>();
		
		for (Alquiler alquiler : listaAlquilados) {
			if(alquiler.isAlquilado()) {
				return true;
			}
		}
		return false;
		
	}

	public void alquilar(int idLibro,int idCliente) {
		Transaction tr=sesion.beginTransaction();
		Query qlibro=this.sesion.createQuery("Select l from Libro l where l.idLibro=:idLibro");
		Query qcliente=this.sesion.createQuery("Select c from Cliente c where c.idCliente=:idCliente");
		qlibro.setParameter("idLibro", idLibro);
		qcliente.setParameter("idCliente", idCliente);
		try {
			Libro libro=(Libro) qlibro.getSingleResult();
			Cliente cliente=(Cliente) qcliente.getSingleResult();
			
			Boolean alquilado=esAlquilado(libro.getId());
			
			if(alquilado==false) {
				Alquiler alquiler=new Alquiler(new Date(),true);
				alquiler.setCliente(cliente);
				alquiler.setLibro(libro);
				alquiler.setAlquilado(true);
				this.sesion.save(alquiler);
				
				System.out.println("Libro alquilado correctamente");
				
			}else {
				System.out.println("Libro ya alquilado");
			}
			
		}catch(Exception e) {
			System.out.println("No exite el libro o el cliente");
		}
		
		tr.commit();
	}
	
	public void devolver(int idLibro) {
		if(esAlquilado(idLibro)) {
			Transaction tr=this.sesion.beginTransaction();
			Query q= this.sesion.createQuery("select a from Alquiler a where a.libro.idLibro=:idLibro and a.alquilado=true");
			q.setParameter("idLibro", idLibro);
			
			Alquiler alquiler=(Alquiler) q.getSingleResult();
			
			alquiler.setAlquilado(false);
			this.sesion.update(alquiler);
			tr.commit();
		}else {
			System.out.println("No se puede devolver");
		}
		
		
	}
}
