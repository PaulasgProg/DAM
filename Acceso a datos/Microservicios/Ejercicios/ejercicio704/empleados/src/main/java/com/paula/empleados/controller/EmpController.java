package com.paula.empleados.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paula.empleados.entities.Emp;
import com.paula.empleados.service.EmpService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/empleados/Emp")
@RequiredArgsConstructor
public class EmpController {

    private final EmpService empService;

    @GetMapping
    public List<Emp> obtenerEmpleados(){
        return empService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emp> obtenerEmpleadoPorId(@PathVariable Long id){
        Emp emp=empService.findById(id);
        return ResponseEntity.ok(emp);
    }

    @PostMapping
    public ResponseEntity<String> crearEmpleado(@RequestBody Emp emp){
        empService.createDepto(emp);
        return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado un empleado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emp> modificarEmpleado(@PathVariable Long id,@RequestBody Emp empActEmp){
        Emp emp=empService.findById(id);
        if (emp != null) {
            empActEmp.setNumemp(id);
            empService.guardar(empActEmp);
            return ResponseEntity.ok(empActEmp);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
