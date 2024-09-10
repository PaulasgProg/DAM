package com.paula.reservas.reservaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservaIdEstadoDTO {
    private int reservaId;
    private String estado;

}
