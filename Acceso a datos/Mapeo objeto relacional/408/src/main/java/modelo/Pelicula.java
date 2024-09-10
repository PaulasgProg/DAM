package modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="peliculas")
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="idPelicula")
	private int idPelicula;
	private int anhoPubli;
	private String titulo;
	
	@ManyToMany(mappedBy = "listaPeliculas")
	private List<Persona> listaPersonas;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="idGenero")
	private Genero genero;

	public Pelicula() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pelicula(String titulo, int anhoPubli, Genero genero) {
		super();
		this.anhoPubli = anhoPubli;
		this.titulo = titulo;
		this.genero = genero;
		genero.addPelicula(this);
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getAnhoPubli() {
		return anhoPubli;
	}

	public void setAnhoPubli(int anhoPubli) {
		this.anhoPubli = anhoPubli;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Pelicula [idPelicula=" + idPelicula + ", anhoPubli=" + anhoPubli + ", titulo=" + titulo
				+ ", listaPersonas=" + listaPersonas + ", genero=" + genero + "]";
	}

	
}
