package entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lineapedido")
public class LineaPedido {

	/*idLineaPedido int primary key AUTO_INCREMENT,
    idPedido int,
    idProducto int,
    cantidad int,
    foreign key (idProducto) references producto(idProducto),
    foreign key (idPedido) references pedido(idPedido)*/
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name="idLineaPedido")
	private int idLineaPedido;
	private int cantidad;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="idPedido")
	private Pedido pedido;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="idProducto")
	private Producto producto;

	public LineaPedido(int cantidad, Pedido pedido, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.pedido = pedido;
		this.producto = producto;
	}

	public LineaPedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdLineaPedido() {
		return idLineaPedido;
	}

	public void setIdLineaPedido(int idLineaPedido) {
		this.idLineaPedido = idLineaPedido;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "LineaPedido [idLineaPedido=" + idLineaPedido + ", cantidad=" + cantidad + ", pedido=" + pedido
				+ ", producto=" + producto + "]";
	}
	
	
}
