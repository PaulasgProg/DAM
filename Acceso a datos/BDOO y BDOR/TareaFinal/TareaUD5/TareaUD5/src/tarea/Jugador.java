package tarea;

import java.math.BigDecimal;

public class Jugador {
	private int dorsal;
	private String posicion;
	private Double altura;
	
	public Jugador(String info) {
		String[] info_split = info.substring(1, info.length()-1).split(",");
		this.dorsal = Integer.parseInt(info_split[0]);
		this.posicion = info_split[1].replace("\"", "");
		this.altura = Double.valueOf(info_split[2]);
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	@Override
	public String toString() {
		return "INFORMACIÓN DEL JUGADOR: \n "
				+ "	Dorsal=" + dorsal + "\n"
				+ "	Posicion=" + posicion + "\n"
				+ "	Altura=" + altura ;
	}
	
	
	
	

}
