package com.retosbk.comerciantesapi.entity;

import com.retosbk.comerciantesapi.util.RolConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 150)
    private String correoElectronico;

    @Column(nullable = false, length = 100)
    private String contrasena;

    @Convert(converter = RolConverter.class)
    @Column(nullable = false, length = 30)
    private Rol rol;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + this.rol);
    }
}
