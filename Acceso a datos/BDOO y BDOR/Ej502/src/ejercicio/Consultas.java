package ejercicio;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consultas {

	private Connection con;
	private Statement st;
	
	private String user="postgres";
	private String pwd="abc1234.";
	private String url="jdbc:postgresql://localhost:5432/";
	private String bd="formula1";
	
	public Consultas() {
		con=null;
	}
	
	public void abrirConexion() {
		try {
			System.out.println("Abrimos conexion");
			con=DriverManager.getConnection(url + bd,user,pwd);
			st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void cerrarConexion() {
		try {
			System.out.println("Cerramos conexion");
			st.close();
			con.close();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Listar todos los equipos y sus directores
	public void listarEquipos() {
		System.out.println("Listar todos los equipos y sus directores ");

		String sql="SELECT nombre,director FROM equipos	";
		
		ResultSet rs;
		try {
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				System.out.println("Equipo: "+rs.getString("nombre")+"-"+rs.getString("director"));		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cerrarConexion();
		}
		System.out.println();
	
	}
	
	//Obtener los pilotos y sus equipos actuales
	public void listarPilotosEquipos() {
		System.out.println("Obtener los pilotos y sus equipos actuales");

		String sql="SELECT pilotos.nombre AS \"Piloto\",equipos.nombre AS \"Equipo\" FROM pilotos "
				+ "INNER JOIN equipos ON pilotos.equipo_id=equipos.equipo_id;";
		
		try {
			ResultSet rs=st.executeQuery(sql);
			
			while (rs.next()) {
				String piloto = rs.getString("Piloto");
				String equipo = rs.getString("Equipo");
				System.out.println(equipo + "\t" + piloto);
			}
		} catch (SQLException e) {
			cerrarConexion();
			e.printStackTrace();
		}
		System.out.println();

	}
	
	//Resultados de una carrera específica
	public void relsultadoCarrera() { //ponemos la carrera a mano
		System.out.println("Resultados de una carrera específica");
		
		String sql="SELECT resultados.posicion AS \"Posicion\", carreras.nombre AS \"Carrera\", pilotos.nombre AS \"Piloto\", resultados.tiempo AS \"Tiempo\""
				+ "FROM resultados "
				+ "INNER JOIN carreras ON resultados.carrera_id=carreras.carrera_id "
				+ "INNER JOIN pilotos ON resultados.piloto_id=pilotos.piloto_id "
				+ "WHERE carreras.nombre= 'Gran Premio de Australia' ";
		
		
		
		try {
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				String carrera = rs.getString("Carrera");
				String piloto = rs.getString("Piloto");
				int posicion = rs.getInt("Posicion");
				String tiempo = rs.getString("Tiempo");
				System.out.println(carrera + " - " + piloto + " - Posición: " + posicion + " - Tiempo: " + tiempo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cerrarConexion();
		}
		System.out.println();
			
	}
	
	//Piloto más viejo
	
	public void pilotoViejo() {
		System.out.println("Piloto más viejo");
		String sql="SELECT nombre,fecha_nacimiento FROM pilotos WHERE fecha_nacimiento = (SELECT MIN(fecha_nacimiento) FROM pilotos)";
		
		ResultSet rs;
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				System.out.println("Piloto: "+rs.getString("nombre")+" nacido el "+rs.getDate("fecha_nacimiento"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cerrarConexion();
		}
		System.out.println();
		
	}
	
	//Número de victorias por equipo
	public void victoriasEquipo() {
		System.out.println("Número de victorias por equipo");
		String query = "SELECT equipos.nombre AS \"Equipo\", COUNT(resultados.resultado_id) AS \"Victorias\" " +
                "FROM resultados " +
                "INNER JOIN pilotos ON resultados.piloto_id = pilotos.piloto_id " +
                "INNER JOIN equipos ON pilotos.equipo_id = equipos.equipo_id " +
                "WHERE resultados.posicion = 1 " +
                "GROUP BY equipos.nombre";
		
		ResultSet resultSet;
		try {
			resultSet = st.executeQuery(query);
			while (resultSet.next()) {
				String equipo = resultSet.getString("Equipo");
				int victorias = resultSet.getInt("Victorias");
				System.out.println(equipo + " - Victorias: " + victorias);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cerrarConexion();
		}
		System.out.println();

	
	}
}
