package ejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EjPokemon {
	/*Insertar un nuevo pokemon.
	Modificar un pokemon.
	Eliminar un pokemon.*/
	private static String user="postgres";
	private static String pwd="abc1234.";
	private static String url="jdbc:postgresql://localhost:5432/";
	private static String bd="pokemons";
	
	static Scanner scanner;
	
    private static void insertarPokemon(Connection conn) throws SQLException {
        System.out.print("Nombre del Pokémon: ");
        String nombre = scanner.nextLine();
        System.out.print("Tipo del Pokémon: ");
        String tipo = scanner.nextLine();
        System.out.print("Nivel del Pokémon: ");
        int nivel = scanner.nextInt();

        String insertSQL = "INSERT INTO objetos.Pokemons (pokemon) VALUES (ROW(?, ?, ?))";
        PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
        preparedStatement.setString(1, nombre);
        preparedStatement.setString(2, tipo);
        preparedStatement.setInt(3, nivel);

        int rowsInserted = preparedStatement.executeUpdate();

        if (rowsInserted > 0) {
            System.out.println("Pokémon insertado con éxito.");
        } else {
            System.out.println("No se pudo insertar el Pokémon.");
        }
    }

    private static void modificarPokemon(Connection conn) throws SQLException {

        System.out.print("ID del Pokémon a modificar: ");
        int idPokemon = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        System.out.print("Nuevo nombre del Pokémon: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Nuevo tipo del Pokémon: ");
        String nuevoTipo = scanner.nextLine();
        System.out.print("Nuevo nivel del Pokémon: ");
        int nuevoNivel = scanner.nextInt();

        String updateSQL = "UPDATE objetos.Pokemons SET pokemon = ROW(?, ?, ?) WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(updateSQL);
        preparedStatement.setString(1, nuevoNombre);
        preparedStatement.setString(2, nuevoTipo);
        preparedStatement.setInt(3, nuevoNivel);
        preparedStatement.setInt(4, idPokemon);

        int rowsUpdated = preparedStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Pokémon actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar el Pokémon. Verifique el ID.");
        }
    }

    private static void eliminarPokemon(Connection conn) throws SQLException {

        System.out.print("ID del Pokémon a eliminar: ");
        int idPokemon = scanner.nextInt();

        String deleteSQL = "DELETE FROM objetos.Pokemons WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, idPokemon);

        int rowsDeleted = preparedStatement.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Pokémon eliminado con éxito.");
        } else {
            System.out.println("No se pudo eliminar el Pokémon. Verifique el ID.");
        }
    }

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection(url + bd, user, pwd)) {
            scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Menú:");
                System.out.println("1. Insertar Pokémon");
                System.out.println("2. Modificar Pokémon");
                System.out.println("3. Eliminar Pokémon");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 1:
                        insertarPokemon(conn);
                        break;
                    case 2:
                        modificarPokemon(conn);
                        break;
                    case 3:
                        eliminarPokemon(conn);
                        break;
                    case 4:
                        System.out.println("¡Hasta luego!");
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner.close();
    }
    }
