package modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Actriz extends Persona{

	private int numeroMejorActriz;

	public Actriz(String dni, String nombre) {
		super(dni, nombre);
		// TODO Auto-generated constructor stub
	}

	public Actriz(String dni, String nombre, int numeroMejorActriz) {
		super(dni, nombre);
		this.numeroMejorActriz = numeroMejorActriz;
	}

	public int getNumeroMejorActriz() {
		return numeroMejorActriz;
	}

	public void setNumeroMejorActriz(int numeroMejorActriz) {
		this.numeroMejorActriz = numeroMejorActriz;
	}
	
	
}
