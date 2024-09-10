package entidades;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="factura")
public class Factura {
	
	/*idFactura integer primary key AUTO_INCREMENT,
    idCliente integer,
    descripcion varchar(500),
    precio double,
    fecha Date,
    foreign key (idCliente) references cliente(idCliente)*/
	
	private int idFactura;
	private String descripcion;
	private Double precio;
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	public Factura() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Factura(String descripcion, Double precio, Date fecha, Cliente cliente) {
		super();
		this.descripcion = descripcion;
		this.precio = precio;
		this.fecha = fecha;
		this.cliente = cliente;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", descripcion=" + descripcion + ", precio=" + precio + ", fecha="
				+ fecha + ", cliente=" + cliente.toString() + "]";
	}
	
	
}
