package ejercicio602;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.basex.examples.api.BaseXClient;

public class App {
	static Scanner sc;
    static ArrayList<Libro> biblioteca = new ArrayList<>();
    final static String nombreDatasetGrande = "ejercicio602FicheroGrande";
    final static String nombreDatasetPequeno = "ejercicio602FicherosPequenhos";

	public static void main(String[] args) throws IOException {
	
		 sc = new Scanner(System.in);
	        int opcion;
	        do{
	            opcion = pedirInt("1. Crear datasets\n2. Realizar consultas\n3. Salir");
	            switch (opcion){
	                case 1:
	                    crearDatasets();
	                    break;
	                case 2:
	                    realizarConsultas();
	                    break;
	                case 3:

                        break;
	            }
	        }while(opcion != 3);
	}
	
	   private static void realizarConsultas() {
	        try(BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {
	            int opcion;
	            do{
	                opcion = pedirInt("1. Número total de documentos\n" +
	                        "2. Número de libros publicados antes de cierto año\n" +
	                        "3. Número de libros que escribió un determinado autor\n" +
	                        "4. Número medio de palabras de todos los libros\n" +
	                        "5. Número medio de palabras de los libros publicados antes de un determinado año y por un determinado autor\n" +
	                        "6. Salir");
	                int opcion2;
	                do{
	                    opcion2 = pedirInt("1. Dataset grande\n2. Dataset pequeño");
	                }while(opcion2 > 2 || opcion2 < 1);

	                switch (opcion) {
	                    case 1:
	                        totalDocumentos(session, opcion2);
	                        break;
	                    case 2:
	                        librosPorAnho(session, opcion2);
	                        break;
	                    case 3:
	                        librosPorAutor(session, opcion2);
	                        break;
	                    case 4:
	                        numMedioPalabras(session, opcion2);
	                        break;
	                    case 5:
	                        numMedioPalabrasLibroAnhoAutor(session, opcion2);
	                        break;
	                    case 6:

	                        break;
	                }
	            }while(opcion != 6);
	        }catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	
	private static void numMedioPalabrasLibroAnhoAutor(BaseXClient session, int opcion2) {
		int autor = pedirInt("Introduzca el número del autor");
	    int primerApellido = pedirInt("Introduzca el número del primer apellido");
	    
	    int anho = pedirInt("Introduzca un año");
	    
		String sql;
		if(opcion2==1) {
			sql = "avg(for $i in db:get('"+nombreDatasetGrande+"')/biblioteca/libro where $i/autor/nombre='Nombre"+autor+"' and "
					+ "contains($i/autor/apellidos, 'Apellido" + primerApellido + "') and "
							+ "$i/@publicacion<"+anho+" return $i/paginas";
		}else {
			sql = "avg(for $i in db:get('"+nombreDatasetPequeno+"')/libro where $i/autor/nombre='Nombre"+autor+"' and "
					+ "contains($i/autor/apellidos, 'Apellido" + primerApellido + "') and "
							+ "$i/@publicacion<"+anho+" return $i/paginas";
		}
		
		try(BaseXClient.Query query=session.query(sql)) {
			
			if(query.more()) {
				System.out.println("Media paginas: "+query.next()+query.info());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private static void numMedioPalabras(BaseXClient session, int opcion2) {
		String sql;
		if(opcion2==1) {
			sql = "avg(for $i in db:get('"+nombreDatasetGrande+"')/biblioteca/libro return $i/paginas";
		}else {
			sql = "avg(for $i in db:get('"+nombreDatasetPequeno+"')/libro return $i/paginas";
		}
		
		try(BaseXClient.Query query=session.query(sql)) {
			
			if(query.more()) {
				System.out.println("Media paginas: "+query.next()+query.info());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private static void librosPorAutor(BaseXClient session, int opcion2) {
		int autor = pedirInt("Introduzca el número del autor");
	    int primerApellido = pedirInt("Introduzca el número del primer apellido");

		String sql;
		
		if(opcion2==1) {
			sql = "count(for $i in db:get('" + nombreDatasetGrande + "')/biblioteca/libro where $i/autor/nombre='Nombre" + autor +
                    "' and contains($i/autor/apellidos, 'Apellido" + primerApellido + "') return $i)";
		}else {
			sql = "count(for $i in db:get('" + nombreDatasetPequeno + "')/libro where $i/autor/nombre='Nombre" + autor +
                    "' and contains($i/autor/apellidos, 'Apellido" + primerApellido + "') return $i)";
		}
		
		try (BaseXClient.Query query= session.query(sql)){
			if (query.more()) {
                System.out.println("Total de documentos: " + query.next() + query.info());
            }			
		} catch (Exception e) {}	
	}

	private static void librosPorAnho(BaseXClient session, int opcion2) {
		int anho = pedirInt("Introduzca un año");
        String sql;
        if(opcion2==1) {
			sql="count(for $i in db:get('"+nombreDatasetGrande+"')/biblioteca/libro "
					+ "where $i/@publicacion < "+anho+" return $i)";
		}else {
			sql="count(for $i in db:get('"+nombreDatasetPequeno+"')/libro "
					+ "where $i/@publicacion < "+anho+" return $i)";
		}
        try (BaseXClient.Query query = session.query(sql)) {

            // Comprobación e iteración de los resultados
            if (query.more()) {
                System.out.println("Total de documentos: " + query.next() + query.info());
            }
            
        } catch (Exception ignored) { }
	}

	private static void totalDocumentos(BaseXClient session, int opcion2) throws IOException {
		String sql;
		if(opcion2==1) {
			sql="count(for $i in db:get('"+nombreDatasetGrande+"') return $i)";
		}else {
			sql="count(for $i in db:get('"+nombreDatasetPequeno+"') return $i)";
		}
		try(BaseXClient.Query query=session.query(sql)){
			if (query.more()) {
                System.out.println("Total de documentos: " + query.next() + query.info());
            }
		}
		
	}

	private static void crearDatasets() throws IOException {
		
		while (biblioteca.size()<1000) {
			biblioteca.add(new Libro().generarDatosAleatorios());
		}
		
		try(BaseXClient session=new BaseXClient("localhost", 1984, "admin", "abc123")){
			
			InputStream bais=new ByteArrayInputStream(biblioteca.get(0).toString().getBytes());
			session.create(nombreDatasetPequeno, bais);
			
			 for (int i = 1; i < biblioteca.size(); i++) {
	                bais = new ByteArrayInputStream(biblioteca.get(i).toString().getBytes());
	                session.add(nombreDatasetPequeno + "/libro" + i + ".xml", bais);
	         }
		}catch (Exception ignored){}
		
		try(BaseXClient session=new BaseXClient("localhost", 1984, "admin", "abc123")){
			StringBuilder sb=new StringBuilder();
			
			sb.append("<biblioteca>");
			
			for (Libro libro : biblioteca) {
				sb.append(libro.toString());
			}
			sb.append("</biblioteca>");
			InputStream bais=new ByteArrayInputStream(sb.toString().getBytes());
			session.create(nombreDatasetGrande, bais);
			
			
		}catch (Exception ignored){}
		
	}
	
	private static String pedirString(String mensaje) {
        while(true){
            try{
                System.out.println(mensaje);
                return sc.next();
            }catch (Exception ignored){}
        }
    }

    private static int pedirInt(String mensaje) {
        while(true){
            try{
                System.out.println(mensaje);
                return sc.nextInt();
            }catch (Exception ignored){}
        }
    }
}
