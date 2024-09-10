package ejercicio607;

import java.util.Scanner;

import org.basex.examples.api.BaseXClient;

public class App {
	static Scanner sc;
	
	public static void main(String[] args) {
		sc=new Scanner(System.in);
		int option=0;
		
		try (BaseXClient session=new BaseXClient("localhost", 1984, "admin", "abc123")){
			String nombre=pedirString("Introduce el nombre de la bd");
			session.execute("open "+nombre);
			do {
				mostrarMenu();
				option=sc.nextInt();
				
				switch (option) {
				case 1://Una lista que contiene, para cada receta, el elemento <titulo>
					//de la receta y un elemento <calorias> que contenga el número de calorías.
					ejecutarConsulta(session, "for $i in /recetas/receta "
							+ "return <receta>{$i/titulo}"
							+ "<calorias>{number($i/nutricion/@caloria)}"
							+ "</calorias>"
							+ "</receta>");
					
					break;
				case 2://Una lista similar a la primera, ordenada según las calorías
					ejecutarConsulta(session, "<recetas>"
							+ "  {"
							+ "    for $r in /recetas/receta "
							+ "    order by number($r/nutricion/@caloria) "
							+ "    return "
							+ "      <receta>"
							+ "      {"
							+ "        $r/titulo,"
							+ "        <calorias>{number($r/nutricion/@caloria)}</calorias>"
							+ "      }"
							+ "      </receta>"
							+ "  }"
							+ "</recetas>");
					break;
				case 3://Una lista similar a la primera, ordenada alfabéticamente según el título
					ejecutarConsulta(session, "<recetas>"
							+ "  {"
							+ "    for $r in /recetas/receta "
							+ "    order by $r/titulo "
							+ "    return "
							+ "      <receta>"
							+ "      {"
							+ "        $r/titulo,"
							+ "        <calorias>{number($r/nutricion/@caloria)}</calorias>"
							+ "      }"
							+ "      </receta>"
							+ "  }"
							+ "</recetas>");
					break;
				case 4://Una lista similar a la primera, ordenada según el contenido de grasa
					ejecutarConsulta(session, "<recetas>"
							+ "  {"
							+ "    for $r in /recetas/receta "
							+ "    order by number($r/nutricion/@grasa) "
							+ "    return "
							+ "      <receta>"
							+ "      {"
							+ "        $r/titulo,"
							+ "        <calorias>{number($r/nutricion/@caloria)}</calorias>"
							+ "      }"
							+ "      </receta>"
							+ "  }"
							+ "</recetas>");
					break;
				case 5://Una lista similar a la primera, con el título como atributo y las calorías como contenido
					ejecutarConsulta(session, "<recetas>"
							+ "  {"
							+ "    for $r in /recetas/receta "
							+ "    return "
							+ "      <receta titulo=\"{$r/titulo}\">"
							+ "			<calorias>{number($r/nutricion/@caloria)}</calorias>"
							+ "      </receta>"
							+ "  }"
							+ "</recetas>");
					break;
				case 6://Una lista que contenga para cada receta, el título como atributo y cada uno de los
					//ingredientes de nivel superior (sin añadir los ingredientes que están dentro de otros ingredientes)
					ejecutarConsulta(session, "<recetas>"
							+ "{"
							+ "		for $r in /recetas/receta "
							+ "		return "
							+ "			<receta titulo=\"{$r/titulo}\">"
							+ "			{"
							+ "				for $i in $r/ingrediente "
							+ "				return "
							+ "				<ingredientes>{string($i/@nombre)}</ingredientes>"
							+ "			}"
							+ "			</receta>"
							+ "}"
							+ "</recetas>");
					break;
				case 7://7.Una lista con cada una de las recetas que contengan el ingrediente harina
						//Poner el título de la receta como atributo del elemento receta.
					ejecutarConsulta(session, "<recetas>"
							+ "{"
							+ "		for $r in /recetas/receta "
							+ "		where $r/ingrediente/@nombre= \"harina\" "
							+ "		return "
							+ "			<receta titulo=\"{$r/titulo}\">"
							+ "			</receta>"
							+ "}"
							+ "</recetas>");
					break;
				case 8://Una lista de todas aquellas recetas que tengan un ingrediente llamado relleno y este 
						//contenga en su interior más de 5 elementos ingrediente. La lista resultante estará
						//formada por elementos receta que contienen un atributo titulo con el valor del elemento
						//titulo de la receta. Además, dentro de cada elemento receta habrá elementos ingrediente
						//con el nombre de cada uno de los ingredientes.
					ejecutarConsulta(session, "<recetas>"
							+ "{"
							+ "		for $r in /recetas/receta "
							+ "		for $i in $r/ingrediente "
							+ "		where $i/@nombre= \"relleno\" and count($i/ingrediente)>5 "
							+ "		return "
							+ "			<receta titulo=\"{$r/titulo}\">"
							+ "				{for $f in $i/ingrediente "
							+ "				return <ingrediente>{data($f/@nombre)}</ingrediente>}"
							+ "			</receta>"
							+ "}"
							+ "</recetas>");
					break;
				case 9:
					break;

				default:
					break;
				}
			} while (option!=9);

		} catch (Exception e) {
			System.out.println("holaaa");
		}
		
	}
	public static void mostrarMenu() {
		System.out.println("1.Una lista que contiene, para cada receta, el elemento <titulo> \r\n"
				+ "		de la receta y un elemento <calorias> que contenga el número de calorías.\r\n"
				+ "2.Una lista similar a la primera, ordenada según las calorías.\r\n"
				+ "3.Una lista similar a la primera, ordenada alfabéticamente según el título.\r\n"
				+ "4.Una lista similar a la primera, ordenada según el contenido de grasa.\r\n"
				+ "5.Una lista similar a la primera, con el título como atributo y las calorías como contenido.\r\n"
				+ "6.Una lista que contenga para cada receta, el título como atributo y cada uno de los \r\n"
				+ " 	ingredientes de nivel superior (sin añadir los ingredientes que están dentro de otros \r\n"
				+ " 	ingredientes).\r\n"
				+ "7.Una lista con cada una de las recetas que contengan el ingrediente harina. \r\n"
				+ "		Poner el título de la receta como atributo del elemento receta.\r\n"
				+ "8.Una lista de todas aquellas recetas que tengan un ingrediente llamado relleno y este \r\n"
				+ "		contenga en su interior más de 5 elementos ingrediente. La lista resultante estará \r\n"
				+ "		formada por elementos receta que contienen un atributo titulo con el valor del elemento \r\n"
				+ "		titulo de la receta. Además, dentro de cada elemento receta habrá elementos ingrediente \r\n"
				+ "		con el nombre de cada uno de los ingredientes.");
	}
	public static String pedirString(String texto) {
		while (true) {
			try {
				System.out.println(texto);
				return sc.next();
			} catch (Exception e) {}
			
		}
	}
	public static void ejecutarConsulta(BaseXClient sesion, String consulta) {
		try (BaseXClient.Query query=sesion.query(consulta)){
			while(query.more()) {
				System.out.println(query.next());
			}
		} catch (Exception e) {
			System.out.println("Consulta incorrecta:\n" + e.getStackTrace());
		}
	}

}
