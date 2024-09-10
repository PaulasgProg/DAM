package ejercicio616;

import java.util.Date;

import org.bson.types.ObjectId;

public class Carrera {
	//nombre del gran premio, la fecha y el ganador. Añade dos ejemplos.

	private ObjectId id;
	private String nombre;
	private Date fecha;
	private Piloto ganador;
	public Carrera() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Carrera(ObjectId id, String nombre, Date fecha, Piloto ganador) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.ganador = ganador;
	}

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Piloto getGanador() {
		return ganador;
	}
	public void setGanador(Piloto ganador) {
		this.ganador = ganador;
	}
	@Override
	public String toString() {
		return "Carrera{id=" + id + 
				", nombre=" + nombre + 
				", fecha=" + fecha + 
				", ganador=" + ganador + 
				"}";
	}
	
	

}
