package com.retosbk.comerciantesapi.repository;

import com.retosbk.comerciantesapi.entity.Establecimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstablecimientoRepository extends JpaRepository<Establecimiento, Long> {
    List<Establecimiento> findByComercianteIdComerciante(Long id);
}
