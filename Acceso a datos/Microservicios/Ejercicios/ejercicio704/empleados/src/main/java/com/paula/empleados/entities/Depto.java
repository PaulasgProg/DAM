package com.paula.empleados.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor//Esta es una anotación de Lombok que genera automáticamente un 
                        //constructor que toma todos los campos marcados como final o @NonNull como argumentos
@NoArgsConstructor //Genera automáticamente un constructor sin argumentos para la clase. 
@Table(name="Depto")
@EqualsAndHashCode(exclude = {"empleados", "jefe"}) //es una anotación de Lombok que genera automáticamente los métodos equals() y hashCode()
public class Depto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numdep;

    @NonNull
    private String nomdep;

    @NonNull
    private String localidad;
    
    @OneToMany(mappedBy = "depto")
    @ToString.Exclude
    private List<Emp> empleados=new ArrayList<>();

    @OneToOne
    @JoinColumn(name="codjefe")
    @ToString.Exclude //útil para evitar problemas de recursión infinita cuando se imprimen objetos que tienen relaciones bidireccionales
    private Emp jefe;

    public void agregarEmpleado(Emp emp ){
        this.empleados.add(emp);
        emp.setDepto(this);
    }

    public void addEmpleado(Emp emp){
        this.empleados.add(emp);
    }
    public void agregarefe(Emp emp){
        this.jefe=emp;
        emp.setDeptoJefe(this);
    }
}
