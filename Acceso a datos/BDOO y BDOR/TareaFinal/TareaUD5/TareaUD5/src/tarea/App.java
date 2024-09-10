package tarea;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.Scanner;

public class App {
	private static Scanner sc;
	private static Connection conn;

	public static void main(String[] args) {

		String url="jdbc:postgresql://localhost/futbol";
		String user="postgres";
		String pswd="abc1234.";
		try {
			conn=DriverManager.getConnection(url,user,pswd);
			sc = new Scanner(System.in);

            int opcion;
                    
            do {
                System.out.println("Menú de opciones:");
                System.out.println("1. Insertar equipo");
                System.out.println("2. Insertar jugador");
                System.out.println("3. Insertar Partido");
                System.out.println("4. Inscribir jugador en equipo");
                System.out.println("5. Modificar equipo");
                System.out.println("6. Modificar jugador");
                System.out.println("7. Modificar Partido");
                System.out.println("4. Desinscribir jugador en equipo");
                System.out.println("9. Eliminar equipo");
                System.out.println("10. Eliminar jugador");
                System.out.println("11. Eliminar Partido");
                System.out.println("12. Listar toda la información de un Equipo buscándolo por id.");
                System.out.println("13. Listar toda la información de todos los Equipos.");
                System.out.println("14. Listar la información de un Jugador buscándolo por id.");
                System.out.println("15. Listar la información de un Jugador buscándolo por nombre.");
                System.out.println("16. Buscar partidos en los que un determinado equipo jugara como local.");
                System.out.println("17. Buscar partidos en los que un determinado equipo jugara como visitante.");
                System.out.println("18. Obtener toda la información de los jugadores que jueguen en una determinada posición.");
                System.out.println("19. Obtener toda la información de los jugadores según su dorsal.");
                System.out.println("20. Obtener todos los partidos según la fecha.");
                System.out.println("21. Salir");

                opcion = pedirInt("Selecciona una opción: ");
                
                switch (opcion) {
            		
                    case 1: // Insertar equipo
                        insertEquipo();
                        break;
                    case 2: // Insertar jugador
                        insertJugador();
                        break;
                    case 3: // Insertar Partido
                        insertPartido();
                        break;
                    case 4: // Inscribir jugador en equipo
                        inscribirJugador();
                        break;
                    case 5: // Modificar equipo
                        modificarEquipo();
                        break;
                    case 6: // Modificar jugador
                        modificarJugador();
                        break;
                    case 7: // Modificar partido
                        modificarPartido();
                        break;
                    case 8: // Desinscribir jugador en equipo
                        desinscribirEquipo();
                        break;
                    case 9: // Eliminar equipo
                        eliminarEquipo();
                        break;
                    case 10: // Eliminar jugador
                        eliminarJugador();
                        break;
                    case 11: // Eliminar partido
                        eliminarPartido();
                        break;
                    case 12: // Listar toda la información de un Equipo buscándolo por id.
                    	listarInformacionEquipoId();
                        break;
                    case 13: // Listar toda la información de todos los Equipos.
                        listarInformacionEquipos();
                        break;
                    case 14: // Listar la información de un Jugador buscándolo por id.
                        listarInformacionJugadorID();
                        break;
                    case 15: // Listar la información de un Jugador buscándolo por nombre.
                        listarInformacionJugadorNombre();
                        break;
                    case 16: // Buscar partidos en los que un determinado equipo jugara como local.
                        listarPartidosEquipoLocalId();
                        break;
                    case 17: // Buscar partidos en los que un determinado equipo jugara como visitante.
                        listarPartidosEquipoVisitId();
                        break;
                    case 18: // Obtener toda la información de los jugadores que jueguen en una determinada posición.
                        listarInformacionJugadoresPosicion();
                        break;
                    case 19: // Obtener toda la información de los jugadores según su dorsal.
                        listarInformacionJugadoresDorsal();
                        break;
                    case 20: // Obtener todos los partidos según la fecha
                        listarPartidosFecha();
                        break;
                    case 21: //salir
          
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }while(opcion != 21);
            sc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void listarPartidosFecha() {
		String fecha = pedirString("Escribe la fecha (YYYY-MM-DD): ");
		String sql="SELECT * FROM objetos.Partidos WHERE fecha=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setDate(1, java.sql.Date.valueOf(fecha));
			
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				System.out.println("\n ID PARTIDO: "+ rs.getInt("partido_id"));
				System.out.println("	Fecha: "+rs.getDate("fecha"));
				System.out.println("	id Equipo local : "+rs.getInt("equipo_local"));
				System.out.println("	id Equipo visitante: "+rs.getInt("equipo_visitante"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void listarInformacionJugadoresDorsal() {
		int dorsal=pedirInt("Introduce el dorsal: ");
		String sql="SELECT * FROM objetos.Jugadores WHERE (jugador_info).dorsal=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, dorsal);
			
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Persona p=new Persona(rs.getString("datos_personales"));
				Jugador j=new Jugador(rs.getString("jugador_info"));
				System.out.println("ID jugador: "+rs.getInt("jugador_id"));
				System.out.println(p.toString());
				System.out.println(j.toString());
				int id=rs.getInt("equipo_id");
				String equipo=listarNombreEquipoId(id);
				System.out.println("	Equipo= "+equipo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private static void listarInformacionJugadoresEquipo(int idEquipo) {
		
		String sql="SELECT * FROM objetos.Jugadores WHERE equipo_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, idEquipo);
			
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Persona p=new Persona(rs.getString("datos_personales"));
				Jugador j=new Jugador(rs.getString("jugador_info"));
				System.out.println("	ID jugador: "+rs.getInt("jugador_id"));
				System.out.println("	"+p.toString());
				System.out.println("	"+j.toString()+"\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void listarInformacionJugadoresPosicion() {
		String posicion=pedirString("Introduce la posición: ");
		String sql="SELECT * FROM objetos.Jugadores WHERE (jugador_info).posicion=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, posicion);
			
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Persona p=new Persona(rs.getString("datos_personales"));
				Jugador j=new Jugador(rs.getString("jugador_info"));
				System.out.println("ID jugador: "+rs.getInt("jugador_id"));
				System.out.println(p.toString());
				System.out.println(j.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void listarPartidosEquipoVisitId() {
		int id=pedirInt("Introduce el id del equipo visitante: ");
		String sql="SELECT * FROM objetos.Partidos WHERE equipo_visitante=?";
		
		if (existeEquipo(id)) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				
				ResultSet rs=ps.executeQuery();
				while (rs.next()) {
					System.out.println("ID PARTIDO: "+ rs.getInt("partido_id"));
					System.out.println("	Fecha: "+rs.getDate("fecha"));
					int idlocal=rs.getInt("equipo_local");
					int idvisitante=rs.getInt("equipo_visitante");
					System.out.println("	Equipo local : "+idlocal+" ("+listarNombreEquipoId(idlocal)+")");
					System.out.println("	Equipo visitante: "+idvisitante+" ("+listarNombreEquipoId(idvisitante)+")");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	private static void listarPartidosEquipoLocalId() {
		int id=pedirInt("Introduce el id del equipo local: ");
		String sql="SELECT * FROM objetos.Partidos WHERE equipo_local=?";
		
		if (existeEquipo(id)) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				
				ResultSet rs=ps.executeQuery();
				while (rs.next()) {
					System.out.println("ID PARTIDO: "+ rs.getInt("partido_id"));
					System.out.println("	Fecha: "+rs.getDate("fecha"));
					int idlocal=rs.getInt("equipo_local");
					int idvisitante=rs.getInt("equipo_visitante");
					System.out.println("	Equipo local : "+idlocal+" ("+listarNombreEquipoId(idlocal)+")");
					System.out.println("	Equipo visitante: "+idvisitante+" ("+listarNombreEquipoId(idvisitante)+")");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

	private static void listarInformacionJugadorNombre() {
		String nombre=pedirString("Introduce el nombre del jugadpr: ");
		
		String sql="SELECT * FROM objetos.Jugadores WHERE (datos_personales).nombre=?";

		PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, nombre);
				
				ResultSet rs=ps.executeQuery();
				while (rs.next()) {
					Persona persona=new Persona(rs.getString("datos_personales"));
					Jugador jugador=new Jugador(rs.getString("jugador_info"));
					System.out.println(jugador.toString());
					int id=rs.getInt("equipo_id");
					String equipo=listarNombreEquipoId(id);
					System.out.println("	Equipo= "+equipo);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private static void listarInformacionJugadorID() {
		int id=pedirInt("Introduce el id del jugadpr: ");
		
		String sql="SELECT * FROM objetos.Jugadores WHERE jugador_id=?";
		if (existeJugador(id)) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				
				ResultSet rs=ps.executeQuery();
				while (rs.next()) {
					Persona persona=new Persona(rs.getString("datos_personales"));
					Jugador jugador=new Jugador(rs.getString("jugador_info"));
					System.out.println(persona.toString());
					System.out.println(jugador.toString());
					String equipo=listarNombreEquipoId(id);
					System.out.println("	Equipo= "+equipo);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	

	private static void listarInformacionEquipos() {

		String sql="SELECT equipo_id, (equipo_info).nombre, (equipo_info).ciudad, (equipo_info).entrenador FROM objetos.Equipos";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
				
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				int id=rs.getInt("equipo_id");
				String nombre=rs.getString("nombre");
				String ciudad = rs.getString("ciudad");
			    Persona entrenador=new Persona(rs.getString("entrenador"));
			    
			    Equipo equipo = new Equipo(nombre, ciudad, entrenador);
			    System.out.println("EQUIPO ID "+id);
				System.out.println(equipo.toString());		
				System.out.println("\nJUGADORES: ");
				listarInformacionJugadoresEquipo(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

	private static void listarInformacionEquipoId() {
		int id=pedirInt("Introduce el id del equipo: ");
		
		String sql = "SELECT (equipo_info).nombre, (equipo_info).ciudad, (equipo_info).entrenador FROM objetos.Equipos WHERE equipo_id=?";


		if (existeEquipo(id)) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				
				ResultSet rs=ps.executeQuery();
				while (rs.next()) {
					String nombre = rs.getString("nombre");
				    String ciudad = rs.getString("ciudad");
				    Persona entrenador=new Persona(rs.getString("entrenador"));
				  
	
				    Equipo equipo = new Equipo(nombre, ciudad, entrenador);
				    System.out.println(equipo.toString());

		
				}
				System.out.println("\nJUGADORES: ");
				listarInformacionJugadoresEquipo(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	private static String listarNombreEquipoId(int id) {
	
		String sql = "SELECT (equipo_info).nombre FROM objetos.Equipos WHERE equipo_id=?";


		if (existeEquipo(id)) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				
				ResultSet rs=ps.executeQuery();
				while (rs.next()) {
					String nombre = rs.getString("nombre");
				    
					return nombre;
				}

				return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return null;
		
	}

	private static void eliminarPartido() {
		int id=pedirInt("Introduce el id del partido a eliminar: ");
		String sql="DELETE FROM objetos.Partidos WHERE partido_id=?";
		
		if (existePartido(id)) {
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, id);
				
				int row=ps.executeUpdate();
				if(row>0) {
					System.out.println("Se ha borrado correctamente");
				}else {
					System.out.println("No se ha podido borrar");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
	}

	private static void eliminarJugador() {
		int id=pedirInt("Introduce el id del jugador a eliminar: ");
		String sql="DELETE FROM objetos.Jugadores WHERE jugador_id=?";
		
		if (existeJugador(id)) {
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, id);
				
				int row=ps.executeUpdate();
				if(row>0) {
					System.out.println("Se ha borrado correctamente");
				}else {
					System.out.println("No se ha podido borrar");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

	private static void eliminarEquipo() {
		int id=pedirInt("Introduce el id del equipo a eliminar: ");
		String sql="DELETE FROM objetos.Equipos WHERE equipo_id=?";
		
		if (existeEquipo(id)) {
			DesinscribirjugadoresPorEquipoId(id);
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, id);
				
				int row=ps.executeUpdate();
				if(row>0) {
					System.out.println("Se ha borrado correctamente");
				}else {
					System.out.println("No se ha podido borrar");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	private static void desinscribirEquipo() {
		int id=pedirInt("Introduce el id del jugador a desinscribir: ");
		String sql="UPDATE objetos.Jugadores SET equipo_id=? WHERE jugador_id=?";
		
		if (existeJugador(id)) {
			if (JugadorInscrito(id)) {
				try {
					PreparedStatement ps=conn.prepareStatement(sql);
					ps.setInt(1, 0);
					ps.setInt(2, id);
					
					int row=ps.executeUpdate();
					if(row>0) {
						System.out.println("Se ha modificado correctamente");
					}else {
						System.out.println("No se ha podido modificar");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}else {
				System.out.println("No está incrito en ningún equipo");
			}
		}	
	}

	private static void modificarPartido() {
		int id=pedirInt("Introduce el id del partido a modificar: ");
		if (existePartido(id)) {
			String fecha=pedirString("Introduce una fecha (dd-mm-aaaa)");
			int equipo_local=pedirInt("Introduce el id del equipo local: ");
			int equipo_visit=pedirInt("Introduce el id del equipo visitante: ");
			
			String sql="UPDATE objetos.Partidos SET fecha=?, equipo_local=?, equipo_visitante=? "
					+ "WHERE partido_id=?";
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, fecha);
				ps.setInt(2, equipo_local);
				ps.setInt(3, equipo_visit);
				ps.setInt(4, id);
				
				int row=ps.executeUpdate();
				if(row>0) {
					System.out.println("Se ha añadido correctamente");
				}else {
					System.out.println("No se ha podido añadir");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

	private static void modificarJugador() {
		int id=pedirInt("Introduce el id del jugador a modificar: ");
		
		
		if (existeJugador(id)) {
			String nombre=pedirString("Introduce el nombre del jugador: ");
			int edad=pedirInt("Introduce la edad del jugador: ");
			int dorsal=pedirInt("Introduce el dorsal: ");
			String posicion=pedirString("Introduce la posición en la que juega: ");
			Double altura=pedirDec("Introduce la altura del jugador: ");
			
			int idEquipo=pedirInt("Introduce el id del equipo en el que juega: ");
			if(existeEquipo(idEquipo)) {
				String sql="UPDATE objetos.Jugadores SET datos_personales=ROW(?,?), jugador_info=ROW(?,?,?), equipo_id=? "
						+ "WHERE jugador_id=?";
				
				try {
					PreparedStatement ps=conn.prepareStatement(sql);
					ps.setString(1, nombre);
					ps.setInt(2, edad);
					ps.setInt(3, dorsal);
					ps.setString(4, posicion);
					ps.setDouble(5, altura);
					ps.setInt(6, idEquipo);
					ps.setInt(7, id);
					
					int row=ps.executeUpdate();
					if(row>0) {
						System.out.println("Se ha modificado correctamente");
					}else {
						System.out.println("No se ha podido modificar");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}		
	}

	private static void modificarEquipo() {
		int id=pedirInt("Introduce el id del equipo a modificar: ");
	
		
		if(existeEquipo(id)) {
			String nombre=pedirString("Introduce el nombre del equipo: ");
			String ciudad=pedirString("Introduce la ciudad del equipo: ");
			String nombreEntrenador=pedirString("Indtroduce el nombre el entrenador: ");
			int edad=pedirInt("Introduce la edad del entrenador: ");
			
			String sql="UPDATE objetos.Equipos SET equipo_info=(ROW(?,?,ROW(?,?))) WHERE equipo_id=?";
			
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, nombre);
				ps.setString(2, ciudad);
				ps.setString(3, nombreEntrenador);
				ps.setInt(4, edad);
				ps.setInt(5, id);
				
				int row=ps.executeUpdate();
				if(row>0) {
					System.out.println("Se ha modificado correctamente");
				}else {
					System.out.println("No se ha modificado añadir");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
	}

	private static void inscribirJugador() {
		int id=pedirInt("Introduce el id del jugador a inscribir: ");
		String sql="UPDATE objetos.Jugadores SET equipo_id=? WHERE jugador_id=?";
		
		if (existeJugador(id)) {
			if (JugadorInscrito(id)) {
				System.out.println("Ya está inscrito en un equipo");
			}else {
				int idEquipo=pedirInt("Introduce el id del equipo: ");
				try {
					PreparedStatement ps=conn.prepareStatement(sql);
					ps.setInt(1, idEquipo);
					ps.setInt(2, id);
					
					int row=ps.executeUpdate();
					if(row>0) {
						System.out.println("Se ha modificado correctamente");
					}else {
						System.out.println("No se ha podido modificar");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
		}
		
	
	}

	private static void insertPartido() {

		String fecha=pedirString("Introduce una fecha (dd-mm-aaaa)");
		int equipo_local=pedirInt("Introduce el id del equipo local: ");
		int equipo_visit=pedirInt("Introduce el id del equipo visitante: ");
		
		String sql="INSERT INTO objetos.Partidos(fecha, equipo_local, equipo_visitante) "
				+ "VALUES(?,?,?)";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, fecha);
			ps.setInt(2, equipo_local);
			ps.setInt(3, equipo_visit);
			
			int row=ps.executeUpdate();
			if(row>0) {
				System.out.println("Se ha añadido correctamente");
			}else {
				System.out.println("No se ha podido añadir");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void insertJugador() {
		String nombre=pedirString("Introduce el nombre del jugador: ");
		int edad=pedirInt("Introduce la edad del jugador: ");
		int dorsal=pedirInt("Introduce el dorsal: ");
		String posicion=pedirString("Introduce la posición en la que juega: ");
		Double altura=pedirDec("Introduce la altura del jugador: ");
		int idEquipo=pedirInt("Introduce el id del equipo en el que juega: ");
		
		
		String sql="INSERT INTO objetos.Jugadores(datos_personales, jugador_info, equipo_id) "
				+ "VALUES (ROW(?,?),ROW(?,?,?),?)";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setInt(2, edad);
			ps.setInt(3, dorsal);
			ps.setString(4, posicion);
			ps.setDouble(5, altura);
			ps.setInt(6, idEquipo);
			
			int row=ps.executeUpdate();
			if(row>0) {
				System.out.println("Se ha añadido correctamente");
			}else {
				System.out.println("No se ha podido añadir");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void insertEquipo() {
		String nombre=pedirString("Introduce el nombre del equipo: ");
		String ciudad=pedirString("Introduce la ciudad del equipo: ");
		String nombreEntrenador=pedirString("Indtroduce el nombre el entrenador: ");
		int edad=pedirInt("Introduce la edad del entrenador: ");
		
		String sql="INSERT INTO objetos.Equipos(equipo_info) VALUES (ROW(?,?,ROW(?,?)))";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setString(2, ciudad);
			ps.setString(3, nombreEntrenador);
			ps.setInt(4, edad);
			
			int row=ps.executeUpdate();
			if(row>0) {
				System.out.println("Se ha añadido correctamente");
			}else {
				System.out.println("No se ha podido añadir");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private static int pedirInt(String mensaje) {
		 while(true) {
	            
	            try {
	            	System.out.print(mensaje);
	                return sc.nextInt();
	            }catch (Exception e) {
	            }finally {
	            	sc.nextLine();
				}
	        }
		
	}
	private static Double pedirDec(String mensaje) {
		 while(true) {
	            
	            try {
	            	System.out.print(mensaje);
	                return sc.nextDouble();
	                
	            }catch (Exception e) {
	           
	            }finally {
	            	sc.nextLine();
	            }
	        }
		
	}
	private static String pedirString(String mensaje) {
		while (true) {
	        try {
	        	System.out.print(mensaje);
	            return sc.nextLine();
	        } catch (Exception e) {
	            // Manejar la excepción, si es necesario
	        }        
	    }
	}
	private static Boolean JugadorInscrito(int id) {
		String sql="SELECT equipo_id FROM objetos.Jugadores WHERE jugador_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int idEquipo=rs.getInt("equipo_id");
				if(idEquipo==0) {
					return false;
				}else {
					return true;
				}
			}else {
				System.out.println("No existe ese jugador");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		
			
	}
	private static Boolean existeEquipo(int id) {
		String sql="SELECT * FROM objetos.Equipos WHERE equipo_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				return true;
				
			}
			System.out.println("No existe ese equipo");
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
			
	}
	private static Boolean existeJugador(int id) {
		String sql="SELECT * FROM objetos.Jugadores WHERE jugador_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
				
			}else {
				System.out.println("No existe ese jugador");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
			
	}

	private static Boolean existePartido(int id) {
		String sql="SELECT * FROM objetos.Partidos WHERE partido_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
				
			}else {
				System.out.println("No existe ese partido");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
			
	}
	private static void DesinscribirjugadoresPorEquipoId(int id) {
		String sql="UPDATE objetos.Jugadores SET equipo_id=null WHERE equipo_id=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			int rows=ps.executeUpdate();
			System.out.println("Se han desinscrito "+rows+" jugadores");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
