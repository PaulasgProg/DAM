package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="telefonos")
public class Telefonos implements Serializable{
	@Id
	@OneToOne
	private Autores autor;
	
	@Column(name="NumeroTf")
	private String numeroTf;
	

	public Telefonos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Telefonos(Autores autor, String numeroTf) {
		super();
		this.autor = autor;
		this.numeroTf = numeroTf;
	}


	public Autores getAutor() {
		return autor;
	}


	public void setAutor(Autores autor) {
		this.autor = autor;
	}



	public String getNumeroTf() {
		return numeroTf;
	}


	public void setNumeroTf(String numeroTf) {
		this.numeroTf = numeroTf;
	}

	@Override
	public String toString() {
		return "Telefonos [autor=" + autor + ", numeroTf=" + numeroTf + "]";
	}
	
	
	

}
