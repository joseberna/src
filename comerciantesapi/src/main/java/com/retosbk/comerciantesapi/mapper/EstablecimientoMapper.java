package com.retosbk.comerciantesapi.mapper;

import com.retosbk.comerciantesapi.dto.EstablecimientoDTO;
import com.retosbk.comerciantesapi.entity.Establecimiento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstablecimientoMapper {

    EstablecimientoDTO toDTO(Establecimiento entity);

    Establecimiento toEntity(EstablecimientoDTO dto);
}
