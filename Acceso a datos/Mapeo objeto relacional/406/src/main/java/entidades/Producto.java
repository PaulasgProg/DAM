package entidades;

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
@Table(name="producto")
public class Producto {
	
	/*idProducto int primary key AUTO_INCREMENT,
    nombre varchar(250),
    descripcion varchar(250),
    precio double,
    imagen varchar(250)*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idProducto")
	private int idProducto;
	private String nombre;
	private String descripcion;
	private double precio;
	private String imagen;
	
	@OneToMany(mappedBy = "producto",cascade = CascadeType.ALL )
	List<LineaPedido> listaLineaPedido;

	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Producto(String nombre, String descripcion, double precio, String imagen,
			List<LineaPedido> listaLineaPedido) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagen = imagen;
		this.listaLineaPedido = listaLineaPedido;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<LineaPedido> getListaLineaPedido() {
		return listaLineaPedido;
	}

	public void setListaLineaPedido(List<LineaPedido> listaLineaPedido) {
		this.listaLineaPedido = listaLineaPedido;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", imagen=" + imagen + ", listaLineaPedido=" + listaLineaPedido + "]";
	}
	

	
}
