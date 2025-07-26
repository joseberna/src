package com.retosbk.comerciantesapi.service.impl;

import com.retosbk.comerciantesapi.dto.ComercianteDTO;
import com.retosbk.comerciantesapi.entity.Comerciante;
import com.retosbk.comerciantesapi.exception.NotFoundException;
import com.retosbk.comerciantesapi.repository.ComercianteRepository;
import com.retosbk.comerciantesapi.service.ComercianteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComercianteServiceImpl implements ComercianteService {

    private final ComercianteRepository comercianteRepository;

    private ComercianteDTO toDTO(Comerciante entity) {
        if (entity == null) return null;
        String usuarioActualizacion = entity.getUsuarioActualizacion();
        if (usuarioActualizacion != null && usuarioActualizacion.length() > 100) {
            usuarioActualizacion = usuarioActualizacion.substring(0, 80);
        }
        System.out.println("ComercianteServieImnpl.toDTO "+ usuarioActualizacion);
        return ComercianteDTO.builder()
                .idComerciante(entity.getIdComerciante())
                .nombreRazonSocial(entity.getNombreRazonSocial())
                .municipio(entity.getMunicipio())
                .telefono(entity.getTelefono())
                .correoElectronico(entity.getCorreoElectronico())
                .estado(entity.getEstado())
                .fechaRegistro(entity.getFechaRegistro())
                .fechaActualizacion(entity.getFechaActualizacion())
                .usuarioActualizacion(usuarioActualizacion)
                .build();
    }

    private Comerciante toEntity(ComercianteDTO dto) {
        if (dto == null) return null;
        return Comerciante.builder()
                .idComerciante(dto.getIdComerciante())
                .nombreRazonSocial(dto.getNombreRazonSocial())
                .municipio(dto.getMunicipio())
                .telefono(dto.getTelefono())
                .correoElectronico(dto.getCorreoElectronico())
                .estado(dto.getEstado())
                .fechaRegistro(dto.getFechaRegistro())
                .fechaActualizacion(dto.getFechaActualizacion())
                .usuarioActualizacion(dto.getUsuarioActualizacion())
                .build();
    }

    @Override
    public Page<ComercianteDTO> consultarPaginado(String filtro, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idComerciante").descending());
        Page<Comerciante> entities = comercianteRepository
                .findByEstadoAndNombreRazonSocialContainingIgnoreCaseOrEstadoAndMunicipioContainingIgnoreCase(
                        "Activo", filtro, "Activo", filtro, pageable
                );
        return entities.map(this::toDTO);
    }


    @Override
    public ComercianteDTO consultarPorId(Long id) {
        Comerciante entity = comercianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comerciante no encontrado"));
        return toDTO(entity);
    }

    @Override
    @Transactional
    public ComercianteDTO crear(ComercianteDTO dto, String usuarioActualizacion) {
        Comerciante entity = toEntity(dto);
        entity.setFechaRegistro(LocalDate.now());
        entity.setEstado("Activo");
        if (usuarioActualizacion != null && usuarioActualizacion.length() > 100) {
            usuarioActualizacion = usuarioActualizacion.substring(0, 100);
        }
        entity.setUsuarioActualizacion(usuarioActualizacion);
        entity.setFechaActualizacion(LocalDate.now());
        Comerciante saved = comercianteRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    @Transactional
    public ComercianteDTO actualizar(Long id, ComercianteDTO dto, String usuarioActualizacion) {
        Comerciante entity = comercianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comerciante no encontrado"));
        entity.setNombreRazonSocial(dto.getNombreRazonSocial());
        entity.setMunicipio(dto.getMunicipio());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreoElectronico(dto.getCorreoElectronico());
        entity.setFechaActualizacion(LocalDate.now());
        if (usuarioActualizacion != null && usuarioActualizacion.length() > 100) {
            usuarioActualizacion = usuarioActualizacion.substring(0, 100);
        }
        entity.setUsuarioActualizacion(usuarioActualizacion);
        return toDTO(comercianteRepository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Long id, String usuarioActualizacion) {
        Comerciante entity = comercianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comerciante no encontrado"));
        entity.setEstado("Inactivo");
        entity.setFechaActualizacion(LocalDate.now());
        if (usuarioActualizacion != null && usuarioActualizacion.length() > 100) {
            usuarioActualizacion = usuarioActualizacion.substring(0, 100);
        }
        entity.setUsuarioActualizacion(usuarioActualizacion);
        comercianteRepository.save(entity);
    }


    @Override
    @Transactional
    public ComercianteDTO modificarEstado(Long id, String nuevoEstado, String usuarioActualizacion) {
        Comerciante entity = comercianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comerciante no encontrado"));
        entity.setEstado(nuevoEstado);
        entity.setFechaActualizacion(LocalDate.now());
        if (usuarioActualizacion != null && usuarioActualizacion.length() > 100) {
            usuarioActualizacion = usuarioActualizacion.substring(0, 100);
        }
        entity.setUsuarioActualizacion(usuarioActualizacion);
        return toDTO(comercianteRepository.save(entity));
    }

    @Override
    public void generarReporteCsv(HttpServletResponse response, String usuario) {
        System.out.println("ComercianteServiceImpl.generarReporteCsv");
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=reporte_comerciantes.csv");

        try (var writer = response.getWriter()) {
            // Cabecera del archivo CSV
            writer.write("Nombre|Municipio|Teléfono|Correo Electrónico|Fecha de Registro|Estado|Cantidad de Establecimientos|Total Ingresos|Cantidad de Empleados\n");

            List<Comerciante> comerciantes = comercianteRepository.findByEstado("Activo");
            for (Comerciante c : comerciantes) {
                int establecimientos = c.getEstablecimientos() != null ? c.getEstablecimientos().size() : 0;
                double totalIngresos = c.getEstablecimientos() != null ?
                        c.getEstablecimientos().stream().mapToDouble(e -> e.getIngresos() != null ? e.getIngresos() : 0).sum() : 0;
                int empleados = c.getEstablecimientos() != null ?
                        c.getEstablecimientos().stream().mapToInt(e -> e.getNumeroEmpleados() != null ? e.getNumeroEmpleados() : 0).sum() : 0;

                writer.write(String.format("%s|%s|%s|%s|%s|%s|%d|%.2f|%d\n",
                        c.getNombreRazonSocial(),
                        c.getMunicipio(),
                        c.getTelefono(),
                        c.getCorreoElectronico(),
                        c.getFechaRegistro(),
                        c.getEstado(),
                        establecimientos,
                        totalIngresos,
                        empleados
                ));
            }
            System.out.println("ComercianteServiceImpl.generarReporteCsv writer: "+writer);
        } catch (IOException e) {
            throw new RuntimeException("Error al generar el archivo CSV", e);
        }
    }
}
