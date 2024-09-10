package com.paula.comentarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.paula.comentarios.dtos.ComentarioDTO;
import com.paula.comentarios.dtos.ComentarioInput;

@Controller
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @MutationMapping
    public ComentarioDTO crearComentario(@Argument ComentarioInput comentario,
            @Argument String username, @Argument String password) {

        // Logueamos
        if (comentarioService.login(username, password)) {
            // Comprobamos que existe reserva
            if (comentarioService.checkReserva(comentario, username, password)) {
                // Comprobamos si ya existe un comentario
                if (comentarioService.existeComentario(comentario, username, password)) {
                    return null;
                } else {
                    comentarioService.crearComentario(comentario, username, password);
                    ComentarioDTO comentarioDTO = new ComentarioDTO(comentario.getNombreHotel(),
                            comentario.getReservaId(), comentario.getPuntuacion(), comentario.getComentario());
                    return comentarioDTO;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @MutationMapping
    public String eliminarComentarios() {
        Boolean eliminados = comentarioService.eliminarComentarios();
        if (eliminados) {
            return "Se han eliminado los comentarios";
        } else {
            return "No se pudieron eliminar los comentarios";
        }
    }

    @MutationMapping
    public String eliminarComentarioDeUsuario(@Argument String idComentario,
            @Argument String username, @Argument String password) {
        if (comentarioService.login(username, password)) {
            Boolean eliminado = comentarioService.eliminarComentarioDeUsuario(idComentario);
            if (eliminado) {
                return "Se ha eliminado el comentario correctamente";
            } else {
                return "No se ha podido eliminar el comentario";
            }
        } else {
            return "Las credenciales no son correctas";
        }
    }

    @QueryMapping
    public List<ComentarioDTO> listarComentariosHotel(@Argument String nombreHotel,
            @Argument String username, @Argument String password) {
        if (comentarioService.login(username, password)) {
            return comentarioService.listarComentariosHotel(nombreHotel, username, password);
        } else {
            return null;
        }
    }

    @QueryMapping
    public List<ComentarioDTO> listarComentariosUsuario(
            @Argument String username, @Argument String password) {
        if (comentarioService.login(username, password)) {
            return comentarioService.listarComentariosUsuario(username, password);
        } else {
            return null;
        }
    }

    @QueryMapping
    public List<ComentarioDTO> mostrarComentarioUsuarioReserva(@Argument Integer idReserva,
            @Argument String username, @Argument String password) {

        if (comentarioService.login(username, password)) {
            return comentarioService.mostrarComentariosUsuarioReserva(idReserva, username, password);
        } else {
            return null;
        }
    }

    @QueryMapping
    public Double puntuacionMediaHotel(@Argument String nombreHotel,
            @Argument String username, @Argument String password) {
        if (comentarioService.login(username, password)) {
            return comentarioService.puntuacionMediaHotel(nombreHotel, username, password);
        } else {
            return null;
        }
    }

    @QueryMapping
    public Double puntuacionesMediasUsuario(
            @Argument String username, @Argument String password) {
        if (comentarioService.login(username, password)) {
            return comentarioService.puntuacionesMediasUsuario(username, password);
        } else {
            return null;
        }
    }

    @QueryMapping
    public Boolean checkReserva(@Argument ComentarioInput comentario, @Argument String username,
            @Argument String password) {
        return comentarioService.checkReserva(comentario, username, password);
    }

    @QueryMapping
    public Boolean existeComentario(@Argument ComentarioInput comentario, @Argument String username,
            @Argument String password) {
        return comentarioService.existeComentario(comentario, username, password);
    }

}
