package com.retosbk.comerciantesapi.service;

import com.retosbk.comerciantesapi.dto.MunicipioDTO;
import com.retosbk.comerciantesapi.entity.Municipio;
import java.util.List;

public interface MunicipioService {
    List<MunicipioDTO> obtenerTodos();
}
