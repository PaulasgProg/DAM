package ejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Codigo {
	
	private static Connection conn;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String url="jdbc:postgresql://localhost/universidad";
		String user="postgres";
		String pswd="abc1234.";
		
		try {
			conn=DriverManager.getConnection(url,user,pswd);
			Scanner scanner = new Scanner(System.in);
            boolean salir = false;

            while (!salir) {
                System.out.println("Menú de opciones:");
                System.out.println("1. Insertar Estudiante");
                System.out.println("2. Eliminar Estudiante");
                System.out.println("3. Modificar Estudiante");
                System.out.println("4. Insertar Profesor");
                System.out.println("5. Eliminar Profesor");
                System.out.println("6. Modificar Profesor");
                System.out.println("7. Insertar Curso");
                System.out.println("8. Eliminar Curso");
                System.out.println("9. Modificar Curso");
                System.out.println("10. Inscribir Estudiante en Curso");
                System.out.println("11. Desinscribir Estudiante de Curso");
                System.out.println("12. Inscribir Profesor en Curso");
                System.out.println("13. Desinscribir Profesor de Curso");
                System.out.println("14. Listar Estudiante por ID");
                System.out.println("15. Listar todos los Estudiantes");
                System.out.println("16. Listar Curso por ID");
                System.out.println("17. Listar todos los Cursos");
                System.out.println("18. Listar Profesores por ID");
                System.out.println("19. Listar todos los Profesores");
                System.out.println("20. Listar todos los Estudiantes y sus matriculas");
                System.out.println("21. Listar cursos  de un Estudiante.");
                System.out.println("22. Número de estudiantes por carrera");
                System.out.println("23. Salir");

                System.out.print("Selecciona una opción: ");
                int opcion = scanner.nextInt();
                
                switch (opcion) {
                case 1:
                    // Insertar Estudiante
                	String nombre=pedirString(scanner, "Introduce el nombre:");
                	int edad=pedirInt(scanner, "Introduce la edad");
                	String matricula=pedirString(scanner, "Introduce matricula");
                	String carrera=pedirString(scanner, "Introduce carrera");
                	insertarEstudiante(nombre, edad, matricula, carrera);
                   
                    break;
                case 2:
                    // Eliminar Estudiante
                	int id=pedirInt(scanner, "Introduce id del estudiante a eliminar");
                	eliminarEstudiante(id);
                    
                    break;
                case 3:
                    // Modificar Estudiante
                	id=pedirInt(scanner, "Introduce id del estudiante a modificar");
                	
                	nombre=pedirString(scanner, "Introduce el nombre:");
                	edad=pedirInt(scanner, "Introduce la edad");
                	matricula=pedirString(scanner, "Introduce matricula");
                	carrera=pedirString(scanner, "Introduce carrera");
                	
                	modificarEstudiante(id, nombre, edad, matricula, carrera);
                    break;
                case 4:
                    // Insertar profesor
                	nombre=pedirString(scanner, "Introduce el nombre:");
                	edad=pedirInt(scanner, "Introduce la edad");
                	String cedula=pedirString(scanner, "Introduce cedula");
                	String departamento=pedirString(scanner, "Introduce departamento");
                	int idCurso=pedirInt(scanner, "Introduce curso");
                	
                	insertarProfesor(nombre, edad, cedula, departamento,idCurso);
                    
                    break;
                case 5:
                    //Eliminar Profesor
                	id=pedirInt(scanner, "Introduce id del profesor a elminar");
                	eliminarProfesor(id);
                    
                    break;
                case 6:
                    // Modificar Profesor
                	id=pedirInt(scanner, "Introduce id del profesor a modificar");
                	
                	nombre=pedirString(scanner, "Introduce el nombre:");
                	edad=pedirInt(scanner, "Introduce la edad");
                	cedula=pedirString(scanner, "Introduce cedula");
                	departamento=pedirString(scanner, "Introduce departamento");
                	idCurso=pedirInt(scanner, "Introduce curso");
                	
                	modificarProfesor(id, nombre, edad, cedula, departamento, idCurso);
                	
                   
                    break;
                case 7:
                    // Insertar Curso
                	nombre=pedirString(scanner, "Introduce el nombre:");
                	insertarCurso(nombre);
                 
                    break;
                case 8:
                    // Eliminar Curso
                	id=pedirInt(scanner, "Introduce id del curso a eliminar");
                	eliminarCurso(id);
                    break;
                case 9:
                    // Modificar Curso
                	id=pedirInt(scanner, "Introduce id del curso a modificar");
                	nombre=pedirString(scanner, "Introduce el nombre:");
                	modificarCurso(id, nombre);
                    break;
                case 10:
                    // Inscribir Estudiante en Curso
                	idCurso=pedirInt(scanner, "Introduce id del curso");
                	id=pedirInt(scanner, "Introduce id del estudinte");
                	insertarEstudianteCurso(id, idCurso);
                	
                    
                    break;
                case 11:
                    // Desinscribir Estudiante de Curso
      
                	id=pedirInt(scanner, "Introduce id de la inscripcion");
                	eliminarInscripcion(id);
                    
                    break;
                case 12:
                    // Inscribir Profesor en Curso
                	id=pedirInt(scanner, "Introduce id de la inscripcion");
                	idCurso=pedirInt(scanner, "Introduce id del curso");
                	inscribirProfesor(id, idCurso);
                    break;
                case 13:
                    // Desinscribir Profesor de Curso
                	id=pedirInt(scanner, "Introduce id del profe");
                	desinscribirProfesorDeCurso(id);
                    break;
                case 14:
                    // Listar Estudiante por ID
                	id=pedirInt(scanner, "Introduce id del estudiente");
                	listarEstudianteID(id);
                    break;
                case 15:
                    // Listar todos los Estudiantes
                    listarEstudiantes();
                    break;
                case 16:
                    //Listar Curso por ID
                	id=pedirInt(scanner, "Introduce id del curso");
                	listarCursoID(id);
                    break;
                case 17:
                    //Listar todos los Cursos
                	listarCursos();
                    break;
                case 18:
                    //Listar Profesores por ID
                	id=pedirInt(scanner, "Introduce id del profesor");
                	listarProfesorID(id);
                    break;
                case 19:
                    //Listar todos los Profesores
                   listarProfesores();
                    break;
                case 20:
                    //Listar todos los Estudiantes y sus matriculas
                	
                    break;
                case 21:
                    //Listar cursos de un Estudiante.
                	listarEstudiantesCursos();
                    break;
                case 22:
                    //Número de estudiantes por carrera
                
                    break;
                case 23:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            }
            scanner.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static int pedirInt(Scanner scanner, String mensaje) {
        while(true) {
            System.out.println(mensaje);
            try {
                return scanner.nextInt();
            }catch (Exception e) {
            }
        }
    }

    public static String pedirString(Scanner scanner, String mensaje) {
        while(true) {
            System.out.println(mensaje);
            try {
                return scanner.next();
            }catch (Exception e) {
            }
        }
    }
    
    private static boolean listarCursoID(int cursoID) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM objetos.Cursos WHERE curso_id = ?");
            statement.setInt(1, cursoID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int cursoid = resultSet.getInt("curso_id");
                String nombre = resultSet.getString("nombre");

                System.out.println("ID: " + cursoid);
                System.out.println("Nombre: " + nombre);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static void listarCursos() {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM objetos.Cursos");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int cursoid = resultSet.getInt("curso_id");
                String nombre = resultSet.getString("nombre");

                System.out.println("ID: " + cursoid);
                System.out.println("Nombre: " + nombre);
               
            }
         
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
    private static boolean listarEstudianteID(int cursoID) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM objetos.Estudiantes WHERE estudiante_id = ?");
            statement.setInt(1, cursoID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int cursoid = resultSet.getInt("estudiante_id");
                Persona persona = new Persona(resultSet.getString("datos_personales"));
                Estudiante es=new Estudiante(resultSet.getString("estudiante_info"));

                System.out.println("ID: " + cursoid);
                System.out.println(persona);
                System.out.println(es);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static void listarEstudiantes() {
        try {
            Statement statement = conn.createStatement();
            
            ResultSet resultSet=statement.executeQuery("SELECT * FROM objetos.Estudiantes");
            while (resultSet.next()) {
                int cursoid = resultSet.getInt("estudiante_id");
                Persona persona = new Persona(resultSet.getString("datos_personales"));
                Estudiante es=new Estudiante(resultSet.getString("estudiante_info"));

                System.out.println("ID: " + cursoid);
                System.out.println(persona);
                System.out.println(es);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
    private static void listarEstudiantesCursos() {
        try {
            Statement statement = conn.createStatement();
            
            ResultSet resultSet=statement.executeQuery("SELECT (e.datos_personales).nombre AS estudiante, c.nombre AS curso "
            		+ "FROM objetos.Estudiantes e "
            		+ "INNER JOIN objetos.Inscripciones i ON i.estudiante_id=e.estudiente_id "
            		+ "INNER JOIN objetos.Cursos c ON c.curso_id=i.curso_id");
            
            while (resultSet.next()) {
                String nombreEstudiante = resultSet.getString("estudiante");
                String nombreCurso = resultSet.getString("curso");
                

                System.out.println("Estudiante: " + nombreEstudiante);
                System.out.println("Curso: "+nombreCurso);
       
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
    private static boolean listarProfesorID(int profosorId) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM objetos.Profesores WHERE profesor_id = ?");
            statement.setInt(1, profosorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int profesorID = resultSet.getInt("profesor_id");
                Persona persona = new Persona(resultSet.getString("datos_personales"));
                Profesor profesor = new Profesor(resultSet.getString("profesor_info"));

                System.out.println("ID: " + profesorID);
                System.out.println(persona);
                System.out.println(profesor);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static void listarProfesores() {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM objetos.Profesores");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int profesorID = resultSet.getInt("profesor_id");
                Persona persona = new Persona(resultSet.getString("datos_personales"));
                Profesor profesor = new Profesor(resultSet.getString("profesor_info"));

                System.out.println("ID: " + profesorID);
                System.out.println(persona);
                System.out.println(profesor);
            
            }
       
        } catch (SQLException e) {
            e.printStackTrace();
     
        }
    }
    private static void numeroEstudianreCarrera() {
    	try {
    	     PreparedStatement statement = conn.prepareStatement("SELECT c.nombre AS nombre_curso, COUNT(e.estudiante_id) AS numero_estudiantes "
                      + "FROM objetos.Cursos c "
                      + "LEFT JOIN objetos.Inscripciones i ON c.curso_id = i.curso_id "
                      + "LEFT JOIN objetos.Estudiantes e ON i.estudiante_id = e.estudiante_id "
                      + "GROUP BY c.nombre "
                      + "ORDER BY c.nombre");
    	     
    	     ResultSet resultSet = statement.executeQuery();
             while (resultSet.next()) {
                 String nombreCurso = resultSet.getString("nombre_curso");
                 int numero_estudiantes = resultSet.getInt("numero_estudiantes");

                 System.out.println("Curso: " + nombreCurso + " Asisten: " + numero_estudiantes + " estudiantes");
             }
		} catch (SQLException e) {
			// TODO: handle exception
		}
    }
    public static void insertarEstudiante(String nombre,int edad, String matricula,String carrera) {
    	try {
    		String sql="INSERT INTO objetos.Estudiantes(datos_personales,estudiante_info) VALUES (ROW(?,?),ROW(?,?))";
    		
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, nombre);
			st.setInt(2, edad);
			st.setString(3, matricula);
			st.setString(4, carrera);
			
			int rows=st.executeUpdate();
			if(rows>0) {
				System.out.println("Se ha introducido correctamente");
			}else {
				System.out.println("No se ha podido introducir");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public static void insertarProfesor(String nombre,int edad, String cedula,String departamento,int cursoid) {
    	try {
    		if (!listarCursoID(cursoid)) {
				System.out.println("No existe el curso");
				return;
			}
    		String sql="INSERT INTO objetos.Profesores(datos_personales,profesor_info,curso_id) VALUES (ROW(?,?),ROW(?,?),?)";
    		
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, nombre);
			st.setInt(2, edad);
			st.setString(3, cedula);
			st.setString(4, departamento);
			st.setInt(5, cursoid);
			
			int rows=st.executeUpdate();
			if(rows>0) {
				System.out.println("Se ha introducido correctamente");
			}else {
				System.out.println("No se ha podido introducir");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public static void insertarCurso(String name) {
		String sql="INSERT INTO objetos.Cursos(nombre) VALUES(?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			
			int rows=ps.executeUpdate();
			if(rows>0) {
				System.out.println("Se ha introducido correctamente");
			}else {
				System.out.println("No se ha podido introducir");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    public static void insertarEstudianteCurso(int estudianteId, int cursoId) {
    	if (!listarCursoID(cursoId)) {
			System.out.println("No existe el curso");
			return;
		}
    	if (!listarEstudianteID(estudianteId)) {
			System.out.println("No existe el estudiante");
			return;
		}
		String sql="INSERT INTO objetos.Inscripciones(estudiante_id,curso_id) VALUES(?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, estudianteId);
			ps.setInt(2, cursoId);
			
			int rows=ps.executeUpdate();
			if(rows>0) {
				System.out.println("Se ha introducido correctamente");
			}else {
				System.out.println("No se ha podido introducir");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    public static void eliminarEstudiante(int id) {
    	try {
    		
			String sql="DELETE FROM objetos.Estudiantes WHERE estudiante_id=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, id);
			
			int rows=st.executeUpdate();
			if(rows>0) {
				System.out.println("Se ha BORRADO correctamente");
			}else {
				System.out.println("No se ha podido BORRAR");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public static void eliminarProfesor(int id) {
    	try {
    		if (!listarProfesorID(id)) {
				System.out.println("No existe el profesor");
				return;
			}
			String sql="DELETE FROM objetos.Profesores WHERE profesor_id=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, id);
			
			int rows=st.executeUpdate();
			if(rows>0) {
				System.out.println("Se ha BORRADO correctamente");
			}else {
				System.out.println("No se ha podido BORRAR");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public static void eliminarCurso(int id) {
    	try {
    		
			String sql="DELETE FROM objetos.Cursos WHERE curso_id=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, id);
			
			int rows=st.executeUpdate();
			if(rows>0) {
				System.out.println("Se ha BORRADO correctamente");
			}else {
				System.out.println("No se ha podido BORRAR");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public static void eliminarInscripcion(int id) {
    	try {
    		
			String sql="DELETE FROM objetos.Inscripciones WHERE inscripcion_id=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, id);
			
			int rows=st.executeUpdate();
			if(rows>0) {
				System.out.println("Se ha BORRADO correctamente");
			}else {
				System.out.println("No se ha podido BORRAR");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public static void modificarEstudiante(int id,String nombre,int edad, String matricula,String carrera) {
    	try {
			String sql="UPDATE objetos.Estudiantes SET datos_pesonales=ROW(?,?), estudiante_info=ROW(?,?) WHERE estudiante_id=? ";
			PreparedStatement st=conn.prepareStatement(sql);
			
			st.setString(1, nombre);
			st.setInt(2, edad);
			st.setString(3, matricula);
			st.setString(4, carrera);
			st.setInt(5, id);
			
			int rows=st.executeUpdate();
			if(rows>0) {
				System.out.println("Se ha actualizado correctamente");
			}else {
				System.out.println("No se ha podido actualizar");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public static void modificarProfesor(int id,String nombre,int edad, String cedula,String dep,int cursoid) {
    	try {
    		if (!listarProfesorID(id)) {
				System.out.println("No existe el profesor");
				return;
			}
    		if (!listarCursoID(cursoid)) {
				System.out.println("No existe el curso");
				return;
			}
			String sql="UPDATE objetos.Profesores SET datos_pesonales=ROW(?,?), profesor_info=ROW(?,?), curso_id=? WHERE profesor_id=? ";
			PreparedStatement st=conn.prepareStatement(sql);
			
			st.setString(1, nombre);
			st.setInt(2, edad);
			st.setString(3, cedula);
			st.setString(4, dep);
			st.setInt(5, cursoid);
			st.setInt(5, id);
			
			int rows=st.executeUpdate();
			if(rows>0) {
				System.out.println("Se ha actualizado correctamente");
			}else {
				System.out.println("No se ha podido actualizar");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public static void modificarCurso(int id,String nombre) {
    	try {
    		if (!listarCursoID(id)) {
				System.out.println("No existe el curso");
				return;
			}
			String sql="UPDATE objetos.Cursos SET nombre=?";
			PreparedStatement st=conn.prepareStatement(sql);
			
			st.setString(1, nombre);
			
			int rows=st.executeUpdate();
			if(rows>0) {
				System.out.println("Se ha actualizado correctamente");
			}else {
				System.out.println("No se ha podido actualizar");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void inscribirProfesor(int id,int cursoid) {
    	try {
    		if (!listarProfesorID(id)) {
				System.out.println("No existe el profesor");
				return;
			}
    		if (!listarCursoID(cursoid)) {
				System.out.println("No existe el curso");
				return;
			}
			String sql="UPDATE objetos.Profesores SET curso_id=? WHERE profesor_id=? ";
			PreparedStatement st=conn.prepareStatement(sql);
			
			st.setInt(1, cursoid);
			st.setInt(2, id);
			
			int rows=st.executeUpdate();
			if(rows>0) {
				System.out.println("Se ha actualizado correctamente");
			}else {
				System.out.println("No se ha podido actualizar");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public static void desinscribirProfesorDeCurso(int profesorId) {
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE objetos.Profesores SET curso_id = NULL WHERE profesor_id = ?");
            statement.setInt(1, profesorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
