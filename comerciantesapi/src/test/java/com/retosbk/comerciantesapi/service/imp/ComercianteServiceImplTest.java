package com.retosbk.comerciantesapi.service.imp;

import com.retosbk.comerciantesapi.dto.ComercianteDTO;
import com.retosbk.comerciantesapi.entity.Comerciante;
import com.retosbk.comerciantesapi.exception.NotFoundException;
import com.retosbk.comerciantesapi.repository.ComercianteRepository;
import com.retosbk.comerciantesapi.service.impl.ComercianteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para ComercianteServiceImpl (CRUD de comerciantes).
 * Uso de Mockito para mockear el repository.
 */
class ComercianteServiceImplTest {

    @Mock
    private ComercianteRepository comercianteRepository;

    @InjectMocks
    private ComercianteServiceImpl comercianteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("Crear Comerciante")
    class CrearComercianteTests {
        @Test
        @DisplayName("Debería crear un comerciante correctamente")
        void testCrearComerciante() {
            ComercianteDTO dto = ComercianteDTO.builder()
                    .nombreRazonSocial("Mi Empresa S.A.S")
                    .municipio("Cali")
                    .telefono("12345")
                    .correoElectronico("empresa@email.com")
                    .build();

            Comerciante entity = Comerciante.builder()
                    .idComerciante(1L)
                    .nombreRazonSocial("Mi Empresa S.A.S")
                    .municipio("Cali")
                    .telefono("12345")
                    .correoElectronico("empresa@email.com")
                    .fechaRegistro(LocalDate.now())
                    .estado("Activo")
                    .usuarioActualizacion("admin@demo.com")
                    .fechaActualizacion(LocalDate.now())
                    .build();

            when(comercianteRepository.save(any(Comerciante.class))).thenReturn(entity);

            ComercianteDTO saved = comercianteService.crear(dto, "admin@demo.com");

            assertNotNull(saved);
            assertEquals("Mi Empresa S.A.S", saved.getNombreRazonSocial());
            assertEquals("admin@demo.com", saved.getUsuarioActualizacion());
            verify(comercianteRepository).save(any(Comerciante.class));
        }
    }

    @Nested
    @DisplayName("Consultar Comerciante por ID")
    class ConsultarPorIdTests {
        @Test
        @DisplayName("Debería retornar comerciante si existe")
        void testConsultarPorIdFound() {
            Comerciante entity = Comerciante.builder()
                    .idComerciante(1L)
                    .nombreRazonSocial("EmpresaX")
                    .municipio("Palmira")
                    .build();

            when(comercianteRepository.findById(1L)).thenReturn(Optional.of(entity));

            ComercianteDTO dto = comercianteService.consultarPorId(1L);

            assertNotNull(dto);
            assertEquals(1L, dto.getIdComerciante());
            assertEquals("EmpresaX", dto.getNombreRazonSocial());
        }

        @Test
        @DisplayName("Debería lanzar NotFoundException si NO existe")
        void testConsultarPorIdNotFound() {
            when(comercianteRepository.findById(2L)).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () -> comercianteService.consultarPorId(2L));
        }
    }

    @Nested
    @DisplayName("Actualizar Comerciante")
    class ActualizarComercianteTests {
        @Test
        @DisplayName("Debería actualizar los datos correctamente")
        void testActualizarComerciante() {
            Comerciante entity = Comerciante.builder()
                    .idComerciante(1L)
                    .nombreRazonSocial("EmpresaX")
                    .municipio("Palmira")
                    .telefono("111")
                    .correoElectronico("a@a.com")
                    .estado("Activo")
                    .fechaRegistro(LocalDate.now())
                    .build();

            when(comercianteRepository.findById(1L)).thenReturn(Optional.of(entity));
            when(comercianteRepository.save(any(Comerciante.class))).thenReturn(entity);

            ComercianteDTO dtoUpdate = ComercianteDTO.builder()
                    .nombreRazonSocial("EmpresaX MOD")
                    .municipio("Cali")
                    .telefono("222")
                    .correoElectronico("b@b.com")
                    .build();

            ComercianteDTO updated = comercianteService.actualizar(1L, dtoUpdate, "aux@demo.com");

            assertEquals("EmpresaX MOD", updated.getNombreRazonSocial());
            assertEquals("aux@demo.com", updated.getUsuarioActualizacion());
            verify(comercianteRepository).save(entity);
        }

        @Test
        @DisplayName("Debería lanzar NotFoundException si el ID no existe")
        void testActualizarComercianteNotFound() {
            when(comercianteRepository.findById(5L)).thenReturn(Optional.empty());
            ComercianteDTO dtoUpdate = ComercianteDTO.builder().nombreRazonSocial("X").build();
            assertThrows(NotFoundException.class, () -> comercianteService.actualizar(5L, dtoUpdate, "user@demo.com"));
        }
    }

    @Nested
    @DisplayName("Eliminar Comerciante (Soft Delete)")
    class EliminarComercianteTests {
        @Test
        @DisplayName("Debería cambiar el estado a Inactivo y guardar usuario")
        void testEliminarComerciante() {
            Comerciante entity = Comerciante.builder()
                    .idComerciante(1L)
                    .estado("Activo")
                    .build();

            when(comercianteRepository.findById(1L)).thenReturn(Optional.of(entity));
            when(comercianteRepository.save(any(Comerciante.class))).thenReturn(entity);

            comercianteService.eliminar(1L, "admin@demo.com");

            assertEquals("Inactivo", entity.getEstado());
            assertEquals("admin@demo.com", entity.getUsuarioActualizacion());
            verify(comercianteRepository).save(entity);
        }

        @Test
        @DisplayName("Debería lanzar NotFoundException si el ID no existe")
        void testEliminarComercianteNotFound() {
            when(comercianteRepository.findById(99L)).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () -> comercianteService.eliminar(99L, "admin@demo.com"));
        }
    }

    @Test
    @DisplayName("Debería retornar una página de comerciantes")
    void testConsultarPaginado() {
        // Arrange
        Comerciante entity = Comerciante.builder()
                .idComerciante(1L)
                .nombreRazonSocial("Empresa1")
                .municipio("Cali")
                .build();
        List<Comerciante> entities = Collections.singletonList(entity);
        Page<Comerciante> page = new PageImpl<>(entities);

        // Mock exact 4 String params + Pageable, in same order as repository
        when(comercianteRepository
                .findByEstadoAndNombreRazonSocialContainingIgnoreCaseOrEstadoAndMunicipioContainingIgnoreCase(
                        anyString(), anyString(), anyString(), anyString(), any(Pageable.class)))
                .thenReturn(page);

        // Act
        Page<ComercianteDTO> result = comercianteService.consultarPaginado("Cali", 0, 5);

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals("Empresa1", result.getContent().get(0).getNombreRazonSocial());
    }



}
