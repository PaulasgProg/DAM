package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="libro")

public class Libro {
	/*idLibro int primary key AUTO_INCREMENT, 
    codigo varchar(20), 
    titulo varchar(200),
    autores varchar(300), 
    año int*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idLibro")
	private int idLibro;
	private String codigo;
	private String autores;
	
	@Column(name = "año")
	private int anho;
	
	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
	List<Alquiler> listaAlquileres;

	public Libro(int id, String codigo, String autores, int anho) {
		super();
		this.idLibro = id;
		this.codigo = codigo;
		this.autores = autores;
		this.anho = anho;
	}

	public Libro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return idLibro;
	}

	public void setId(int id) {
		this.idLibro = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public int getAnho() {
		return anho;
	}

	public void setAnho(int anho) {
		this.anho = anho;
	}

	public List<Alquiler> getListaAlquileres() {
		return listaAlquileres;
	}

	public void setListaAlquileres(List<Alquiler> listaAlquileres) {
		this.listaAlquileres = listaAlquileres;
	}
	
	
	
}
