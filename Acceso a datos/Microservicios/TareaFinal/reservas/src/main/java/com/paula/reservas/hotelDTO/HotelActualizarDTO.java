package com.paula.reservas.hotelDTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class HotelActualizarDTO {

    private int id;
    private String nombre;
    private String direccion;

}
