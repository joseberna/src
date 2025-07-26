package com.retosbk.comerciantesapi.mapper;

import com.retosbk.comerciantesapi.dto.ComercianteDTO;
import com.retosbk.comerciantesapi.entity.Comerciante;

public class ComercianteMapper {
    public static ComercianteDTO toDTO(Comerciante entity) {
        return ComercianteDTO.builder()
                .idComerciante(entity.getIdComerciante())
                .nombreRazonSocial(entity.getNombreRazonSocial())
                .municipio(entity.getMunicipio())
                .telefono(entity.getTelefono())
                .correoElectronico(entity.getCorreoElectronico())
                .estado(entity.getEstado())
                .fechaRegistro(entity.getFechaRegistro())
                .fechaActualizacion(entity.getFechaActualizacion())
                .usuarioActualizacion(entity.getUsuarioActualizacion())
                .build();
    }

    public static Comerciante toEntity(ComercianteDTO dto) {
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
}
