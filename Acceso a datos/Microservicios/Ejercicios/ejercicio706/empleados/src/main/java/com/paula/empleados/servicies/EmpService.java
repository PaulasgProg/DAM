package com.paula.empleados.servicies;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paula.empleados.entities.Consulta2DTO;
import com.paula.empleados.entities.Depto;
import com.paula.empleados.entities.Emp;
import com.paula.empleados.repositories.EmpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpService {

    // @Autowired se usa para inyeccion de dependencias, crea la instancia
    // automaticamente
    private final EmpRepository empRepository;

    public void guardar(Emp emp) {
        empRepository.save(emp);
    }

    public Emp createDepto(Emp emp) {
        return empRepository.save(emp);
    }

    public Emp findById(Long id) {
        return empRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        empRepository.deleteById(id);
    }

    public Emp updateDepto(Emp emp) {
        return empRepository.save(emp);
    }

    public List<Emp> findAll() {
        return empRepository.findAll();
    }

    public List<String> findByNomempLetraSalarioOComisionDep(Consulta2DTO consulta2dto) {
        return empRepository.findByNomempLetraSalarioOComisionDep(consulta2dto.getLetra() + "%",
                consulta2dto.getSalario(),
                consulta2dto.getIdDepartamento()).stream().map(Emp::getNomemp).collect(Collectors.toList());
    }

    public List<Emp> empleadosNombreFechaNoPuesto(String puesto) {
        return empRepository.empleadosNombreFechaNoPuesto(puesto);
    }

    public List<String> findByComissionIsNotNull() {
        return empRepository.findByComissionIsNotNull().stream().map(Emp::getNomemp).collect(Collectors.toList());
    }

    public List<Emp> empleadoSalarioMayorOtroCompanhero(Long id) {
        return empRepository.empleadoSalarioMayorOtroCompanhero(id);
    }
}
