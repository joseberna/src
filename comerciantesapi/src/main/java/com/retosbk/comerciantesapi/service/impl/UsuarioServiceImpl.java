package com.retosbk.comerciantesapi.service.impl;

import com.retosbk.comerciantesapi.entity.Rol;
import com.retosbk.comerciantesapi.entity.Usuario;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retosbk.comerciantesapi.service.UsuarioService;
import com.retosbk.comerciantesapi.repository.UsuarioRepository;
import com.retosbk.comerciantesapi.dto.UsuarioDTO;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioDTO toDTO(Usuario u) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(u.getIdUsuario());
        dto.setNombre(u.getNombre());
        dto.setCorreoElectronico(u.getCorreoElectronico());
        dto.setContrasena(u.getContrasena());
        dto.setRol(String.valueOf(u.getRol()));
        return dto;
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setCorreoElectronico(dto.getCorreoElectronico());
        u.setContrasena(dto.getContrasena());
        u.setRol(Rol.valueOf(dto.getRol()));
        return u;
    }

    @Override
    public UsuarioDTO crear(UsuarioDTO dto) {
        return toDTO(usuarioRepository.save(toEntity(dto)));
    }

    @Override
    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        return usuarioRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        return usuarioRepository.findById(id).map(u -> {
            u.setNombre(dto.getNombre());
            u.setCorreoElectronico(dto.getCorreoElectronico());
            u.setContrasena(dto.getContrasena());
            u.setRol(Rol.valueOf(dto.getRol()));
            return toDTO(usuarioRepository.save(u));
        }).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }


    @Override
    public Usuario autenticar(String correoElectronico, String contrasena) {
        System.out.println("UsuarioServiceImpl.autenticar: "+ correoElectronico);
        System.out.println("UsuarioServiceImpl.autenticar: "+ contrasena);

        Usuario usuario = usuarioRepository.findByCorreoElectronico(correoElectronico)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Imprime el hash que se generaría con la contraseña ingresada
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String nuevoHash = encoder.encode(contrasena);
        System.out.println("Hash de la contraseña ingresada: " + nuevoHash);

        System.out.println("Hash en BD: " + usuario.getContrasena());
        if (!BCrypt.checkpw(contrasena, usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }
}

