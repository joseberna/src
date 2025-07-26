package com.retosbk.comerciantesapi.controller;

import com.retosbk.comerciantesapi.dto.ComercianteDTO;
import com.retosbk.comerciantesapi.service.ComercianteService;
import com.retosbk.comerciantesapi.service.JwtService;
import com.retosbk.comerciantesapi.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/comerciantes")
@RequiredArgsConstructor
@Tag(name = "Comerciantes", description = "Gestión de comerciantes")
public class ComercianteController {

    private final ComercianteService comercianteService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ComercianteDTO>>> consultarPaginado(
            @RequestParam(defaultValue = "") String filtro,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(ApiResponse.success(comercianteService.consultarPaginado(filtro, page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ComercianteDTO>> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(comercianteService.consultarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ComercianteDTO>> crear( @RequestBody @Valid ComercianteDTO dto,
                                                              HttpServletRequest request) {
        String token = extractJwtFromHeader(request);
        String usuarioActualizacion = jwtService.extractUsername(token);
        return ResponseEntity.ok(ApiResponse.success(comercianteService.crear(dto, usuarioActualizacion)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ComercianteDTO>> actualizar(@PathVariable Long id, @RequestBody @Valid ComercianteDTO dto, HttpServletRequest request) {
        String token = extractJwtFromHeader(request);
        String usuarioActualizacion = jwtService.extractUsername(token);
        return ResponseEntity.ok(ApiResponse.success(comercianteService.actualizar(id, dto, usuarioActualizacion)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id, HttpServletRequest request) {
        String token = extractJwtFromHeader(request);
        String usuarioActualizacion = jwtService.extractUsername(token);
        comercianteService.eliminar(id, usuarioActualizacion);
        return ResponseEntity.ok(ApiResponse.success("Comerciante eliminado correctamente", null));

    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ApiResponse<ComercianteDTO>> modificarEstado(
            @PathVariable Long id, @RequestParam String estado, Principal principal) {
        return ResponseEntity.ok(ApiResponse.success(comercianteService.modificarEstado(id, estado, principal.getName())));
    }

    @GetMapping("/reporte")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public void generarReporte(HttpServletResponse response, HttpServletRequest request) {
        System.out.println("ComercianteController.generarReporte writer");
        String token = extractJwtFromHeader(request);
        String usuario = jwtService.extractUsername(token);
        comercianteService.generarReporteCsv(response, usuario);
    }


    private String extractJwtFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }


}

