package model;

import gui.VentanaBorrarUsuario;
import gui.VentanaCambiarContraseña;
import gui.VentanaCrearUsuario;
import gui.VentanaInicioSesion;
import gui.VentanaMenuUsuario;
import gui.VentanaVerUsuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;

public class AplicacionUsuarios {

	private final String RUTA_FICHERO = "";
	private VentanaInicioSesion ventanaInicioSesion;
	private VentanaCrearUsuario ventanaCrearUsuario;
	private VentanaMenuUsuario ventanaMenuUsuario;
	private VentanaVerUsuario ventanaVerUsuario;
	private VentanaCambiarContraseña ventanaCambiarContraseña;
	private VentanaBorrarUsuario ventanaBorrarUsuario;

	private void crearFicheroJson() {

	}

	private JSONArray obtenerUsuariosJson() {

	}

	private int obtenerPosicionUsuario(String nombreUsuario, JSONArray usuarios) {

	}

	private JSONObject obtenerUsuarioJson(String nombreUsuario) {

	}

	public void ejecutar() {

	}

	public void iniciarSesion(String nombreUsuario, String contraseñaUsuario) {

	}

	public void cerrarSesion() {

	}

	public void crearUsuario(String nombre, String contraseña, String edad, String correo) {

	}

	public void cambiarContraseña(String nombreUsuario, String nuevaContraseña) {

	}

	public void borrarUsuario(String nombreUsuario) {

	}

	public void mostrarVentanaCrearUsuario() {

	}

	public void mostrarVentanaVerUsuario(String nombreUsuario) {

	}

	public void mostrarVentanaCambiarContraseña(String nombreUsuario) {

	}

	public void mostrarVentanaBorrarUsuario(String nombreUsuario) {

	}
}
