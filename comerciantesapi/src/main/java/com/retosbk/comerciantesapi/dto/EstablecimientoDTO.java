package com.retosbk.comerciantesapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EstablecimientoDTO {
    private Long idEstablecimiento;
    private String nombre;
    private Double ingresos;
    private Integer numeroEmpleados;
}
