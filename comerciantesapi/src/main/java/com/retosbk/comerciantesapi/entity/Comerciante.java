package com.retosbk.comerciantesapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import com.retosbk.comerciantesapi.entity.Establecimiento;


@Entity
@Table(name = "comerciantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comerciante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comerciante")
    private Long idComerciante;


    @Column(nullable = false, length = 150)
    private String nombreRazonSocial;

    @Column(nullable = false, length = 100)
    private String municipio;

    @Column(length = 20)
    private String telefono;

    @Column(length = 150)
    private String correoElectronico;

    @Column(nullable = false)
    private LocalDate fechaRegistro = LocalDate.now();

    @Column(nullable = false, length = 10)
    private String estado;

    private LocalDate fechaActualizacion;

    @Column(length = 100)
    private String usuarioActualizacion;

    @OneToMany(mappedBy = "comerciante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Establecimiento> establecimientos;


}














