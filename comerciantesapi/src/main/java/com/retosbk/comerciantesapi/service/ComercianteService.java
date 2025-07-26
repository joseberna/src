package com.retosbk.comerciantesapi.service;

import com.retosbk.comerciantesapi.dto.ComercianteDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComercianteService {
    Page<ComercianteDTO> consultarPaginado(String filtro, int page, int size);
    ComercianteDTO consultarPorId(Long id);
    ComercianteDTO crear(ComercianteDTO dto, String usuarioActualizacion);
    ComercianteDTO actualizar(Long id, ComercianteDTO dto, String usuarioActualizacion);
    void eliminar(Long id, String usuarioActualizacion);
    ComercianteDTO modificarEstado(Long id, String nuevoEstado, String usuarioActualizacion);
    void generarReporteCsv(HttpServletResponse response, String usuario);
}

