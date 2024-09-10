package com.paula.usuarios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paula.usuarios.dto.UsuarioNombreContrasena;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Usuario usuarioActualizado) {
        Usuario usuario = obtenerInfoUsuarioPorId(usuarioActualizado.getUsuarioId());
        if (usuario != null) {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setDireccion(usuarioActualizado.getDireccion());
            usuario.setCorreo_electronico(usuarioActualizado.getCorreo_electronico());
            usuario.setContrasena(usuarioActualizado.getContrasena());

            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    public Usuario eliminarUsuario(UsuarioNombreContrasena uNombreContrasena) {

        List<Usuario> lista = usuarioRepository.findByNombre(uNombreContrasena.getNombre());
        if (!lista.isEmpty()) {
            Usuario usuarioLista = lista.get(0);
            Usuario usuarioBorrar = new Usuario();

            if (usuarioLista.getContrasena().equals(uNombreContrasena.getContrasena())) {
                usuarioBorrar = usuarioLista;
            }

            usuarioRepository.delete(usuarioBorrar);
            return usuarioBorrar;
        }
        return null;

    }

    public Usuario validarUsuario(UsuarioNombreContrasena usuarioNombreContrasena) {
        List<Usuario> lista = usuarioRepository.findByNombre(usuarioNombreContrasena.getNombre());
        if (!lista.isEmpty()) {
            Usuario usuario = lista.get(0);
            if (usuario != null) {
                if (usuarioNombreContrasena.getContrasena().equals(usuario.getContrasena())) {
                    return usuario;
                }
            }
        }
        return null;
    }

    public Usuario obtenerInfoUsuarioPorId(Integer id) {

        Boolean existe = checkIfExist(id);
        if (existe) {
            return usuarioRepository.findByUsuarioId(id);
        } else {
            return null;
        }

    }

    public Usuario obtenerInfoUsuarioPorNombre(String nombre) {
        List<Usuario> lista = usuarioRepository.findByNombre(nombre);
        if (lista.isEmpty()) {
            return null;
        } else {
            Usuario usuario = lista.get(0);
            return usuario;
        }

    }

    public Boolean checkIfExist(Integer id) {
        Usuario usuario = usuarioRepository.findByUsuarioId(id);
        if (usuario == null) {
            return false;
        } else {
            return true;
        }
    }

}