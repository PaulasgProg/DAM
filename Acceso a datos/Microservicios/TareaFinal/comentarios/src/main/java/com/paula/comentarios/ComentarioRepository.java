package com.paula.comentarios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ComentarioRepository extends MongoRepository<ComentarioEntity, String> {

        @Query("{ 'usuarioId': ?0, 'hotelId': ?1, 'reservaId': ?2 }")
        Optional<ComentarioEntity> findByReservaIdUsuarioIdHotelId(int usuarioId, int hotelId, int reservaId);

        Optional<ComentarioEntity> findById(String id);

        List<ComentarioEntity> findByHotelId(int hotelId);

        List<ComentarioEntity> findByUsuarioId(int usuarioId);

        @Query("{ 'usuarioId': ?0, 'reservaId': ?1 }")
        List<ComentarioEntity> findByReservaIdUsuarioId(int usuarioId, int reservaId);

        @Aggregation(pipeline = {
                        "{ $match: { 'hotelId': ?0 } }",
                        "{ $group: { _id: '$hotelId', mediaPuntos: { $avg: '$puntuacion' } } }"
        })
        Double puntiacionMediaHotel(int hotelId);

        @Aggregation(pipeline = {
                        "{ $match: { 'usuarioId': ?0 } }",
                        "{ $group: { _id: '$usuarioId', mediaPuntos: { $avg: '$puntuacion' } } }"
        })
        Double puntiacionMediasUsuario(int usuarioId);
}
