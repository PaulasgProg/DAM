package com.paula.empleados.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.Cache;
import org.hibernate.internal.build.AllowPrintStacktrace;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Emp")
@EqualsAndHashCode(exclude = {"depto","deptoJefe"})
public class Emp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numemp;

    @NonNull
    @Column(length = 10,nullable = false)
    private String nomemp;

    @NonNull
    @Column(length = 10)
    private String puesto;

    @NonNull
    @Column(columnDefinition = "DATE")
    private LocalDate feccont;

    @NonNull
    @Column(precision = 7, scale = 2)
    private BigDecimal sal;

    @Column(name="comission",precision = 7, scale = 2)
    private BigDecimal comission;

    @ManyToOne
    @JoinColumn(name = "numdep")
    @ToString.Exclude
    private Depto depto;

    @OneToOne(mappedBy = "jefe")
    @ToString.Exclude
    private Depto deptoJefe;

    public void agregarDepto(Depto depto){
        this.depto=depto;
        depto.addEmpleado(this);
    }

    public void agregarJefe(Depto depto){
        this.deptoJefe=depto;
        depto.setJefe(this);
    }

    public Emp(String nombre, String puesto, LocalDate  date, BigDecimal salario, BigDecimal comision) {
        this.nomemp=nombre;
        this.puesto=puesto;
        this.feccont=date;
        this.sal=salario;
        this.comission=comision;
    }

    
}
