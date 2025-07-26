package com.retosbk.comerciantesapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.retosbk.comerciantesapi.entity.Comerciante;

@Entity
@Table(name = "establecimientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Establecimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstablecimiento;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column()
    private Double ingresos;

    @Column(nullable = false)
    private Integer numeroEmpleados = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comerciante", nullable = false)
    private Comerciante comerciante;

    private LocalDate fechaActualizacion;

    @Column(length = 100)
    private String usuarioActualizacion;
}










