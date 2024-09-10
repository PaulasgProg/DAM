package modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Actor extends Persona{
	
	private int numeroOscars;

	public Actor(String dni, String nombre) {
		super(dni, nombre);
		// TODO Auto-generated constructor stub
	}

	public Actor(String dni, String nombre,int numeroOscars) {
		super(dni, nombre);
		this.numeroOscars = numeroOscars;
	}

	public int getNumeroOscars() {
		return numeroOscars;
	}

	public void setNumeroOscars(int numeroOscars) {
		this.numeroOscars = numeroOscars;
	}
	
	

}
