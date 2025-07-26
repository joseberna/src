package com.retosbk.comerciantesapi.repository;

import com.retosbk.comerciantesapi.entity.Comerciante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComercianteRepository extends JpaRepository<Comerciante, Long> {
    Optional<Comerciante> findByCorreoElectronico(String correoElectronico);

    Page<Comerciante> findByEstadoAndNombreRazonSocialContainingIgnoreCaseOrEstadoAndMunicipioContainingIgnoreCase(
            String estado, String filtro,
            String estadoAgain, String filtroAgain,
            Pageable pageable
    );


    List<Comerciante> findByEstado(String estado);

}
