package ejercicio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class App {

	private static Scanner sc;
	private static Connection conn;
	public static void main(String[] args) {
		String url="jdbc:postgresql://localhost/hospital";
		String user="postgres";
		String pswd="abc1234.";
		try {
			conn=DriverManager.getConnection(url,user,pswd);
			sc = new Scanner(System.in);

            int opcion;
                    
            do {
                System.out.println("Menú de opciones:");
                System.out.println("1. Insertar Medico");
                System.out.println("2. Insertar Paciente");
                System.out.println("3. Insertar Examen Medico");
                System.out.println("4. Insertar Cita Medica");
                System.out.println("5. Modificar Medico");
                System.out.println("6. Modificar Paciente");
                System.out.println("7. Modificar Examen Medico");
                System.out.println("8. Modificar Cita Medica");
                System.out.println("9. Eliminar Medico");
                System.out.println("10. Eliminar Paciente");
                System.out.println("11. Eliminar Examen Medico");
                System.out.println("12. Eliminar Cita Medica");
                System.out.println("13. Listar información de un Paciente por ID");
                System.out.println("14. Listar información de todos los Pacientes");
                System.out.println("15. Listar información de un Medico por ID");
                System.out.println("16. Listar información de todos los Medicos");
                System.out.println("17. Listar información de un CitaMedica por ID de Paciente");
                System.out.println("18. Listar información de un CitaMedica por ID de Medico");
                System.out.println("19. Listar información de todos los Examenes Medicos de un Paciente");
                System.out.println("20. Listar información de un Paciente buscándolo por grupo sanguineo");
                System.out.println("21. Listar información de un Medico que atendia a un determinado Paciente");
                System.out.println("22. Salir");

                System.out.print("Selecciona una opción: ");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1: // Insertar Medico
                        insertMedico();
                        break;
                    case 2: // Insertar Paciente
                        insertPaciente();
                        break;
                    case 3: // Insertar Examen Medico
                        insertExamenMedico();
                        break;
                    case 4: // Insertar Cita Medica
                        insertCitaMedica();
                        break;
                    case 5: // Modificar Medico
                        modificarMedico();
                        break;
                    case 6: // Modificar Paciente
                        modificarPaciente();
                        break;
                    case 7: // Modificar Examen Medico
                        modificarExamenMedico();
                        break;
                    case 8: // Modificar Cita Medica
                        modificarCitaMedica();
                        break;
                    case 9: // Eliminar Medico
                        eliminarMedico();
                        break;
                    case 10: // Eliminar Paciente
                        eliminarPaciente();
                        break;
                    case 11: // Eliminar Examen Medico
                        eliminarExamenMedico();
                        break;
                    case 12: //  Eliminar Cita Medica
                        eliminarCitaMedica();
                        break;
                    case 13: // Listar información de un Paciente por ID
                        listarInformacionPacientePorId();
                        break;
                    case 14: // Listar información de todos los Pacientes
                        listarInformacionTodosPacientes();
                        break;
                    case 15: //  Listar información de un Medico por ID
                        listarInformacionMedicoPorId();
                        break;
                    case 16: // Listar información de todos los Medicos
                        listarInformacionTodosMedicos();
                        break;
                    case 17: // Listar información de un CitaMedica por ID de Paciente
                        listarInformacionCitaMedicaPorIdPaciente();
                        break;
                    case 18: // Listar información de un CitaMedica por ID de Medico
                        listarInformacionCitaMedicaPorIdMedico();
                        break;
                    case 19: // Listar información de todos los Examenes Medicos de un Paciente
                        listarInformacionTodosExamenesMedicosPaciente();
                        break;
                    case 20: // Listar información de un Paciente buscándolo por grupo sanguineo
                        listarInformacionPacientePorGrupoSanguineo();
                        break;
                    case 21: // Listar información de un Medico que atendia a un determinado Paciente
                        listarInformacionMedicoQueAtiendioPaciente();
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }while(opcion != 22);
            sc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static void insertMedico() {
		String nombre=pedirString("Introduce un nombre");
		int edad=pedirInt("Introduce la edad");
		String codigo=pedirString("Introduce el codigo");
		String especialidad=pedirString("Introduce especialidad");
		
		String sql="INSERT INTO objetos.Medicos(datos_personales,medico_info) VALUES (ROW(?,?),ROW(?,?))";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setInt(2, edad);
			ps.setString(3, codigo);
			ps.setString(4, especialidad);
			
			int row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void insertPaciente() {
		String nombre=pedirString("Introduce un nombre");
		int edad=pedirInt("Introduce la edad");
		String numero=pedirString("Introduce el numero historial");
		String grSang=pedirString("Introduce grupo sanguineo");
		
		String sql="INSERT INTO objetos.Pacientes(datos_personales,paciente_info) VALUES (ROW(?,?),ROW(?,?))";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setInt(2, edad);
			ps.setString(3, numero);
			ps.setString(4, grSang);
			
			int row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void insertExamenMedico() {
		int pacienteId = pedirInt("Ingrese el ID del paciente para el examen médico: ");
        String nombreExamen = pedirString("Ingrese el nombre del examen médico: ");
        String fechaRealizacion = pedirString("Ingrese la fecha de realización del examen médico (YYYY-MM-DD): ");
        String resultado = pedirString("Ingrese el resultado del examen médico: ");

		
		String sql="INSERT INTO objetos.ExamenesMedicos(paciente_id,examen_info) VALUES (?,ROW(?,?,?))";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, pacienteId);
			ps.setString(2, nombreExamen);
			ps.setString(3, fechaRealizacion);
			ps.setString(4, resultado);
			
			int row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void insertCitaMedica() {
		String fechaHora = pedirString("Ingrese la fecha y hora de la cita médica (YYYY-MM-DD HH:mm:ss): ");
        int pacienteId = pedirInt("Ingrese el ID del paciente para la cita médica: ");
        int medicoId = pedirInt("Ingrese el ID del médico para la cita médica: ");

        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO objetos.CitasMedicas (fecha_hora, paciente_id, medico_id) VALUES (?, ?, ?)"
            );

            statement.setString(1, fechaHora);
            statement.setInt(2, pacienteId);
            statement.setInt(3, medicoId);

            statement.executeUpdate();
            System.out.println("Cita médica insertada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	private static void modificarMedico() {
		 int medicoId = pedirInt("Ingrese el ID del médico a modificar: ");
		 
		 String nombre=pedirString("Introduce un nombre");
		 int edad=pedirInt("Introduce la edad");
		 String codigo=pedirString("Introduce el codigo");
		 String especialidad=pedirString("Introduce especialidad");
		 
		 String sql="UPDATE objetos.Medicos SET datos_personales=ROW(?,?), medico_info=ROW(?,?) "
		 		+ "WHERE medico_id=?";
		 try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setInt(2, edad);
			ps.setString(3, codigo);
			ps.setString(4, especialidad);
			ps.setInt(5, medicoId);
			
			int row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private static void modificarPaciente() {
		int Id = pedirInt("Ingrese el ID del paciente a modificar: ");
		String nombre=pedirString("Introduce un nombre");
		int edad=pedirInt("Introduce la edad");
		String numero=pedirString("Introduce el numero historial");
		String grSang=pedirString("Introduce grupo sanguineo");
		
		String sql="UPDATE objetos.Pacientes SET datos_personales=ROW(?,?), paciente_info=ROW(?,?) "
		 		+ "WHERE paciente_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setInt(2, edad);
			ps.setString(3, numero);
			ps.setString(4, grSang);
			ps.setInt(5, Id);
			
			int row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private static void modificarExamenMedico() {
		int Id = pedirInt("Ingrese el ID del examen a modificar: ");
		int pacienteId = pedirInt("Ingrese el ID del paciente para el examen médico: ");
        String nombreExamen = pedirString("Ingrese el nombre del examen médico: ");
        String fechaRealizacion = pedirString("Ingrese la fecha de realización del examen médico (YYYY-MM-DD): ");
        String resultado = pedirString("Ingrese el resultado del examen médico: ");

		
		String sql="UPDATE objetos.ExamenesMedicos SET paciente_id=?, examen_info=ROW(?,?,?) WHERE examen_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, pacienteId);
			ps.setString(2, nombreExamen);
			ps.setString(3, fechaRealizacion);
			ps.setString(4, resultado);
			ps.setInt(1, Id);
			
			int row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void modificarCitaMedica() {
		int Id = pedirInt("Ingrese el ID de la cita a modificar: ");
		String fechaHora = pedirString("Ingrese la fecha y hora de la cita médica (YYYY-MM-DD HH:mm:ss): ");
        int pacienteId = pedirInt("Ingrese el ID del paciente para la cita médica: ");
        int medicoId = pedirInt("Ingrese el ID del médico para la cita médica: ");

        try {
        	String sql="UPDATE objetos.CitasMedicas SET fecha_hora=?, medico_id=?, paciente_id=? WHERE cita_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, fechaHora);
            statement.setInt(2, pacienteId);
            statement.setInt(3, medicoId);
            statement.setInt(3, Id);

            statement.executeUpdate();
            System.out.println("Cita médica modificads correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	private static void eliminarMedico() {
		int Id = pedirInt("Ingrese el ID del medico a eliminar: ");
		String sql="DELETE FROM objetos.Medicos WHERE medico_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Id);
			int row=ps.executeUpdate();
			if (row> 0) {
                System.out.println("Medico eliminado correctamente.");
            } else {
                System.out.println("No se encontró un médico con el ID especificado.");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private static void eliminarPaciente() {
		int Id = pedirInt("Ingrese el ID del paciente a eliminar: ");
		String sql="DELETE FROM objetos.Pacientes WHERE paciente_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Id);
			int row=ps.executeUpdate();
			if (row > 0) {
                System.out.println("paciente eliminado correctamente.");
            } else {
                System.out.println("No se encontró un paciente con el ID especificado.");
            }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	private static void eliminarExamenMedico() {
		int Id = pedirInt("Ingrese el ID del examen a eliminar: ");
		String sql="DELETE FROM objetos.ExamenesMedicos WHERE examen_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Id);
			int row=ps.executeUpdate();
			
			if (row > 0) {
                System.out.println("examen eliminado correctamente.");
            } else {
                System.out.println("No se encontró un examen con el ID especificado.");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	private static void eliminarCitaMedica() {
		int Id = pedirInt("Ingrese el ID de la cita a eliminar: ");
		String sql="DELETE FROM objetos.CitasMedicas WHERE cita_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Id);
			int row=ps.executeUpdate();
			if (row > 0) {
                System.out.println("cita eliminado correctamente.");
            } else {
                System.out.println("No se encontró una cita con el ID especificado.");
            }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	private static void listarInformacionPacientePorId() {
		int Id = pedirInt("Ingrese el ID del paciente a consultar: ");
		
		String sql="SELECT p FROM objetos.Pacientes p WHERE paciente_id=?";
		
		   try {
	            PreparedStatement statement = conn.prepareStatement(sql);

	            statement.setInt(1, Id);

	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                System.out.println("\nID: " + resultSet.getInt("paciente_id"));
	                System.out.println("Nombre: " + resultSet.getString("datos_personales.nombre"));
	                System.out.println("Edad: " + resultSet.getInt("datos_personales.edad"));
	                System.out.println("Número de Historia: " + resultSet.getString("paciente_info.numero_historia"));
	                System.out.println("Grupo Sanguíneo: " + resultSet.getString("paciente_info.grupo_sanguineo"));
	            } else {
	                System.out.println("No se encontró un paciente con el ID especificado.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}
	private static void listarInformacionTodosPacientes() {
		String sql="SELECT * FROM objetos.Pacientes";
		
		try {
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	
                System.out.println("\nID: " + resultSet.getInt("paciente_id"));
                //System.out.println("Nombre: " + resultSet.getString("datos_personales.nombre"));
                //System.out.println("Edad: " + resultSet.getInt("datos_personales.edad"));
                Persona persona=new Persona(resultSet.getString("datos_personales"));
                System.out.println(persona.toString());
                //System.out.println("Número de Historia: " + resultSet.getString("paciente_info.numero_historia"));
                //System.out.println("Grupo Sanguíneo: " + resultSet.getString("paciente_info.grupo_sanguineo"));
                Paciente paciente=new Paciente(resultSet.getString("paciente_info"));
                System.out.println(paciente.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	private static void listarInformacionMedicoPorId() {
		int Id = pedirInt("Ingrese el ID del medico a consultar: ");
		String sql="SELECT m FROM objetos.Medicos m WHERE medico_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Id);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("\nID: " + rs.getInt("paciente_id"));
                System.out.println("Nombre: " + rs.getString("datos_personales.nombre"));
                System.out.println("Edad: " + rs.getInt("datos_personales.edad"));
                System.out.println("Codigo: " + rs.getString("medico_info.codigo_medico"));
                System.out.println("Grupo Sanguíneo: " + rs.getString("medico_info.especialidad"));
			}else {
				System.out.println("No se encontró");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void listarInformacionTodosMedicos() {
	
		String sql="SELECT m FROM objetos.Medicos m ";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("\nID: " + rs.getInt("paciente_id"));
                System.out.println("Nombre: " + rs.getString("datos_personales.nombre"));
                System.out.println("Edad: " + rs.getInt("datos_personales.edad"));
                System.out.println("Codigo: " + rs.getString("medico_info.codigo_medico"));
                System.out.println("Grupo Sanguíneo: " + rs.getString("medico_info.especialidad"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void listarInformacionCitaMedicaPorIdPaciente() {
		int Id = pedirInt("Ingrese el ID del paciente a consultar: ");
		String sql="SELECT c FROM objetos.CitasMedicas c WHERE paciente_id=?";
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("\nID: " + rs.getInt("paciente_id"));
	            System.out.println("idCita: " + rs.getString("cita_id"));
	            System.out.println("idMedico: " + rs.getInt("medico_id"));
	            System.out.println("Fecha y hora: " + rs.getString("fecha_hora"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	private static void listarInformacionCitaMedicaPorIdMedico() {
		int Id = pedirInt("Ingrese el ID del medico a consultar: ");
		
		String sql="SELECT c FROM objetos.CitasMedicas c WHERE medico_id=?";
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("\nID: " + rs.getInt("paciente_id"));
	            System.out.println("idCita: " + rs.getString("cita_id"));
	            System.out.println("idMedico: " + rs.getInt("medico_id"));
	            System.out.println("Fecha y hora: " + rs.getString("fecha_hora"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private static void listarInformacionTodosExamenesMedicosPaciente() {
		int Id = pedirInt("Ingrese el ID del paciente a consultar: ");
		String sql="SELECT e FROM objetos.ExamenesMedicos e WHERE paciente_id=?";
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Id);
			
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				 System.out.println("\nID: " + rs.getInt("examen_id"));
	             System.out.println("Nombre del Examen: " + rs.getString("examen_info.nombre_examen"));
	             System.out.println("Fecha de Realización: " + rs.getString("examen_info.fecha_realizacion"));
	             System.out.println("Resultado: " + rs.getString("examen_info.resultado"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private static void listarInformacionPacientePorGrupoSanguineo() {
		String grupo=pedirString("Introduce el grupo sanguineo");
		String sql="SELECT p FROM objetos.Pacientes p WHERE paciente_info.grupo_sanguineo=?";
		
		try {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, grupo);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("paciente_id"));
                System.out.println("Nombre: " + resultSet.getString("datos_personales.nombre"));
                System.out.println("Edad: " + resultSet.getInt("datos_personales.edad"));
                System.out.println("Número de Historia: " + resultSet.getString("paciente_info.numero_historia"));
                System.out.println("Grupo Sanguíneo: " + resultSet.getString("paciente_info.grupo_sanguineo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	private static void listarInformacionMedicoQueAtiendioPaciente() {
		int Id = pedirInt("Ingrese el ID del paciente a consultar: ");
		String sql="SELECT * FROM objetos.Medicos m JOIN objetos.CitasMedicas c ON m.medico_id=c.medico_id where c.paciente_id=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Id);
			
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {
					Persona per=new Persona(rs.getString("datos_personales"));
					Medico m=new Medico(rs.getString("medico_info"));
					System.out.println("nombre: "+per.getNombre());
					
	                System.out.println("\nID: " + rs.getInt("medico_id"));
	                System.out.println("Nombre: " + per.getNombre());
	                System.out.println("Edad: " + per.getEdad());
	                System.out.println("Código Médico: " + m.getCodigo_medico());
	                System.out.println("Especialidad: " + m.getEspecialidad());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

    public static int pedirInt(String mensaje) {
        while(true) {
            System.out.print(mensaje);
            try {
                return sc.nextInt();
            }catch (Exception e) {
            }
        }
    }

    public static String pedirString(String mensaje) {
        while(true) {
            System.out.print(mensaje);
            try {
                return sc.next();
            }catch (Exception e) {
            }
        }
    }

}
