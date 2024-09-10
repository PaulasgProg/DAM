package com.paula.empleados.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paula.empleados.entities.Consulta2DTO;
import com.paula.empleados.entities.Consulta3DTO;
import com.paula.empleados.entities.Depto;
import com.paula.empleados.entities.DeptoDTO;
import com.paula.empleados.entities.Emp;
import com.paula.empleados.entities.EmpDTO;
import com.paula.empleados.service.DeptoService;
import com.paula.empleados.service.EmpService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class AppController {

    private final DeptoService deptoService;
    private final EmpService empService;

     @GetMapping("/Consulta1/{nomDep1}-{nomDep2}")
    public ResponseEntity<List<DeptoDTO>> findByNomdepIsNotAndNomdepIsNot(@PathVariable String nomDep1, @PathVariable String nomDep2){
        List<DeptoDTO> deptos = deptoService.findByNomdepIsNotAndNomdepIsNot(nomDep1, nomDep2)
                .stream()
                .map(DeptoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(deptos);
    }

    @PostMapping("/Consulta2")
    public ResponseEntity<List<String>> nombreEmpleadoLetraSalariooComicionDepartamento(@RequestBody Consulta2DTO datos){
        return ResponseEntity.ok(empService.findByNomempLetraSalarioOComisionDep(datos));
    }


    @GetMapping("/Consulta3/{puesto}")
    public ResponseEntity<List<Consulta3DTO>> nombreyFechaEmpleadosNoPuesto(@PathVariable String puesto){
        return ResponseEntity.ok(empService.empleadosNombreFechaNoPuesto(puesto).stream().map(Consulta3DTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/Consulta4")
    public ResponseEntity<List<String>> empleadosComision(){
        return ResponseEntity.ok(empService.findByComissionIsNotNull());
    }

    @GetMapping("/Consulta5/{id}")
    public ResponseEntity<List<EmpDTO>> empleadosSalarioMayorASalariodeOtroCompanhero(@PathVariable Long id){
        List<EmpDTO> listaEmpDto = empService.empleadoSalarioMayorOtroCompanhero(id).stream()
                .map(EmpDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaEmpDto);
    }


    @GetMapping("/asignar/{idDept}-{idEmp}")
    public ResponseEntity<DeptoDTO> asignarDepartamento(@PathVariable Long idDept, @PathVariable Long idEmp ){
        Depto depto = deptoService.findById(idDept);
        Emp emp = empService.findById(idEmp);
        depto.agregarEmpleado(emp);
        deptoService.guardar(depto);
        return ResponseEntity.ok(new DeptoDTO(depto));
    }

    @GetMapping("/jefe/{idDept}-{idEmp}")
    public ResponseEntity<DeptoDTO> modificarJefe(@PathVariable Long idDept, @PathVariable Long idEmp ){
        Depto depto = deptoService.findById(idDept);
        Emp emp = empService.findById(idEmp);
        depto.agregarefe(emp);
        deptoService.guardar(depto);
        return ResponseEntity.ok(new DeptoDTO(depto));
    }

    @PatchMapping("/cargar")
    public ResponseEntity<String> cargarDatos(){
        ArrayList<Depto> listaDeptos = new ArrayList<>();
        ArrayList<Emp> listaEmp = new ArrayList<>();

        listaEmp.add(new Emp("SMITH", "CONTABLE", LocalDate.of(1980, 12, 17), new BigDecimal(800)));
        listaEmp.add(new Emp("ALLEN", "COMERCIAL", LocalDate.of(1981, 2, 20), new BigDecimal(1600), new BigDecimal(300)));
        listaEmp.add(new Emp("WARD", "COMERCIAL", LocalDate.of(1981, 2, 22), new BigDecimal(1250), new BigDecimal(500)));
        listaEmp.add(new Emp("JONES", "MANAGER", LocalDate.of(1981, 4, 2), new BigDecimal(2975)));
        listaEmp.add(new Emp("MARTIN", "COMERCIAL", LocalDate.of(1981, 9, 28), new BigDecimal(1250), new BigDecimal(1400)));
        listaEmp.add(new Emp("BLAKE", "MANAGER", LocalDate.of(1981, 5, 1), new BigDecimal(2850)));
        listaEmp.add(new Emp("CLARK", "MANAGER", LocalDate.of(1981, 6, 9), new BigDecimal(2450)));
        listaEmp.add(new Emp("SCOTT", "ANALISTA", LocalDate.of(1982, 12, 9), new BigDecimal(3000)));
        listaEmp.add(new Emp("KING", "PRESIDENTE", LocalDate.of(1981, 11, 17), new BigDecimal(5000)));
        listaEmp.add(new Emp("TURNER", "COMERCIAL", LocalDate.of(1981, 9, 8), new BigDecimal(1500), new BigDecimal(0)));
        listaEmp.add(new Emp("ADAMS", "CONTABLE", LocalDate.of(1983, 1, 12), new BigDecimal(1100)));
        listaEmp.add(new Emp("JAMES", "CONTABLE", LocalDate.of(1981, 12, 3), new BigDecimal(950)));
        listaEmp.add(new Emp("FORD", "ANALISTA", LocalDate.of(1981, 12, 3), new BigDecimal(3000)));
        listaEmp.add(new Emp("MILLER", "CONTABLE", LocalDate.of(1982, 1, 23), new BigDecimal(1300)));

        for (Emp emp: listaEmp)
            empService.guardar(emp);

        listaDeptos.add(new Depto("CONTABILIDAD", "SANTIAGO"));
        listaDeptos.add(new Depto("ADMINISTRACION", "VIGO"));
        listaDeptos.add(new Depto("VENTAS", "PONTEVEDRA"));
        listaDeptos.add(new Depto("OPERACIONES", "VILAGARCIA"));

        listaDeptos.get(0).agregarefe(listaEmp.get(6));
        deptoService.guardar(listaDeptos.get(0));
        listaDeptos.get(1).agregarefe(listaEmp.get(0));
        deptoService.guardar(listaDeptos.get(1));
        listaDeptos.get(2).agregarefe(listaEmp.get(1));
        deptoService.guardar(listaDeptos.get(2));
        listaDeptos.get(3).agregarefe(listaEmp.get(13));
        deptoService.guardar(listaDeptos.get(3));

        listaDeptos.get(0).agregarEmpleado(listaEmp.get(6));
        listaDeptos.get(0).agregarEmpleado(listaEmp.get(8));
        listaDeptos.get(0).agregarEmpleado(listaEmp.get(13));

        listaDeptos.get(1).agregarEmpleado(listaEmp.get(1));
        listaDeptos.get(1).agregarEmpleado(listaEmp.get(3));
        listaDeptos.get(1).addEmpleado(listaEmp.get(7));
        listaDeptos.get(1).agregarEmpleado(listaEmp.get(10));
        listaDeptos.get(1).agregarEmpleado(listaEmp.get(12));

        listaDeptos.get(2).agregarEmpleado(listaEmp.get(1));
        listaDeptos.get(2).agregarEmpleado(listaEmp.get(2));
        listaDeptos.get(2).agregarEmpleado(listaEmp.get(4));
        listaDeptos.get(2).agregarEmpleado(listaEmp.get(5));
        listaDeptos.get(2).agregarEmpleado(listaEmp.get(9));
        listaDeptos.get(2).agregarEmpleado(listaEmp.get(11));

        for (Depto depto : listaDeptos)
            deptoService.guardar(depto);

        return ResponseEntity.ok("Datos cargados correctamente");
    }
}
