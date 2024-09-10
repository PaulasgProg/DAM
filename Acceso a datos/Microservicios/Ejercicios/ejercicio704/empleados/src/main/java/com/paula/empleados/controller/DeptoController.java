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

import com.paula.empleados.entities.Depto;
import com.paula.empleados.service.DeptoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/empleados/Depto")
@RequiredArgsConstructor
public class DeptoController {

    private final DeptoService deptoService;

    @GetMapping
    public List<Depto> obtenerDeptos(){
        return deptoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Depto> obtenerDeptoPorId(@PathVariable Long id){
        Depto depto=deptoService.findById(id);
        return ResponseEntity.ok(depto);
    }

    @PostMapping
    public ResponseEntity<String> crearDepto(@RequestBody Depto depto){
        deptoService.createDepto(depto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Departamento creado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Depto> modificarDepto(@PathVariable Long id,@RequestBody Depto deptoActualizado){
        Depto depto=deptoService.findById(id);

        if (depto != null) {
            deptoActualizado.setNumdep(id);
            deptoService.guardar(depto);
            return ResponseEntity.ok(depto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
