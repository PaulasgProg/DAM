package ejercicio610;

import java.util.ArrayList;
import java.util.Scanner;

import org.bson.Document;
import com.mongodb.client.*;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class App {
	static Scanner sc;
    static MongoClient mongoClient;
    private static MongoDatabase database = null;
    private static MongoCollection<Document> coleccion;
    
	public static void main(String[] args) {
		sc=new Scanner(System.in);
		
		mongoClient=MongoClients.create("mongodb+srv://paulasotelo99:abc123.@cluster0.rh5crpr.mongodb.net/");
		
		 int opcion;
	        do{
	            opcion = pedirInt("1. Crear base de datos\n" +
	                    "2. Crear colección\n" +
	                    "3. Añadir nuevos Datos\n" +
	                    "4. Mostar información de todos los equipos\n" +
	                    "5. Mostrar los equipos equipos de una ciudad específica\n" +
	                    "6. Mostrar los datos de los equipos de un jugador por ID\n" +
	                    "7. Modificar la ciudad de un equipo\n" +
	                    "8. Modificar el teléfono de un jugador determinado.\n" +
	                    "9. Salir");
	            switch (opcion){
	                case 1:
	                    crearBaseDatos();
	                    break;
	                case 2:
	                    crearCollecion();
	                    break;
	                case 3:
	                    anhadirDatos();
	                    break;
	                case 4:
	                    informacionEquipos();
	                    break;
	                case 5:
	                    informacionEquipoCiudad();
	                    break;
	                case 6:
	                    informacionEquipoCiudadId();
	                    break;
	                case 7:
	                    modificarCiudadEquipo();
	                    break;
	                case 8:
	                    modificarTelefonoJugardo();
	                    break;
	            }
	        }while(opcion != 9);
		
			
	}
	
	private static void modificarTelefonoJugardo() {
	       if (coleccion == null)
	            crearCollecion();

	        String idJugador = pedirString("Introduzca el ID del jugador");
	        int numeroTelefono = pedirInt("Introduzca el numero de telefono");
	        /**
	         * UpdateResult rs = coleccion.updateMany(
	         *                 combine(eq("jugadores.personalId", idJugador),
	         *                         eq(...)
	         *                 ),
	         *                 set("jugadores.$.telefono", numeroTelefono));
	         */

	        UpdateResult rs = coleccion.updateOne(eq("jugadores.dniJugador", idJugador),
	                set("jugadores.$.telefono", numeroTelefono));

	        if(rs.getMatchedCount() > 0){
	            System.out.println("Actualizacion correcta. " + rs.getMatchedCount() + " documentos actualizados.");
	        }else{
	            System.out.println("No se ha actualizado ningún documento");
	        }
		
	}

	private static void modificarCiudadEquipo() {
		if (coleccion==null) {
			crearCollecion();
		}
		String equipo=pedirString("Introduce el nombre del equipo:");
		String ciudad=pedirString("Introduce la ciudad del equipo:");
		
		Document docFiltro=new Document("nombreClub",equipo);
		Document docModificar=new Document("$set",new Document("ciudad",ciudad));
		
		UpdateResult rs=coleccion.updateMany(docFiltro, docModificar);
		
		if(rs.getMatchedCount() > 0){
            System.out.println("Actualizacion correcta. " + rs.getMatchedCount() + " documentos actualizados.");
        }else{
            System.out.println("No se ha actualizado ningún documento");
        }
		
	}

	private static void informacionEquipoCiudadId() {
		if (coleccion==null) {
			crearCollecion();
		}
		String idJugador=pedirString("Introduce el id del jugador");
		Document docBusqueda=new Document("jugadores.dniJugador",idJugador);
		FindIterable<Document> iterDoc=coleccion.find(docBusqueda);
		
		for (Document document : iterDoc) {
            System.out.println(document);
        }
		
	}

	private static void informacionEquipoCiudad() {
		if (coleccion==null) {
			crearCollecion();
		}
		String ciudad=pedirString("Introduce la ciudad");
		Document docBusqueda=new Document("ciudad",ciudad);
		FindIterable<Document> iterDoc=coleccion.find(docBusqueda);
		
		for (Document document : iterDoc) {
			System.out.println(document);
		}
		
	}

	private static void informacionEquipos() {
		 if (coleccion == null)
	            crearCollecion();
	        FindIterable<Document> iterDoc = coleccion.find();

	        for (Document document : iterDoc) {
	            System.out.println(document);
	        }
		
	}

	private static void anhadirDatos() {
		if(database==null) {
			crearBaseDatos();
		}
		if (coleccion==null) {
			crearCollecion();
		}
		
		String[] campos = new String[]{"dniEntrenador", "nombreEntrenador", "fecha_nacimiento", "calle", "codPostal", "ciudad", "telefono"};
        String[] campoJugadores = new String[]{"dniJugador", "nombreJugador", "fechaNacimiento", "calle", "codPostal", "ciudad", "telefono"};

        Document documento= new Document("nombreClub", pedirString("Introduce el valor de :nombreClub"));
        documento.append("ciudad", pedirString("Introduzca el valor del campo: ciudad"));
        
        Document docEntrenador=new Document();
        
        for (String campo : campos) {
			docEntrenador.append(campo, pedirString("Introduzca el valor del campo: " + campo));
		}
        
        ArrayList<Document> listaJugadores = new ArrayList<Document>();

        String opcion = null;
        
        do {
        	Document docJugador=new Document();
        	
        	for (String campo : campoJugadores) {
				docJugador.append(campo, pedirString("Introduzca el valor del campo: " + campo));

			}
			listaJugadores.add(docJugador);
			opcion=pedirString("Deseas introducir otro jugador (Si/No)");
			
		} while (opcion.equals("SI"));
        
        documento.append("entrenador", docEntrenador).append("jugadores", listaJugadores);
		coleccion.insertOne(documento);
	}

	private static void crearCollecion() {
		if(database==null) {
			crearBaseDatos();
		}
		String nombreColeccion=pedirString("Introduce el nombre de la coleccion");
		try {
			coleccion=database.getCollection(nombreColeccion);
		} catch (Exception e) {
			database.createCollection(nombreColeccion);
			coleccion=database.getCollection(nombreColeccion);
		}		
	}

	private static void crearBaseDatos() {
		String db_name=pedirString("Introduce el nombre de la bd: ");
		database=mongoClient.getDatabase(db_name);
		
	}

	private static int pedirInt(String mensaje) {
        System.out.println(mensaje);
        while(true) {
            try {
                return sc.nextInt();
            } catch (Exception ignored) {
            }
        }
    }

    private static String pedirString(String mensaje){
        System.out.println(mensaje);
        return sc.next();
    }
    
    

}
