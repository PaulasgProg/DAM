package ejercicio612;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;

public class App {
	
	static Scanner sc;
	static MongoClient mongoClient;
	static MongoDatabase database=null;
	private static MongoCollection<Document> coleccion;

	public static void main(String[] args) {
		sc=new Scanner(System.in);
		mongoClient=MongoClients.create("mongodb+srv://paulasotelo99:abc123.@cluster0.rh5crpr.mongodb.net/");
		int option;
		do {
			option=pedirInt("1.Crear una base de datos llamada F1DB\r\n"
					+ "2.Crear una colección equipos que almacene: el nombre, el país y el año de fundación \r\n"
					+ "  de los equipos de F1 y añade dos ejemplos\r\n"
					+ "3.Crea una colección pilotos que almacene: \r\n "
					+ "  el nombre, la nacionalidad, el nombre del equipo y los puntos de un piloto de F1 y añade dos ejemplos\r\n"
					+ "4.Crea una colección carreras que almacene información sobre carreras de F1.\r\n"
					+ " Se desea almacenar: nombre del gran premio, la fecha y el ganador. Añade dos ejemplos.\r\n"
					+ "5.Consulta 1: Muestra todos los equipos de Formula 1.\r\n"
					+ "6.Consulta 2: Muestra todos los pilotos del equipo Mercedes\r\n"
					+ "7.Consulta 3: Muestra la información de una carrera concreta.\r\n"
					+ "8.Consulta 4: Muestra todos los pilotos ordenados por puntos de mayor a menor.\r\n"
					+ "9.Consulta 5: Actualiza los puntos de un piloto después de ganar la carrera (una victoria supone ganar 25 puntos)\r\n"
					+ "10.Consulta 6: Elimina un equipo y todos sus pilotos\r\n"
					+ "11.Consulta 7: Muestra todos los pilotos que han ganado al menos una carrera\r\n"
					+ "12.Consulta 8: Suma los puntos de todos los pilotos\r\n"
					+ "13.Consulta 9: Actualiza la fecha de realización de un gran premio\r\n"
					+ "14.Consulta 10: Muestra los equipos fundados antes de un determinado año\r\n"
					+ "15.Consulta 11: Incrementa los puntos de todos los pilotos en 5 puntos\r\n"
					+ "16.Consulta 12: Muestra la cantidad total de puntos por nacionalidad de los pilotos\r\n"
					+ "17.Salir");
			
			switch (option) {
			case 1://Crear una base de datos llamada F1DB\
				crearBaseDatos();
				break;
			case 2://Crear una colección equipos que almacene: el nombre, el país y el año de fundación
				crearColeccion();
				break;
			case 3://.Crea una colección pilotos que almacene:
				//el nombre, la nacionalidad, el nombre del equipo y los puntos de un piloto de F1 y añade dos ejemplos
				crearColeccionPilotos();
				break;
			case 4://Crea una colección carreras que almacene información sobre carreras de F1.\r\n"
				//Se desea almacenar: nombre del gran premio, la fecha y el ganador. Añade dos ejemplos.
				crearColeccionCarreras();
				break;
			case 5://Consulta 1: Muestra todos los equipos de Formula 1
				mostrarEquiposF1();
				break;
			case 6://Consulta 2: Muestra todos los pilotos del equipo Mercedes
				mostrarPilotosMercedes();
				break;
			case 7://Consulta 3: Muestra la información de una carrera concreta.
				mostrarInfoCarrera();
				break;
			case 8://.Consulta 4: Muestra todos los pilotos ordenados por puntos de mayor a menor.
				mostrarPilotosPuntos();
				break;
			case 9://Consulta 5: Actualiza los puntos de un piloto después de ganar la carrera (una victoria supone ganar 25 puntos)
				actualizarPuntos();
				break;
			case 10://Consulta 6: Elimina un equipo y todos sus pilotos
				eliminaEquipoyPilotos();
				break;
			case 11://Consulta 7: Muestra todos los pilotos que han ganado al menos una carrera
				muestraPilotosCarrera();
				break;
			case 12://Consulta 8: Suma los puntos de todos los pilotos\r\n"
				sumarPuntosPilotos();
				break;
				
			case 13://"13.Consulta 9: Actualiza la fecha de realización de un gran premio\r\n"
				actualizaFecha();
				break;
			case 14://+ "14.Consulta 10: Muestra los equipos fundados antes de un determinado año\r\n"
				equiposFundadosPorAño();
				break;
			case 15://+ "15.Consulta 11: Incrementa los puntos de todos los pilotos en 5 puntos\r\n"
				inccrementaPuntosPilotos();
				break;
			case 16://+ "16.Consulta 12: Muestra la cantidad total de puntos por nacionalidad de los pilotos\r\n"
				totalPuntosPorNacionalidad();
				break;
			case 17://+ "17.Salir"
				break;
			default:
				break;
			}
		}while(option!=17);
		
		
		
	}
	

