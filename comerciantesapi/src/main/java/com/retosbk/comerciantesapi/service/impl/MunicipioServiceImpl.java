package com.retosbk.comerciantesapi.service.impl;

import com.retosbk.comerciantesapi.dto.MunicipioDTO;
import com.retosbk.comerciantesapi.entity.Municipio;
import com.retosbk.comerciantesapi.repository.MunicipioRepository;
import com.retosbk.comerciantesapi.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipioServiceImpl implements MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Override
    @Cacheable("municipios")
    public List<MunicipioDTO> obtenerTodos() {
        System.out.println("MunicipiosServiceImpl");
        return municipioRepository.findAll().stream()
                .map(m -> new MunicipioDTO(m.getId(), m.getNombre(), m.getCodigo_dane()))
                .collect(Collectors.toList());
    }
}
