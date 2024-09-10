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
import javax.persistence.Table;

@Entity
@Table(name="cliente")

public class Cliente {

	/*idCliente int primary key AUTO_INCREMENT,
    DNI char(9),
    nombre varchar(200),
    email varchar(200)*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCliente")
	private int idCliente;
	
	@Column(name = "DNI", columnDefinition = "char")
	private String dni;
	private String nombre;
	private String email;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Alquiler> listaAlquileres;

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente( String dni, String nombre, String email) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
	}

	public int getId() {
		return idCliente;
	}

	public void setId(int id) {
		this.idCliente = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Alquiler> getListaAlquileres() {
		return listaAlquileres;
	}

	public void setListaAlquileres(List<Alquiler> listaAlquileres) {
		this.listaAlquileres = listaAlquileres;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + idCliente + ", dni=" + dni + ", nombre=" + nombre + ", email=" + email + "]";
	}
	
	
}
