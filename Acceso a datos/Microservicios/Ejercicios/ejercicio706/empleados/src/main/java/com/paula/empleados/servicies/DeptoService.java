package com.paula.empleados.servicies;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paula.empleados.entities.Depto;
import com.paula.empleados.repositories.DeptoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptoService {

    // @Autowired
    private final DeptoRepository deptoRepository;

    public void guardar(Depto depto) {
        deptoRepository.save(depto);
    }

    public Depto createDepto(Depto depto) {
        return deptoRepository.save(depto);
    }

    public Depto findById(Long id) {
        return deptoRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        deptoRepository.deleteById(id);
    }

    public Depto updateDepto(Depto depto) {
        return deptoRepository.save(depto);
    }

    public List<Depto> findAll() {
        return deptoRepository.findAll();
    }

    public List<Depto> findByNomdepIsNotAndNomdepIsNot(String nomDep1, String nomDep2) {
        return deptoRepository.findByNomdepIsNotAndNomdepIsNot(nomDep1, nomDep2);
    }

}
