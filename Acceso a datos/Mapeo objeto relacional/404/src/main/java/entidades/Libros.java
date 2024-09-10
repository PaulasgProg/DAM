package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name= "libros", uniqueConstraints = @UniqueConstraint(columnNames = "titulo", name = "tituloUniqueConstraint") )
public class Libros {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="idLibro")
	private int id;
	
	@Column(name="titulo")
	private String titulo;
	@Column(name="precio")
	private double precio;
	
	@ManyToMany(mappedBy = "listaLibros")
	
	List<Autores> listaAutores=new ArrayList<Autores>();
	
	

	public Libros() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Libros(String titulo, double precio) {
		super();
		this.titulo = titulo;
		this.precio = precio;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Autores> getListaAutores() {
		return listaAutores;
	}

	public void setListaAutores(List<Autores> listaAutores) {
		this.listaAutores = listaAutores;
	}



	@Override
	public String toString() {
		return "Libros [id=" + id + ", titulo=" + titulo + ", precio=" + precio + ", listaAutores=" + listaAutores
				+ "]";
	}



	public void addAutor(Autores autores) {
		this.listaAutores.add(autores);
		
	}
	
	
}
