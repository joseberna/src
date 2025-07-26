package com.retosbk.comerciantesapi.service;

import com.retosbk.comerciantesapi.dto.EstablecimientoDTO;

import java.util.List;

public interface EstablecimientoService {
    EstablecimientoDTO crearEstablecimiento(Long idComerciante, EstablecimientoDTO dto);
    List<EstablecimientoDTO> obtenerTodos();
    List<EstablecimientoDTO> obtenerPorComerciante(Long idComerciante);
    EstablecimientoDTO obtenerPorId(Long id);
    EstablecimientoDTO actualizar(Long id, EstablecimientoDTO dto);
    void eliminar(Long id);
}
