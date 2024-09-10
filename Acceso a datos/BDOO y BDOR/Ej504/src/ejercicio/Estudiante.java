package ejercicio;

public class Estudiante {
	private String matricula;
	private String carrera;
	
	public Estudiante(String matricula, String carrera) {
		super();
		this.matricula = matricula;
		this.carrera = carrera;
	}

	public Estudiante(String string) {
		// TODO Auto-generated constructor stub
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	@Override
	public String toString() {
		return "Estudiante [matricula=" + matricula + ", carrera=" + carrera + "]";
	}
	
	

}
