package entidades;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {

	/*idPedido int primary key AUTO_INCREMENT,
    idCliente int, 
    fecha timestamp default current_timestamp,
    foreign key (idCliente) references cliente(idCliente)*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPedido")
	private int idPedido;
	private Date fecha;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL)
	private List<LineaPedido> listaLineaPedido;

	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pedido(Date fecha, Cliente cliente) {
		super();
		this.fecha = fecha;
		this.cliente = cliente;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
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

	public List<LineaPedido> getListaLineaPedido() {
		return listaLineaPedido;
	}

	public void setListaLineaPedido(List<LineaPedido> listaLineaPedido) {
		this.listaLineaPedido = listaLineaPedido;
	}
	
	
	
	
}
