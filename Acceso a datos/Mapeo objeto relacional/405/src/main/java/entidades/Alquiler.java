package entidades;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mysql.cj.xdevapi.Client;

@Entity
@Table(name="alquiler")
public class Alquiler {
	/*idAlquiler int primary key AUTO_INCREMENT,
    idLibro int, 
	idCliente int,
    fecha Date, 
    alquilado bool,
    foreign key (idLibro) references libro(idLibro),
    foreign key (idCliente) references cliente(idCliente)*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAlquiler;
	private Date fecha;
	private boolean alquilado;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "idLibro")
	private Libro libro;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	public Alquiler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Alquiler(java.util.Date date, Boolean alquilado) {
		super();
		this.fecha = (Date) date;
		this.alquilado = alquilado;
	}

	public int getId() {
		return idAlquiler;
	}

	public void setId(int id) {
		this.idAlquiler = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getAlquilado() {
		return alquilado;
	}

	public void setAlquilado(Boolean alquilado) {
		this.alquilado = alquilado;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean isAlquilado() {
		return alquilado;
	}
}
