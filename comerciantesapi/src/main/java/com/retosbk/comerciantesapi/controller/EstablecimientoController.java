package com.retosbk.comerciantesapi.controller;

import com.retosbk.comerciantesapi.dto.EstablecimientoDTO;
import com.retosbk.comerciantesapi.service.EstablecimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/establecimientos")
@Tag(name = "Establecimientos", description = "Gestión de establecimientos")
public class EstablecimientoController {

    @Autowired
    private EstablecimientoService establecimientoService;

    @Operation(summary = "Crear un establecimiento para un comerciante")
    @PostMapping("/comerciante/{idComerciante}")
    public EstablecimientoDTO crear(
            @PathVariable Long idComerciante,
            @Valid @RequestBody EstablecimientoDTO dto
    ) {
        return establecimientoService.crearEstablecimiento(idComerciante, dto);
    }

    @Operation(summary = "Obtener todos los establecimientos")
    @GetMapping
    public List<EstablecimientoDTO> obtenerTodos() {
        return establecimientoService.obtenerTodos();
    }

    @Operation(summary = "Obtener establecimientos por ID de comerciante")
    @GetMapping("/comerciante/{idComerciante}")
    public List<EstablecimientoDTO> obtenerPorComerciante(@PathVariable Long idComerciante) {
        return establecimientoService.obtenerPorComerciante(idComerciante);
    }

    @Operation(summary = "Obtener un establecimiento por su ID")
    @GetMapping("/{id}")
    public EstablecimientoDTO obtenerPorId(@PathVariable Long id) {
        return establecimientoService.obtenerPorId(id);
    }

    @Operation(summary = "Actualizar un establecimiento existente")
    @PutMapping("/{id}")
    public EstablecimientoDTO actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EstablecimientoDTO dto
    ) {
        return establecimientoService.actualizar(id, dto);
    }

    @Operation(summary = "Eliminar un establecimiento por su ID")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        establecimientoService.eliminar(id);
    }
}
