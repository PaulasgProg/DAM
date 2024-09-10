package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="genero")
public class Genero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="idGenero")
	private int idGenero;
	private String nombre;
	
	@OneToMany(mappedBy = "genero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Pelicula> listaPeliculas=new ArrayList<Pelicula>();

	
	public Genero() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Genero(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pelicula> getListaPeliculas() {
		return listaPeliculas;
	}

	public void setListaPeliculas(List<Pelicula> listaPeliculas) {
		this.listaPeliculas = listaPeliculas;
	}
	
	public void addPelicula(Pelicula pelicula) {
		this.listaPeliculas.add(pelicula);
	}

	@Override
	public String toString() {
		return "Genero [idGenero=" + idGenero + ", nombre=" + nombre +"]";
	}

	
}
