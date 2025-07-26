package com.retosbk.comerciantesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioDTO {
    private Long id;
    private String nombre;
    private String codigo_dane;
}
