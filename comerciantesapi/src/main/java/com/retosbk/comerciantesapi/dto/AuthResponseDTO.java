package com.retosbk.comerciantesapi.dto;

import com.retosbk.comerciantesapi.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String nombre;
    private String correoElectronico;
    private Rol rol;
}
