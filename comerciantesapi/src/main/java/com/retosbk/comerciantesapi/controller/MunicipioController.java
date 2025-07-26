package com.retosbk.comerciantesapi.controller;

import com.retosbk.comerciantesapi.dto.MunicipioDTO;
import com.retosbk.comerciantesapi.entity.Municipio;
import com.retosbk.comerciantesapi.service.MunicipioService;
import com.retosbk.comerciantesapi.util.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/municipios")
@Tag(name = "Municipios", description = "Gestión de municipios")
@SecurityRequirement(name = "bearerAuth")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    @GetMapping
    public ResponseEntity<?> obtenerMunicipios() {
        System.out.println("MunicipiosController");
        List<MunicipioDTO> municipios = municipioService.obtenerTodos();
        return ResponseEntity.ok(new ApiResponse<>(true, "Consulta exitosa", municipios));
    }
}
