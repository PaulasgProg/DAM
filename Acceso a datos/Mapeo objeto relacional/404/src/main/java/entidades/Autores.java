package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="autores")
public class Autores {
	@Id
	@Column(name="dniAutor")
	private String dniAutor;
	@Column(name="nombre")
	private String nombre;
	@Column(name="nacionalidad")
	private String nacionalidad;

	
	@ManyToMany
	@JoinTable(name="Libros_Autores",
			joinColumns = @JoinColumn(name="dniAutor"),
			inverseJoinColumns = @JoinColumn(name="idLibro"))
	List<Libros> listaLibros=new ArrayList<Libros>();
	
	@OneToOne(mappedBy = "autor")
	@JoinColumn(name = "dni_autor")
	private Telefonos telefono;

	public Autores() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Autores(String dniAutor, String nombre, String nacionalidad) {
		super();
		this.dniAutor = dniAutor;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	public String getDniAutor() {
		return dniAutor;
	}

	public void setDniAutor(String dniAutor) {
		this.dniAutor = dniAutor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<Libros> getListaLibros() {
		return listaLibros;
	}

	public void setListaLibros(List<Libros> listaLibros) {
		this.listaLibros = listaLibros;
	}

	public Telefonos getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefonos telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Autores [dniAutor=" + dniAutor + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad
				+ ", listaLibros=" + listaLibros + ", telefono=" + telefono + "]";
	}

	public void addListaLibros(Libros libro) {
			this.listaLibros.add(libro);
			libro.addAutor(this);;
		
	}
	
	
}
