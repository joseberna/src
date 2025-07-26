package com.retosbk.comerciantesapi.service;
import java.util.List;
import com.retosbk.comerciantesapi.dto.UsuarioDTO;
import com.retosbk.comerciantesapi.entity.Usuario;

public interface UsuarioService {
    UsuarioDTO crear(UsuarioDTO dto);
    List<UsuarioDTO> obtenerTodos();
    UsuarioDTO obtenerPorId(Long id);
    UsuarioDTO actualizar(Long id, UsuarioDTO dto);
    void eliminar(Long id);
    Usuario autenticar(String correoElectronico, String contrasena);
}
