package com.retosbk.comerciantesapi.service.impl;

import com.retosbk.comerciantesapi.dto.EstablecimientoDTO;
import com.retosbk.comerciantesapi.entity.Comerciante;
import com.retosbk.comerciantesapi.entity.Establecimiento;
import com.retosbk.comerciantesapi.repository.ComercianteRepository;
import com.retosbk.comerciantesapi.repository.EstablecimientoRepository;
import com.retosbk.comerciantesapi.service.EstablecimientoService;
import com.retosbk.comerciantesapi.mapper.EstablecimientoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstablecimientoServiceImpl implements EstablecimientoService {

    private final EstablecimientoMapper mapper;
    private final EstablecimientoRepository establecimientoRepository;
    private final ComercianteRepository comercianteRepository;

    @Override
    public EstablecimientoDTO crearEstablecimiento(Long idComerciante, EstablecimientoDTO dto) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante)
                .orElseThrow(() -> new IllegalArgumentException("Comerciante no encontrado con id: " + idComerciante));

        Establecimiento establecimiento = mapper.toEntity(dto);
        establecimiento.setComerciante(comerciante);

        return mapper.toDTO(establecimientoRepository.save(establecimiento));
    }

    @Override
    public List<EstablecimientoDTO> obtenerPorComerciante(Long idComerciante) {
        return establecimientoRepository.findByComercianteIdComerciante(idComerciante)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public EstablecimientoDTO obtenerPorId(Long id) {
        return establecimientoRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Establecimiento no encontrado con id: " + id));
    }

    @Override
    public List<EstablecimientoDTO> obtenerTodos() {
        return establecimientoRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public EstablecimientoDTO actualizar(Long id, EstablecimientoDTO dto) {
        Establecimiento establecimiento = establecimientoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Establecimiento no encontrado con id: " + id));

        establecimiento.setNombre(dto.getNombre());
        establecimiento.setIngresos(dto.getIngresos());
        establecimiento.setNumeroEmpleados(dto.getNumeroEmpleados());

        return mapper.toDTO(establecimientoRepository.save(establecimiento));
    }

    @Override
    public void eliminar(Long id) {
        if (!establecimientoRepository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar. Establecimiento no encontrado con id: " + id);
        }
        establecimientoRepository.deleteById(id);
    }
}
