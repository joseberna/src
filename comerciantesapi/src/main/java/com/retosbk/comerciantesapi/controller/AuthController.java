package com.retosbk.comerciantesapi.controller;

import com.retosbk.comerciantesapi.dto.AuthResponseDTO;
import com.retosbk.comerciantesapi.dto.LoginRequestDTO;
import com.retosbk.comerciantesapi.entity.Usuario;
import com.retosbk.comerciantesapi.service.JwtService;
import com.retosbk.comerciantesapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Login y generación de token JWT")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Operation(summary = "Login de usuario", description = "Autentica al usuario y retorna un JWT válido por 1 hora")
    @PostMapping("/login")
    public AuthResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        System.out.println("AuthController.login");
        Usuario usuario = usuarioService.autenticar(loginRequest.getCorreoElectronico(), loginRequest.getContrasena());
        String token = jwtService.generateToken(String.valueOf(usuario.getCorreoElectronico()));
        return new AuthResponseDTO( token,
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getRol());
    }
}
