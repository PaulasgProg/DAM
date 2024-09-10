package com.paula.empleados.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paula.empleados.entities.Depto;

/*La entidad o dominio que se va a utilizar.
El tipo de dato del Id. */

@Repository
public interface DeptoRepository extends JpaRepository<Depto,Long>{

    @Query("select d from Depto d where d.nomdep not in (:nom1, :nom2)")
    List<Depto> findByNomdepIsNotAndNomdepIsNot(@Param("nom1") String nom1,@Param("nom2") String nom2);
}
