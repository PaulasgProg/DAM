package tarea;

public class Equipo {
	private String nombre;
	private String ciudad;
	private Persona entrenador;
	


	public Equipo(String nombre, String ciudad, Persona entrenador) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.entrenador = entrenador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Persona getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Persona entrenador) {
		this.entrenador = entrenador;
	}

	@Override
	public String toString() {
		return "Nombre del quipo=" + nombre +"\n"
				+ "Ciudad=" + ciudad +"\n"
				+ "Entrenador=" + "\n	"+entrenador ;
	}
	
	

}
