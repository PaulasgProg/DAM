package ejercicio605;

import java.util.Scanner;

import org.basex.examples.api.BaseXClient;

public class App {
	
	static Scanner sc;
	
	public static void main(String[] args) {
		sc=new Scanner(System.in);
		
		try(BaseXClient session=new BaseXClient("localhost", 1984, "admin", "abc123")) {
			String bd=pedirString();
			session.execute("open "+bd);
			
			int option=0;
			
			do {
				mostrarMenu();
				option=sc.nextInt();
				switch (option) {
				case 1://Cada uno de los nombres de los bailes con la etiqueta <losbailes>
					ejecutarConsulta(session, "for $i in /bailes/baile "
							+ "return <losbailes>{$i/nombre/text()}</losbailes>");
					
					break;

				case 2://Los nombres de los bailes seguidos con el número de plazas entre paréntesis,
					//ambos dentro de la misma etiqueta <losbailes>
					ejecutarConsulta(session, "for $i in /bailes/baile "
							+ "return <losbailes>{$i/nombre/text()}({data($i/plazas)})</losbailes>");
					break;
				case 3://Los nombres de los bailes cuyo precio sea mayor de 30.
					ejecutarConsulta(session, "for $i in /bailes/baile "
							+ "where number($i/precio)>30 "
							+ "return data($i/nombre)");
					break;
				case 4://Los nombres de los bailes cuyo precio sea mayor de 30 y la moneda euro
					ejecutarConsulta(session, "for $i in /bailes/baile "
							+ "where number($i/precio)>30 and $i/precio/@moneda = \"euro\" "
							+ "return data($i/nombre)");
					break;
				case 5://Los nombres y la fecha de comienzo de los bailes que comiencen el mes de enero 
					//(utiliza para buscarlo la cadena de texto /1/)
					ejecutarConsulta(session, "for $i in /bailes/baile "
							+ "where contains($i/comienzo, \"/1/\") "
							+ "return <baile>{$i/nombre},{$i/comienzo}</baile>");
					break;
				case 6://6.Los nombres de los profesores y la sala en la que dan clase, ordenados por sala.
					ejecutarConsulta(session, "for $i in /bailes/baile "
							+ "order by $i/sala "
							+ "return concat($i/profesor/text(),' - ',$i/sala/text())");
					
					break;
				case 7://7.Los nombres de los profesores, eliminando los repetidos y acompañando cada nombre
					//con todas las salas en la que da clase. Ordenar por nombre.
					ejecutarConsulta(session, "for $i in distinct-values(/bailes/baile/profesor) "
							+ "let $salas := /bailes/baile[profesor=$i]/sala "
							+ "order by $i "
							+ "return <profesores>"
							+ "<nombre>{$i}</nombre>"
							+ "{$salas}"
							+ "</profesores>");
					
					break;
					
				case 8://La media de los precios de todos los bailes
					ejecutarConsulta(session, "avg(for $i in /bailes/baile/precio "
							+ "return $i)");
					break;
				case 9://La suma de los precios de los bailes de la sala 1
					ejecutarConsulta(session, "sum("
							+ "for $baile in /bailes/baile "
							+ "where $baile/sala = \"1\" "
							+ "return $baile/precio)");		
					break;
				case 10://La cantidad de plazas ofertadas por el profesor Jesus Lozano.
					ejecutarConsulta(session, "<plazas>{sum("
							+ "for $i in /bailes/baile "
							+ "where $i/profesor= \"Jesus Lozano\" "
							+ "return $i/plazas)}</plazas>");
					
					break;
				case 11://El dinero que ganaría la profesora Laura Mendiola si se completaran todas las plazas
					//de su baile, sabiendo que solo tiene un baile.
					ejecutarConsulta(session, "for $i in /bailes/baile "
							+ "where $i/profesor= \"Laura Mendiola\" "
							+ "return $i/plazas * $i/precio");
					break;
				case 12://El dinero que ganaría el profesor Jesus Lozano si se completaran todas las plazas
					//de su baile, pero mostrando el beneficio de cada baile por separado.
					ejecutarConsulta(session, "for $i in /bailes/baile "
							+ "where $i/profesor= \"Jesus Lozano\" "
							+ "return <baile>{$i/nombre},{$i/plazas * $i/precio}</baile>");
					break;
				case 13://Mostrar el dinero que ganaría la profesora Laura (no conocemos su apellido) 
					//si se completaran todas las plazas de su baile.
					ejecutarConsulta(session, "for $i in /bailes/baile "
							+ "where starts-with($i/profesor,\"Laura \") "
							+ "return $i/plazas * $i/precio");
					break;
					
				case 14://El nombre del baile, su precio y el precio con un descuento del 15% para familias numerosas. 
					//Ordenar por el nombre del baile.
					
					ejecutarConsulta(session, "for $i in /bailes/baile "
							+ "order by $i/nombre "
							+ "return <baile>{$i/nombre},<precio>{$i/precio/text()}</precio>,<precio-descuento>{$i/precio * 0.85}</precio-descuento></baile>");
					break;
				case 15://Todos los datos de cada baile excepto la fecha de comienzo y de fin.
					ejecutarConsulta(session, "for $i in /bailes/baile "
							+ "return <baile>"
							+ "{ "
							+ "			$i/* "
							+ "			except $i/comienzo "
							+ "			except $i/fin "
							+ "}"
							+ "</baile>");
					
					break;
				case 16://En una tabla de HTML, los nombres de los bailes y su profesor, cada uno en una fila."
					ejecutarConsulta(session, "<table>"
							+ "{"
							+ "for $i in /bailes/baile "
							+ "return <tr>"
							+ "<td>{$i/nombre/text()}</td>"
							+ "<td>{$i/profesor/text()}</td>"
							+ "</tr> "
							+ "}"
							+ "</table>");
					
					break;
				case 17:
					break;
				default:
					break;
				}
				
			} while (option!=17);
			
			
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
		 
		 try(BaseXClient.Query query= sesion.query(consulta)) {
			 while (query.more()) {
				System.out.println(query.next());		
			}
			
		} catch (Exception e) {
			 System.out.println("Consulta incorrecta:\n" + e.getStackTrace());
		}
	 }
	 private static void mostrarMenu() {
		 System.out.println("1.Cada uno de los nombres de los bailes con la etiqueta <losbailes>.\r\n"
		 		+ "2.Los nombres de los bailes seguidos con el número de plazas entre paréntesis, ambos dentro de la misma etiqueta <losbailes>.\r\n"
		 		+ "3.Los nombres de los bailes cuyo precio sea mayor de 30.\r\n"
		 		+ "4.Los nombres de los bailes cuyo precio sea mayor de 30 y la moneda euro.\r\n"
		 		+ "5.Los nombres y la fecha de comienzo de los bailes que comiencen el mes de enero (utiliza para buscarlo la cadena de texto /1/).\r\n"
		 		+ "6.Los nombres de los profesores y la sala en la que dan clase, ordenados por sala.\r\n"
		 		+ "7.Los nombres de los profesores, eliminando los repetidos y acompañando cada nombre con todas las salas en la que da clase. Ordenar por nombre.\r\n"
		 		+ "8.La media de los precios de todos los bailes.\r\n"
		 		+ "9.La suma de los precios de los bailes de la sala 1.\r\n"
		 		+ "10.La cantidad de plazas ofertadas por el profesor Jesus Lozano.\r\n"
		 		+ "11.El dinero que ganaría la profesora Laura Mendiola si se completaran todas las plazas de su baile, sabiendo que solo tiene un baile.\r\n"
		 		+ "12.El dinero que ganaría el profesor Jesus Lozano si se completaran todas las plazas de su baile, pero mostrando el beneficio de cada baile por separado.\r\n"
		 		+ "13.Mostrar el dinero que ganaría la profesora Laura (no conocemos su apellido) si se completaran todas las plazas de su baile.\r\n"
		 		+ "14.El nombre del baile, su precio y el precio con un descuento del 15% para familias numerosas. Ordenar por el nombre del baile.\r\n"
		 		+ "15.Todos los datos de cada baile excepto la fecha de comienzo y de fin.\r\n"
		 		+ "16.En una tabla de HTML, los nombres de los bailes y su profesor, cada uno en una fila.");
	 }

}
