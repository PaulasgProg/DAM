package entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="telefono")
public class Telefono {
	
	/*	idTelefono integer primary key AUTO_INCREMENT,
    idCliente integer,
    descripcion varchar(100),
    numero varchar(20),
    foreign key (idCliente) references cliente(idCliente)*/
	
	private int idTelefono;
	private String descripcion;
	private String numero;
	
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	public Telefono() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Telefono(String descripcion, String numero, Cliente cliente) {
		super();
		this.descripcion = descripcion;
		this.numero = numero;
		this.cliente = cliente;
	}

	public int getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(int idTelefono) {
		this.idTelefono = idTelefono;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Telefono [idTelefono=" + idTelefono + ", descripcion=" + descripcion + ", numero=" + numero
				+ ", cliente=" + cliente + "]";
	}

	
}
