package com.retosbk.comerciantesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import com.retosbk.comerciantesapi.dto.ComercianteDTO;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComercianteDTO {
    private Long idComerciante;
    private String nombreRazonSocial;
    private String municipio;
    private String telefono;
    private String correoElectronico;
    private LocalDate fechaRegistro;
    private String estado;
    private LocalDate fechaActualizacion;
    private String usuarioActualizacion;
    private List<EstablecimientoDTO> establecimientos;
}
