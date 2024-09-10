package com.paula.empleados.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.LongSummaryStatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paula.empleados.entities.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp,Long>{

    @Query("select e from Emp e where (e.nomemp like :letra and e.sal>:salario) or (e.comission !=null and e.deptoJefe.numdep=:numdep)")
    List<Emp> findByNomempLetraSalarioOComisionDep(@Param("letra") String letra,
                                                    @Param("salario") double salario,
                                                    @Param("numdep") Long numdep);
    
    @Query("select e from Emp e where e.puesto!=:puesto")
    List<Emp> empleadosNombreFechaNoPuesto(@Param("puesto") String puesto);

    List<Emp> findByComissionIsNotNull();

    @Query("select e from Emp e where e.sal> (select e1.sal from Emp e1 where e1.numemp=:idemp) order by sal")
    List<Emp> empleadoSalarioMayorOtroCompanhero(@Param("idemp") Long id);
}
