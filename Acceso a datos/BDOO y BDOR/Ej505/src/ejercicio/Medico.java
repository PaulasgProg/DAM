package ejercicio;

public class Medico {
	private String codigo_medico;
	private String especialidad;
	
	public Medico(String info) {
		String[] info_split = info.substring(1, info.length() - 1).split(",");
		this.codigo_medico = info_split[0].replace("\"", "");
		this.especialidad = info_split[1];
	}

	public String getCodigo_medico() {
		return codigo_medico;
	}

	public void setCodigo_medico(String codigo_medico) {
		this.codigo_medico = codigo_medico;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "Medico [codigo_medico=" + codigo_medico + ", especialidad=" + especialidad + "]";
	}
	
	

}