	private static void totalPuntosPorNacionalidad() {
		String nombreColeccion="pilotos";
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection(nombreColeccion);
        }catch (Exception e){
            database.createCollection(nombreColeccion);
            coleccion = database.getCollection(nombreColeccion);
        }
        AggregateIterable<Document> doc=coleccion.aggregate(
        		Arrays.asList(
        				Aggregates.group("$nacionalidad", Accumulators.sum("totalPuntos", "$puntos"))));
        
        for (Document document : doc) {
			System.out.println(document);
		}
		
	}


	private static void inccrementaPuntosPilotos() {
		String nombreColeccion="pilotos";
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection(nombreColeccion);
        }catch (Exception e){
            database.createCollection(nombreColeccion);
            coleccion = database.getCollection(nombreColeccion);
        }
        UpdateResult ur=coleccion.updateMany(new Document(), inc("puntos", 5));
        
        if(ur.getModifiedCount() > 0){
            System.out.println("Actualizacion correcta. " + ur.getModifiedCount() + " documentos actualizados.");
        }else{
            System.out.println("No se ha actualizado ningún documento");
        }
		
	}


	private static void equiposFundadosPorAño() {
		//.Consulta 10: Muestra los equipos fundados antes de un determinado año
		String nombreColeccion="equipos";
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection(nombreColeccion);
        }catch (Exception e){
            database.createCollection(nombreColeccion);
            coleccion = database.getCollection(nombreColeccion);
        }
        int ano=pedirInt("Introduce un año");
        FindIterable<Document> doc=coleccion.find(lt("anho",ano));
        
        for (Document document : doc) {
			System.out.println(document);
		}
	}


	private static void actualizaFecha() {
		// .Consulta 9: Actualiza la fecha de realización de un gran premio
		String nombreColeccion="carreras";
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection(nombreColeccion);
        }catch (Exception e){
            database.createCollection(nombreColeccion);
            coleccion = database.getCollection(nombreColeccion);
        }
        String nombrePremio=pedirString("Introduce el nombre del premio");
        
        UpdateResult ur=coleccion.updateOne(new Document("nombre",nombreColeccion), set("fecha", new Date()));
        if (ur.getModifiedCount()>0) {
        	System.out.println("Actualizacion correcta. " + ur.getModifiedCount() + " documentos actualizados.");
        }else{
            System.out.println("No se ha actualizado ningún documento");
        }
        
	}


	private static void sumarPuntosPilotos() {
		//Consulta 8: Suma los puntos de todos los pilotos\r\n"
		String nombreColeccion="pilotos";
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection(nombreColeccion);
        }catch (Exception e){
            database.createCollection(nombreColeccion);
            coleccion = database.getCollection(nombreColeccion);
        }
        
        
        AggregateIterable<Document> agiter=coleccion.aggregate(
        		Arrays.asList(
        				Aggregates.group("puntos", Accumulators.sum("totalPuntos", "$puntos"))));
        
        for (Document document : agiter) {
			System.out.println(document);
		}
		
	}


	private static void muestraPilotosCarrera() {
		//Consulta 7: Muestra todos los pilotos que han ganado al menos una carrera
		String nombreColeccion="pilotos";
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection(nombreColeccion);
        }catch (Exception e){
            database.createCollection(nombreColeccion);
            coleccion = database.getCollection(nombreColeccion);
        }
        FindIterable<Document> doc=coleccion.find(gt("puntos",0));
        
        for (Document document : doc) {
			System.out.println(document);
		}
	}


	private static void eliminaEquipoyPilotos() {
		//Consulta 6: Elimina un equipo y todos sus pilotos
		String equipo=pedirString("Introduce el equipo a eliminar");
		String nombreColeccion="equipos";
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection(nombreColeccion);
        }catch (Exception e){
            database.createCollection(nombreColeccion);
            coleccion = database.getCollection(nombreColeccion);
        }
		DeleteResult dr=coleccion.deleteMany(new Document("nombre",equipo));
		
		if (dr.getDeletedCount()>0) {
			nombreColeccion="pilotos";
			if(database == null)
	            database = mongoClient.getDatabase("F1DB");
	        try{
	            coleccion = database.getCollection(nombreColeccion);
	        }catch (Exception e){
	            database.createCollection(nombreColeccion);
	            coleccion = database.getCollection(nombreColeccion);
	        }
	        DeleteResult dr2=coleccion.deleteMany(new Document("equipo",equipo));
	        if (dr2.getDeletedCount() > 0){
                System.out.println("Equipo y sus pilotos eliminados");
            }else{
                System.out.println("Se ha eliminado el equipo no había pilotos que eliminar");
            }	        
		}else{
            System.out.println("No hay ningún equipo con ese nombre");
        }
	}


	private static void actualizarPuntos() {
		//Consulta 5: Actualiza los puntos de un piloto después de ganar la carrera (una victoria supone ganar 25 puntos)
		String nombreColeccion="pilotos";
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection(nombreColeccion);
        }catch (Exception e){
            database.createCollection(nombreColeccion);
            coleccion = database.getCollection(nombreColeccion);
        }
        String ganador=pedirString("Introduce el nombre del ganador:");
        UpdateResult ur=coleccion.updateOne(new Document("nombre",ganador), inc("puntos", 25));
		
        if(ur.getModifiedCount() > 0){
            System.out.println("Actualizacion correcta. " + ur.getModifiedCount() + " documentos actualizados.");
        }else{
            System.out.println("No se ha actualizado ningún documento");
        }
	}


	private static void mostrarPilotosPuntos() {
		//Consulta 4: Muestra todos los pilotos ordenados por puntos de mayor a menor.
		String nombreColeccion="pilotos";
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection(nombreColeccion);
        }catch (Exception e){
            database.createCollection(nombreColeccion);
            coleccion = database.getCollection(nombreColeccion);
        }
        FindIterable<Document> iterDoc=coleccion.find().sort(descending("puntos"));
        
        for (Document document : iterDoc) {
			System.out.println(document);
		}
		
	}


	private static void mostrarInfoCarrera() {
		String nombreColeccion="carreras";
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection(nombreColeccion);
        }catch (Exception e){
            database.createCollection(nombreColeccion);
            coleccion = database.getCollection(nombreColeccion);
        }
        String carrera=pedirString("Introduce nombre de carrera:");
        FindIterable<Document> iterDoc=coleccion.find(new Document("nombre",carrera));
        
        for (Document document : iterDoc) {
			System.out.println(document);
		}
	}


	private static void mostrarPilotosMercedes() {
		String nombreColeccion="pilotos";
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection(nombreColeccion);
        }catch (Exception e){
            database.createCollection(nombreColeccion);
            coleccion = database.getCollection(nombreColeccion);
        }
        Document doc=new Document("equipo","Mercedes");
        FindIterable<Document> iterDoc=coleccion.find(doc);
		
        for (Document document : iterDoc) {
			System.out.println(document);
		}
	}


	
	private static void mostrarEquiposF1() {
		String nombreColeccion="equipos";
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection(nombreColeccion);
        }catch (Exception e){
            database.createCollection(nombreColeccion);
            coleccion = database.getCollection(nombreColeccion);
        }
		FindIterable<Document> iterDoc=coleccion.find();
		
		for (Document document : iterDoc) {
			System.out.println(document);
		}
		
	}


	private static void crearColeccionCarreras() {
		//Crea una colección carreras que almacene información sobre carreras de F1.\r\n"
		//Se desea almacenar: nombre del gran premio, la fecha y el ganador. Añade dos ejemplos.
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection("carreras");
        }catch (Exception e){
            database.createCollection("carreras");
            coleccion = database.getCollection("carreras");
        }
        HashMap<String, String> parametrosCarreras=new HashMap<>();
        parametrosCarreras.put("nombre", "String");
        parametrosCarreras.put("fecha", "date");
        parametrosCarreras.put("ganador", "String");
        
        Document docCarreras=crearDocumento(parametrosCarreras);
        docCarreras.append("fecha", new Date());
        
        coleccion.insertOne(docCarreras);
		
	}


	private static void crearColeccionPilotos() {
		//.Crea una colección pilotos que almacene:
		//el nombre, la nacionalidad, el nombre del equipo y los puntos de un piloto de F1 y añade dos ejemplos
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection("pilotos");
        }catch (Exception e){
            database.createCollection("pilotos");
            coleccion = database.getCollection("pilotos");
        }
        HashMap<String, String> parametrosPiloto=new HashMap<>();
        parametrosPiloto.put("nombre", "String");
        parametrosPiloto.put("nacionalidad", "String");
        parametrosPiloto.put("equipo", "String");
        parametrosPiloto.put("puntos", "int");
        
        Document docPilotos=crearDocumento(parametrosPiloto);
        coleccion.insertOne(docPilotos);
		
	}


	private static void crearColeccion() {
		/*2.Crear una colección equipos que almacene: el nombre, el país y el año de fundación \r\n"
		    de los equipos de F1 y añade dos ejemplos*/
	
		if(database == null)
            database = mongoClient.getDatabase("F1DB");
        try{
            coleccion = database.getCollection("equipos");
        }catch (Exception e){
            database.createCollection("equipos");
            coleccion = database.getCollection("equipos");
        }
		HashMap<String, String> parametrosEquipo=new HashMap<>();
		parametrosEquipo.put("nombre", "String");
		parametrosEquipo.put("pais", "String");
		parametrosEquipo.put("anho", "int");
		
		Document docEquipo=crearDocumento(parametrosEquipo);
		coleccion.insertOne(docEquipo);
		
	}
	private static Document crearDocumento(HashMap<String, String> parametrosPedido) {
		Document document=new Document();
		for (Map.Entry<String, String> entry : parametrosPedido.entrySet()) {
			if (entry.getValue().equalsIgnoreCase("int")) {
				document.append(entry.getKey(), pedirInt("Introduce el valor del campo:"+entry.getKey()));
				
			}else if(entry.getValue().equalsIgnoreCase("string")){
                document.append(entry.getKey(), pedirString("Introduzca el valor del campo: " + entry.getKey()));

            }else if(entry.getValue().equalsIgnoreCase("double")){
                document.append(entry.getKey(), pedirDouble("Introduzca el valor del campo: " + entry.getKey()));

            }else if(entry.getValue().equalsIgnoreCase("boolean")){
                String entrega = pedirString("Introduzca el valor del campo: " + entry.getKey());
                document.append(entry.getKey(), entrega.equalsIgnoreCase("si"));
            }
		}
		return document;
	}


	private static void crearBaseDatos() {
		if (database==null) {
			database=mongoClient.getDatabase("F1DB");
		}
			
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
    private static Object pedirDouble(String mensaje) {
		System.out.println(mensaje);
        while(true) {
            try {
                return Double.parseDouble(sc.next());
            } catch (Exception ignored) {
            }
        }

	}
    
}
