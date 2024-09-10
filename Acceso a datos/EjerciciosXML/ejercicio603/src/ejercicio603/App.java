package ejercicio603;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.basex.examples.api.BaseXClient;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class App {

	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		 int opcion = 0;
	     String nombreBd;
	     
	     try (BaseXClient session=new BaseXClient("localhost", 1984, "admin", "abc123")){
			do {
				 opcion = pedirInt("1. Crear base de datos\n" +
	                        "2. Seleccionar base de datos\n" +
	                        "3. Eliminar base de datos\n" +
	                        "4. Salir");

	                switch (opcion) {
	                    case 1:
	                        nombreBd = pedirString("Introduzca el nombre de la base de datos: ");
	                        crearBd(session, nombreBd);
	                        break;
	                    case 2:
	                        nombreBd = pedirString("Introduzca el nombre de la base de datos: ");
	                        seleccionarBD(session, nombreBd);
	                        break;
	                    case 3:
	                        nombreBd = pedirString("Introduzca el nombre de la base de datos: ");
	                        eliminarBD(session, nombreBd);
	                        break;
	                    case 4:
	                        break;
	                }
			}while(opcion!=4);
		} catch (IOException e) {
			 throw new RuntimeException(e);
		}
	}
	
	 private static void eliminarBD(BaseXClient session, String nombreBd) {
		//comprobamos si existe
			try(BaseXClient.Query query=session.query("db:get('" + nombreBd + "')")){
				session.execute("drop bd "+nombreBd);
				System.out.println("bd eliminada");
			}catch (Exception e) {
				System.out.println("No existe esa bd");
			}
		
	}

	private static void seleccionarBD(BaseXClient session, String nombreBd) {
		try(BaseXClient.Query query = session.query("db:get('" + nombreBd + "')" )) {
            int opcion;
            do{
                opcion = pedirInt("1. Gestión de documentos\n" +
                        "2. Realizar consultas\n" +
                        "3. Atrás");
                switch (opcion){
                    case 1:
                        gestionDocumentos(session, nombreBd);
                        break;
                    case 2:
                        realizarConsultas(session, nombreBd);
                        break;
                }
            }while(opcion != 3);

        }catch (Exception e){}
		
	}

	private static void realizarConsultas(BaseXClient session, String nombreBd) {
		int opcion;
        do{
            opcion = pedirInt("1. Listar número total de documentos\n" +
                    "2. Listar por campos\n" +
                    "3. Atrás");
            switch (opcion){
                case 1:
                    String numero = ejecutarConsultaUnitaria(session, "count(for $i in db:get('" + nombreBd + "') return $i)");
                    System.out.println(numero);
                    break;
                case 2:
                    String estructura = ejecutarConsultaUnitaria(session, "let $documentos := for $i in db:get('" + nombreBd + "')/ return $documentos[1]");
                    Document doc = parsear(estructura);
                    break;
            }
        }while(opcion != 3);
		
	}

	private static void gestionDocumentos(BaseXClient session, String nombreBd) {
		int opcion;
        // Abrimos la base de datos que vamos a utilizar
        try {
			session.execute("open " + nombreBd);
			do{
	            opcion = pedirInt("1. Añadir nuevo documento\n" +
	                    "2. Modificar documento\n" +
	                    "3. Eliminar documentos.\n" +
	                    "4. Atrás");
	            switch (opcion){
	                case 1:
	                    anhadirDocumento(session, nombreBd);
	                    break;
	                case 2:
	                    modificarDocumento(session, nombreBd);
	                    break;
	                case 3:
	                    eliminarDocumento(session, nombreBd);
	                    break;
	            }
	        }while(opcion != 3);
	        session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		
	}

	private static void eliminarDocumento(BaseXClient session, String nombreBd) {
		ArrayList<String> resultados = ejecutarConsultaMultiple(session, "for $i in db:get('" + nombreBd + "') return $i");
        for(int i = 0; i < resultados.size(); i++)
            System.out.println(i + " --> " + resultados.get(i));

        int posicion = pedirInt("Indique la posición del documento a reemplazar: ");
        if(resultados.size() == 1 && posicion == 0){
            try {
				session.execute("drop database " + nombreBd);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else{
            ejecutarConsultaUnitaria(session, "delete node db:get('" + nombreBd + "')[" + (posicion + 1) + "]");
        }
		
	}

	private static void modificarDocumento(BaseXClient session, String nombreBd) {
		ArrayList<String> resultados= ejecutarConsultaMultiple(session, "for $i in db:get('" + nombreBd + "') return $i");
		
		for(int i = 0; i < resultados.size(); i++) {
			System.out.println(i + " --> " + resultados.get(i));
		}
		
		int posicion = pedirInt("Indique la posición del documento a reemplazar: ");
        String resultado = "";
        do {
            String campoModificar = pedirString("Introduzca el campo que quiere cambiar: ");
            String nuevoValorCampo = pedirString("Nuevo valor del campo: ");

            resultado = ejecutarConsultaUnitaria(session, "replace value of node db:get('" + nombreBd + "')[" + (posicion + 1) + "]//" + campoModificar + " " +
                    "with '" + nuevoValorCampo + "'");
        }while(resultado != null);
		
	}

	private static void anhadirDocumento(BaseXClient session, String nombreBd) {
		int num=Integer.parseInt(ejecutarConsultaUnitaria(session, "count(for $i in db:get('"+ nombreBd+"') return $i)"));
		
		do {
			String documento=pedirString("Introduzca el documento xml");
			try {
				Document doc=parsear(documento);
				doc.getDocumentElement().normalize();
				session.add(nombreBd + "/doc" + num + ".xml", new ByteArrayInputStream(documento.getBytes()));
				return;
				
			} catch (Exception e) {
				System.out.println("El documento no es válido. Vuelve a introducirlo");
			}
		} while (true);
		
	}
	
	private static Document parsear(String xml){
		DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
		DocumentBuilder builder= null;
		Document doc;
		 try {
			builder=factory.newDocumentBuilder();
			doc= builder.parse(new InputSource(new StringReader(xml)));
		 } catch (ParserConfigurationException e) {
	            throw new RuntimeException(e);
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        } catch (SAXException e) {
	            throw new RuntimeException(e);
	        }
	        return doc;
	}
	  private static String pedirXML(String introduzcaElDocumentoXml) {
	        StringBuilder fileXML = new StringBuilder();
	        System.out.println(introduzcaElDocumentoXml);
	        String linea;
	        while(!(linea = sc.next()).equalsIgnoreCase("fin"))
	            fileXML.append(linea);

	        return fileXML.toString();
	   }
	  
	  private static ArrayList<String> ejecutarConsultaMultiple(BaseXClient sesion, String consulta) {
	        ArrayList<String> lista = new ArrayList<>();
	        // Comprobamos si existe o si no la BD
	        try(BaseXClient.Query query = sesion.query(consulta )) {

	            // Comprobación e iteración de los resultados
	            while(query.more()) {
	                lista.add(query.next());
	            }
	        }catch (Exception e){}
	        return lista;
	    }

	    private static String ejecutarConsultaUnitaria(BaseXClient sesion, String consulta) {
	        // Comprobamos si existe o si no la BD
	        try(BaseXClient.Query query = sesion.query(consulta )) {

	            // Comprobación e iteración de los resultados
	            if(query.more()) {
	                return query.next();
	            }
	        }catch (Exception e){}
	        return null;
	    }

	private static void crearBd(BaseXClient session, String nombreBd) {
		//comprobamos si existe
		try(BaseXClient.Query query=session.query("db:get('" + nombreBd + "')")){
			if(query.more()) {
				System.out.println("Ya existe la bd");
			}
		}catch (Exception e) {
			try {
				session.execute("create db "+nombreBd);
				System.out.println("Base de datos creada");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	private static String pedirString(String mensaje) {
	        while(true){
	            try{
	                System.out.println(mensaje);
	                return sc.next();
	            }catch (Exception e){}
	        }
	    }

	    private static int pedirInt(String mensaje) {
	        while(true){
	            try{
	                System.out.println(mensaje);
	                return sc.nextInt();
	            }catch (Exception e){}
	        }
	    }
}
