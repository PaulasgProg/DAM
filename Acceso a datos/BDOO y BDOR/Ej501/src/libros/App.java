package libros;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
	static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		
		String dURL="jdbc:postgresql://localhost:5432/";
		String user="postgres";
		String password="abc1234.";
		String databaseName="listalibros";
		
		Connection conexion=null;
		Statement st=null;
		
		try {
			conexion=DriverManager.getConnection(dURL,user,password);
			st=conexion.createStatement();
			
			try {
				//SI Existe la bd la borramos y la creamos de nuevo
				st.executeUpdate("DROP DATABASE "+ databaseName);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("No existe la base de datos");
			}
			
			st.executeUpdate("CREATE DATABASE "+ databaseName + ";");
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
					conexion.close();
		}
		
		String URL="jdbc:postgresql://localhost:5432/listalibros";
		
		try(Connection conn= DriverManager.getConnection(URL,user,password)) {
			Statement stmt=conn.createStatement();
			
			
			//creamos tipo de objetos llamado autor
			stmt.executeUpdate("CREATE SCHEMA objetos");
			stmt.executeUpdate("CREATE TYPE objetos.tipo_autor AS (nombre_autor varchar(255),fechaNacimiento varchar(100))");
			
			//CREAMOS TABLA LIBROS
			String crearTabla="CREATE TABLE objetos.libros (id serial PRIMARY KEY, titulo VARCHAR, autor objetos.tipo_autor,ano_publicacion integer)";
			PreparedStatement createTableSt=conn.prepareStatement(crearTabla);
			createTableSt.executeUpdate();
			
			insertar(conn);
            consultar(conn);
            actualizar(conn);
            consultar(conn);
            eliminar(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	private static void eliminar(Connection conn) throws SQLException{
		System.out.println("ID del libro a eliminar: ");
		int idLibro=scanner.nextInt();
		
		scanner.nextLine();
		
		String delete="DELETE FROM objetos.libros WHERE id=?";
		PreparedStatement ps=conn.prepareStatement(delete);
		ps.setInt(1, idLibro);
		
		int rowsDeleted= ps.executeUpdate();
		
		if (rowsDeleted>0) {
			System.out.println("Libro eliminado con exito");
			
		}else {
			System.out.println("No se pudo eliminar el libro. Verifica el ID.");
		}
	}
	
	private static void actualizar(Connection conn) throws SQLException {
		System.out.println("ID del libro a actualizar: ");
		int idLibro=scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Nuevo titulo: ");
		String titulo=scanner.nextLine();
		System.out.println("Nombre del autor: ");
		String autor=scanner.nextLine();
		System.out.println("Fecha de nacimiento del autor: ");
		int fecha=scanner.nextInt();
		
		String update="UPDATE objetos.libros SET titulo=?, autor.nombre_autor=?, autor.fechaNacimiento=? WHERE id=? ";
		PreparedStatement ps=conn.prepareStatement(update);
		ps.setString(1, titulo);
		ps.setString(2, autor);
		ps.setInt(3, fecha);
		ps.setInt(4, idLibro);
		
		int rowsUpdated=ps.executeUpdate();
		
		if (rowsUpdated > 0) {
            System.out.println("Libro actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar el libro. Verifica el ID.");
        }
	}
	
	private static void consultar(Connection conn) throws SQLException {
		System.out.println("Buscar libros por titulo o autor");
		String consulta = scanner.nextLine();
        consulta = scanner.nextLine();

        String selectSQL = "SELECT * FROM objetos.libros WHERE titulo LIKE ? OR (autor).nombre_autor LIKE ?";
        PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
        preparedStatement.setString(1, "%" + consulta + "%");
        preparedStatement.setString(2, "%" + consulta + "%");
        
        ResultSet rs=preparedStatement.executeQuery();
        
        while (rs.next()) {
        	System.out.println("Título: " + rs.getString("titulo"));
            System.out.println("Autor: " + rs.getString("autor"));
            System.out.println("Año: " + rs.getInt("ano_publicacion"));
            System.out.println("---------");
		}
	}
	private static void insertar(Connection conn) throws SQLException {
		System.out.print("Título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Autor del nacimiento del Autor: ");
        int nacimientoAutor = scanner.nextInt();
        System.out.print("Año del libro: ");
        int ano = scanner.nextInt();
        
        String insert="INSERT INTO objetos.libros (titulo,autor,ano_publicacion) VALUES (?,ROW(?,?),?)";
        PreparedStatement ps=conn.prepareStatement(insert);
        ps.setString(1, titulo);
        ps.setString(2, autor);
        ps.setInt(3, nacimientoAutor);
        ps.setInt(4, ano);
        
        int rowsInserted=ps.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Libro insertado con éxito.");
        } else {
            System.out.println("No se pudo insertar el libro.");
        }
	}

}
