package tarea;

public class Persona {
	private String nombre;
	private int edad;
	
	public Persona(String info) {
		String[] info_split = info.substring(1, info.length()-1).split(",");
		this.nombre = info_split[0].replace("\"", "");
		this.edad = Integer.parseInt(info_split[1]);

	}
	public Persona(String[] info) {
		this.nombre = info[0].replace("\"", "");
		this.edad = Integer.parseInt(info[1]);
	}

	public Persona(String nombre, int edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
			return "INFORMACIÓN PERSONAL: \n" +
					"	Nombre: " + nombre + '\n' +
					"	Edad=" + edad ;
	}
	
	

}
