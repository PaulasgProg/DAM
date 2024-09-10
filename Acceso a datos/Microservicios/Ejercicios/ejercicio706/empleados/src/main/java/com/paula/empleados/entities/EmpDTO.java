package com.paula.empleados.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EmpDTO {
    private Long numemp;
    private String nomemp;
    private String puesto;
    private LocalDate feccont;
    private BigDecimal sal;
    private BigDecimal comision;
    private Long iddepto;

    public EmpDTO (Emp emp){
        this.numemp = emp.getNumemp();
        this.nomemp = emp.getNomemp();
        this.puesto = emp.getPuesto();
        this.feccont = emp.getFeccont();
        this.sal = emp.getSal();
        this.comision = emp.getComission();
        this.iddepto = emp.getDepto().getNumdep();
    }
}