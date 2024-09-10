package com.paula.libros.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.paula.libros.dtos.Consulta4DTO;
import com.paula.libros.dtos.Consulta6DTO;
import com.paula.libros.dtos.Consulta7DTO;
import com.paula.libros.entities.Socio;
import com.paula.libros.repositories.SocioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocioService {

    private final SocioRepository socioRepository;

    public List<Consulta7DTO> consulta3() {
        List<Object[]> resu = socioRepository.consulta3();
        return resu.stream().map(obj -> new Consulta7DTO((String) obj[0], (Long) obj[1])).collect(Collectors.toList());
    }

    public List<Consulta4DTO> consulta4() {

        List<Object[]> resu = socioRepository.consulta4();
        return resu.stream().map(obj -> new Consulta4DTO((String) obj[0], (LocalDate) obj[1]))
                .collect(Collectors.toList());
    }

    public List<Consulta6DTO> consulta6() {

        List<Object[]> resu = socioRepository.consulta6();
        return resu.stream().map(obj -> new Consulta6DTO((Integer) obj[0], (String) obj[1], (Long) obj[1]))
                .collect(Collectors.toList());
    }

    public void guardar(Socio socio) {
        socioRepository.save(socio);
    }
}
