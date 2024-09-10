package com.paula.usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paula.usuarios.dto.UsuarioNombreContrasena;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        usuarioService.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario añadido");
    }

    @PutMapping("/registrar")
    public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario) {
        Usuario usuario2 = usuarioService.actualizarUsuario(usuario);
        if (usuario2 != null) {
            return ResponseEntity.ok("Usuario modificado");
        } else {
            return ResponseEntity.ok("No se ha podido modificar el usuario");
        }

    }

    @DeleteMapping
    public ResponseEntity<String> eliminarUsuario(@RequestBody UsuarioNombreContrasena usuario) {
        usuarioService.eliminarUsuario(usuario);
        return ResponseEntity.ok("Usuario eliminado");
    }

    @PostMapping("/validar")
    public ResponseEntity<Boolean> validarUsuario(@RequestBody UsuarioNombreContrasena usuarioNombreContrasena) {
        Usuario user = usuarioService.validarUsuario(usuarioNombreContrasena);
        if (user == null) {
            return ResponseEntity.ok(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }

    @GetMapping("/info/id/{id}")
    public ResponseEntity<String> obtenerInfoUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.obtenerInfoUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario.getNombre());
        } else {
            return ResponseEntity.ok("No exite el usuario");
        }

    }

    @GetMapping("/info/nombre/{nombre}")
    public ResponseEntity<String> obtenerInfoUsuarioPorNombre(@PathVariable String nombre) {
        Usuario Usuario = usuarioService.obtenerInfoUsuarioPorNombre(nombre);

        if (Usuario == null) {
            return ResponseEntity.ok("No se ha encontrado ningun usuario con ese nombre");
        } else {
            return ResponseEntity.ok(Usuario.getUsuarioId().toString());
        }
    }

    @GetMapping("/checkIfExist/{id}")
    public ResponseEntity<Boolean> checkIfExist(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.checkIfExist(id));
    }
}
