package com.paula.autores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    /*
     * Agregar nuevo Autor.
     * Modificar datos de un Autor.
     * Dado un identificador devolver unicamente el nombre de un Autor.
     * Dado un nombre de autor devolverá su identificador.
     */
    @Autowired
    private AutorRepository autorRepository;

    public Autor agregarAutor(Autor autorNuevAutor) {
        return autorRepository.save(autorNuevAutor);
    }

    public Autor actualizarAutor(Long id, Autor autorActualizado) {
        autorActualizado.setId(id);
        return autorRepository.save(autorActualizado);
    }

    public Autor findAutorPorNombre(String nombre) {
        return autorRepository.findByNombre(nombre);
    }

    public Autor obtenerNombrePorId(Long id) {
        return autorRepository.findById(id).orElse(null);
    }
}
