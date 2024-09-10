package com.paula.empleados.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptoDTO {
    private Long numdep;
    private String nomdep;
    private String localidad;
    private Long idJefe;

    public  DeptoDTO(Depto depto){
        this.numdep = depto.getNumdep();
        this.nomdep = depto.getNomdep();
        this.localidad = depto.getLocalidad();
        this.idJefe = depto.getJefe().getNumemp();
    }
}