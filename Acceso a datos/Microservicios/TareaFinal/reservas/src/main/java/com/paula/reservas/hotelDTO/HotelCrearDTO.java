package com.paula.reservas.hotelDTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class HotelCrearDTO {

    private String nombre;
    private String direccion;

}
