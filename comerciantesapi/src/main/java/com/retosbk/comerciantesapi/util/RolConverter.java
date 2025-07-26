package com.retosbk.comerciantesapi.util;

import com.retosbk.comerciantesapi.entity.Rol;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RolConverter implements AttributeConverter<Rol, String> {
    @Override
    public String convertToDatabaseColumn(Rol rol) {
        switch (rol) {
            case ADMINISTRADOR: return "Administrador";
            case AUXILIAR_REGISTRO: return "Auxiliar";
            // más roles...
            default: throw new IllegalArgumentException("Rol desconocido: " + rol);
        }
    }

    @Override
    public Rol convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "Administrador": return Rol.ADMINISTRADOR;
            case "Auxiliar": return Rol.AUXILIAR_REGISTRO;
            // más roles...
            default: throw new IllegalArgumentException("Rol desconocido en BD: " + dbData);
        }
    }
}
