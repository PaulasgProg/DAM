package ejercicio604;

import java.util.ArrayList;
import java.util.Scanner;

import org.basex.examples.api.BaseXClient;

public class App {
	static Scanner sc;
	
	public static void main(String[] args) {
		sc=new Scanner(System.in);
		String basedatos;
		
		try(BaseXClient session= new BaseXClient("localhost",1984,"admin","abc123")) {
			basedatos=pedirString();
			session.execute("open "+basedatos);
			int option=0;
			do {
				menu();
				option=sc.nextInt();
				switch (option) {
				case 1://Título y editorial de todos los libros
					ejecutarConsulta(session, "for $i in /biblioteca/libros/libro "
							+ "return <libro>{concat($i/titulo/text(),' - ',$i/editorial/text())}</libro>");
					break;
				case 2://El título de todos los libros de menos de 400 páginas.
					ejecutarConsulta(session, "for $i in /biblioteca/libros/libro "
							+ "where number($i/paginas) < 400 "
							+ "return $i/titulo/text()"); //o data($i/titulo) para obtener solo el contenido
					break;
				case 3: //La cantidad de libros de más de 400 páginas
					ejecutarConsulta(session, "count( "
							+ "for $libro in /biblioteca/libros/libro "
							+ "where number($libro/paginas)>400 "
							+ "return $libro)");
					break;
				case 4: //Una lista HTML con el título de los libros de la editorial 
					//O'Reilly Media ordenados por título
					ejecutarConsulta(session, "<ul>\r\n"
							+ "  {"
							+ "    for $libro in /biblioteca/libros/libro"
							+ "    where $libro/editorial = \"O'Reilly Media\""
							+ "    order by $libro/titulo"
							+ "    return <li>{data($libro/titulo)}</li>"
							+ "  }"
							+ "</ul>");
					break;
				case 5://Título y editorial de los libros de 2018 y 2019
					ejecutarConsulta(session, "for $libro in /biblioteca/libros/libro "
							+ "where $libro[@publicacion=2018 or @publicacion=2019] "
							+ "return <libro>{$libro/titulo}{$libro/editorial}</libro>");
					break;
				case 6://Título y editorial de los libros con más de un autor
					ejecutarConsulta(session, "for $libro in /biblioteca/libros/libro "
							+ "where count($libro/autor)>1 "
							+ "return <libro>{$libro/titulo}{$libro/editorial}</libro>");
					break;
				case 7://Título y año de publicación de los libros que tienen versión electrónica
					ejecutarConsulta(session, "for $libro in /biblioteca/libros/libro "
							+ "where $libro/edicionElectronica "
							+ "return <libro>{$libro/titulo}"
							+ "<fecha>{data($libro/@publicacion)}</fecha>"
							+ "</libro>");
					break;
				case 8://Título de los libros que no tienen versión electrónica
					ejecutarConsulta(session, "for $libro in /biblioteca/libros/libro "
							+ "where not($libro/edicionElectronica) "
							+ "return <libro>{$libro/titulo}</libro>");
					break;
				case 9:
					break;

				default:
					break;
				}
				
			} while (option!=9);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	 private static String pedirString() {
	        while(true){
	            try{
	                System.out.println("Introduzca el nombre de la BD");
	                return sc.next();
	            }catch (Exception ignored){}
	        }
	    }
	 private static void ejecutarConsulta(BaseXClient sesion, String consulta) {
	        try (BaseXClient.Query query = sesion.query(consulta)) {

	            // Comprobación e iteración de los resultados
	            while (query.more()) {
	                System.out.println(query.next());
	            }
	        } catch (Exception e) {
	            System.out.println("Consulta incorrecta:\n" + e.getStackTrace());
	        }
	    }
	 private static void menu() {
		 System.out.println("1.Título y editorial de todos los libros.\r\n"
		 		+ "2.El título de todos los libros de menos de 400 páginas.\r\n"
		 		+ "3.La cantidad de libros de más de 400 páginas.\r\n"
		 		+ "4.Una lista HTML con el título de los libros de la editorial O'Reilly Media ordenados por título.\r\n"
		 		+ "5.Título y editorial de los libros de 2018 y 2019.\r\n"
		 		+ "6.Título y editorial de los libros con más de un autor.\r\n"
		 		+ "7.Título y año de publicación de los libros que tienen versión electrónica.\r\n"
		 		+ "8.Título de los libros que no tienen versión electrónica.\r\n");
	 }

}
