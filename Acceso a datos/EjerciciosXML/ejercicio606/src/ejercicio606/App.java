package ejercicio606;

import java.util.Scanner;

import org.basex.examples.api.BaseXClient;

public class App {
	
	static Scanner sc;
	public static void main(String[] args) {
		sc=new Scanner(System.in);
		
		try(BaseXClient session=new BaseXClient("localhost", 1984, "admin", "abc123")) {
			String bd=pedirString("Escribe el nombre de la base de datos");
			session.execute("open "+bd);
			
			int option=0;
			
			do {
				mostrarMenu();
				option=sc.nextInt();
				switch (option) {
				case 1://Los títulos de los libros con la etiqueta <titulo> 
					ejecutarConsulta(session, "for $i in /libreria/libro "
							+ "return <titulo>{$i/titulo/text()}</titulo>");
					
					break;

				case 2://Los libros cuyo precio sea menor o igual a 30.
					ejecutarConsulta(session, "for $i in /libreria/libro "
							+ "where number($i/precio)<=30 "
							+ "return $i");
					break;
				case 3://Solo el título de los libros cuyo precio sea menor o igual a 30
					ejecutarConsulta(session, "for $i in /libreria/libro "
							+ "where number($i/precio)<=30 "
							+ "return $i/titulo");
					break;
				case 4://Solo el título sin atributos de los libros cuyo precio sea menor o igual a 30
					ejecutarConsulta(session, "for $i in /libreria/libro "
							+ "where number($i/precio)<=30 "
							+ "return $i/titulo/text()");
					break;
				case 5://El título y el autor de los libros del año 2005, y etiquetar cada uno de ellos con la etiqueta <lib2005>
					ejecutarConsulta(session, "for $i in /libreria/libro "
							+ "where $i/año= \"2005\" "
							+ "return <lib2005>{$i/titulo}{$i/autor}</lib2005>");
					break;
				case 6://6.Los años de publicación, primero con for y luego con let 
					//para comprobar la diferencia entre ellos. Etiquetar la salida con <publicacion>.
					ejecutarConsulta(session, "for $i in /libreria/libro "
							+ "return <publicacion>{$i/año/text()}</publicacion>");
					
					ejecutarConsulta(session, "let $i := /libreria/libro "
							+ "return <publicacion>{$i/año/text()}</publicacion>");
					
					break;
				case 7://7.Los libros ordenados primero por el atributo categoria y luego por titulo en una sola consulta
					ejecutarConsulta(session, "for $i in /libreria/libro"
							+ "order by $i/@categoria,$i/titulo "
							+ "return $i");
					
					break;
					
				case 8://La cantidad de libros. Se debe etiquetar con <total>
					ejecutarConsulta(session, "<total>{count(for $i in /libreria/libro "
							+ "return $i)}</total>");
					break;
				case 9://Los títulos de los libros y, al final, una etiqueta con el número total de libros
					ejecutarConsulta(session, "let $total := count(/libreria/libro),"
							+ "$titulos := (for $i in /libreria/libro "
							+ "return <titulo>{$i/titulo/text()}</titulo>) "
							+ "return <resultado>{$titulos}<totallibros>{$total}</totallibros></resultado>");		
					break;
				case 10://.El precio mínimo y máximo de los libros
					ejecutarConsulta(session, "let $max := max(/libreria/libro/precio),"
							+ "$min := min(/libreria/libro/precio) "
							+ "return "
							+ "<resultado>"
							+ "<maximo>{$max}</maximo>"
							+ "<minimo>{$min}</minimo>"
							+ "</resultado>");
					
					break;
				case 11://El título del libro, su precio y su precio con el IVA incluido
					//cada uno con su propia etiqueta. Ordenarlos por precio con IVA.
					ejecutarConsulta(session, "for $i in /libreria/libro "
							+ "let $precioIva := ($i/precio * 1.21) "
							+ "order by $precioIva "
							+ "return <libro>"
							+ "<titulo>{$i/titulo/text()}</titulo>"
							+ "<precio>{$i/precio/text()}€</precio>"
							+ "<precio-iva>{$precioIva}€</precio-iva>"
							+ "</libro>");
					break;
				case 12://La suma total de los precios de los libros. Se debe etiquetar con <total>
					ejecutarConsulta(session, "<total>"
							+ "{sum(for $i in /libreria/libro/precio "
							+ "return $i)}"
							+ "</total>");
					break;
				case 13://Cada uno de los precios de los libros y, al final,
					//una nueva etiqueta con la suma de los precios.
					ejecutarConsulta(session, "let $libros := /libreria/libro "
							+ "return <precios>"
							+ "{$libros/precio}"
							+ "<total>{sum($libros/precio)}</total>"
							+ "</precios>");
					break;
					
				case 14://El título y el número de autores que tiene cada título en etiquetas diferentes.
					
					ejecutarConsulta(session, "for $i in /libreria/libro "
							+ "return <libro>"
							+ "<titulo>{$i/titulo/text()}</titulo>"
							+ "<numAutores>{count($i/autor)}</numAutores>"
							+ "</libro>");
					break;
				case 15://En la misma etiqueta, el título y, entre paréntesis, el número de autores que tiene ese título
					ejecutarConsulta(session, "for $i in /libreria/libro "
							+ "return <libro>"
							+ "{$i/titulo/text()}"
							+ "({count($i/autor)})"
							+ "</libro>");
					break;
				case 16://Los libros escritos en años que terminen en \"3\"
					ejecutarConsulta(session, "for $libro in /libreria/libro "
							+ "where ends-with($libro/año,\"3\") "
							+ "return $libro");
								
					break;
				case 17://Los libros cuya categoría empiece por \"C\"
					ejecutarConsulta(session, "for $libro in /libreria/libro "
							+ "where starts-with($libro/@categoria,\"C\") "
							+ "return $libro");
					
					break;
				case 18://Los libros que tengan una \"X\" mayúscula o minúscula en el título ordenados de manera descendente
					ejecutarConsulta(session, "for $libro in /libreria/libro "
							+ "where contains($libro/titulo,\"X\") or contains($libro/titulo,\"x\") "
							+ "order by $libro/titulo descending "
							+ "return $libro");
					break;
				case 19://El título y el número de caracteres que tiene cada título, cada uno con su propia etiqueta
					ejecutarConsulta(session, "for $libro in /libreria/libro "
							+ "return <libro>"
							+ "{$libro/titulo}"
							+ "<numeroCaracteres>{string-length($libro/titulo)}</numeroCaracteres>"
							+ "</libro>");
					break;
				case 20://.Todos los años en los que se ha publicado un libro eliminando los repetidos. Se deben etiquetar con <año>.
					ejecutarConsulta(session, "for $año in distinct-values(/libreria/libro/año)\r\n"
							+ "return <año>{$año}</año>"	);
					break;
				case 21://Todos los autores eliminando los que se repiten y 
					//ordenados por el número de caracteres que tiene cada autor.
					
					ejecutarConsulta(session, "for $autor in /libreria/libro/autor "
							+ "let $caracteres := string-length($autor) "
							+ "order by $caracteres "
							+ "return <autor>{$autor/text()}({$caracteres})</autor>");
					break;
				case 22://Los títulos en una tabla de HTML.
					ejecutarConsulta(session, "<table>{"
							+ "for $titulo in /libreria/libro/titulo "
							+ "return "
							+ "<tr>"
							+ "<td>{$titulo/text()}</td>"
							+ "</tr>"
							+ "}"
							+ "</table>");
					break;
				case 23:
					break;
				default:
					break;
				}
				
			} while (option!=23);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private static void ejecutarConsulta(BaseXClient session, String consulta) {
		try (BaseXClient.Query query=session.query(consulta)){
			while (query.more()) {
				System.out.println(query.next());
			}			
		} catch (Exception e) {
			System.out.println("Consulta incorrecta:\n" + e.getStackTrace());
		}		
	}

	private static void mostrarMenu() {
		
		System.out.println("1.Los títulos de los libros con la etiqueta <titulo>.\r\n"
				+ "2.Los libros cuyo precio sea menor o igual a 30.\r\n"
				+ "3.Solo el título de los libros cuyo precio sea menor o igual a 30.\r\n"
				+ "4.Solo el título sin atributos de los libros cuyo precio sea menor o igual a 30.\r\n"
				+ "5.El título y el autor de los libros del año 2005, y etiquetar cada uno de ellos con la etiqueta <lib2005>.\r\n"
				+ "6.Los años de publicación, primero con for y luego con let para comprobar la diferencia entre ellos. Etiquetar la salida con <publicacion>.\r\n"
				+ "7.Los libros ordenados primero por el atributo categoria y luego por titulo en una sola consulta.\r\n"
				+ "8.La cantidad de libros. Se debe etiquetar con <total>.\r\n"
				+ "9.Los títulos de los libros y, al final, una etiqueta con el número total de libros.\r\n"
				+ "10.El precio mínimo y máximo de los libros.\r\n"
				+ "11.El título del libro, su precio y su precio con el IVA incluido, cada uno con su propia etiqueta. Ordenarlos por precio con IVA.\r\n"
				+ "12.La suma total de los precios de los libros. Se debe etiquetar con <total>.\r\n"
				+ "13.Cada uno de los precios de los libros y, al final, una nueva etiqueta con la suma de los precios.\r\n"
				+ "14.El título y el número de autores que tiene cada título en etiquetas diferentes.\r\n"
				+ "15.En la misma etiqueta, el título y, entre paréntesis, el número de autores que tiene ese título.\r\n"
				+ "16.Los libros escritos en años que terminen en \"3\".\r\n"
				+ "17.Los libros cuya categoría empiece por \"C\".\r\n"
				+ "18.Los libros que tengan una \"X\" mayúscula o minúscula en el título ordenados de manera descendente.\r\n"
				+ "19.El título y el número de caracteres que tiene cada título, cada uno con su propia etiqueta.\r\n"
				+ "20.Todos los años en los que se ha publicado un libro eliminando los repetidos. Se deben etiquetar con <año>.\r\n"
				+ "21.Todos los autores eliminando los que se repiten y ordenados por el número de caracteres que tiene cada autor.\r\n"
				+ "22.Los títulos en una tabla de HTML.");
	}
	
	private static String pedirString(String consulta) {
		while(true){
            try{
                System.out.println(consulta);
                return sc.next();
            }catch (Exception ignored){}
	}
	}

}
