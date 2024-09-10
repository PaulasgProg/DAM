package ejercicio;

public class Paciente {
	private String numero_historia;
	private String grupo_sanguineo;
	
	public Paciente(String info) {
		String[] info_split = info.substring(1, info.length() - 1).split(",");
		this.numero_historia = info_split[0].replace("\"", "");
		this.grupo_sanguineo = info_split[1];
	}

	public String getNumero_historia() {
		return numero_historia;
	}

	public void setNumero_historia(String numero_historia) {
		this.numero_historia = numero_historia;
	}

	public String getGrupo_sanguineo() {
		return grupo_sanguineo;
	}

	public void setGrupo_sanguineo(String grupo_sanguineo) {
		this.grupo_sanguineo = grupo_sanguineo;
	}

	@Override
	public String toString() {
		return "Paciente [numero_historia=" + numero_historia + ", grupo_sanguineo=" + grupo_sanguineo + "]";
	}
	
	
	

}
