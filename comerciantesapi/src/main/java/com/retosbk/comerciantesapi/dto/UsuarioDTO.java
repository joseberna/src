package com.retosbk.comerciantesapi.dto;
import lombok.Data;
@Data
public class UsuarioDTO {
    private Long idUsuario;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private String rol;
}