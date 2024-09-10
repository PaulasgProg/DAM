package ejercicio;

public class App {

	public static void main(String[] args) {
		Consultas consultas=new Consultas();
		consultas.abrirConexion();
		
		consultas.listarEquipos();
		consultas.listarPilotosEquipos();
		consultas.pilotoViejo();
		consultas.relsultadoCarrera();
		consultas.victoriasEquipo();
		
		consultas.cerrarConexion();

	}

}
