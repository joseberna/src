-- Reto 4: Paquete para reporte de comerciantes activos
-- Este paquete define una función que retorna un cursor con el resumen de comerciantes activos y sus estadísticas.

-- Especificación (package spec)
CREATE OR REPLACE PACKAGE pkg_comerciantes AS
    TYPE comerciante_cursor IS REF CURSOR;  -- Tipo de cursor REF para resultados dinámicos

    FUNCTION reporte_comerciantes_activos
        RETURN comerciante_cursor;         -- Función que proporcionará el cursor con el reporte
END pkg_comerciantes;
/
-- (La especificación declara la función públicamente, pudiendo ser llamada desde fuera del paquete.)

-- Cuerpo del paquete (implementación)
CREATE OR REPLACE PACKAGE BODY pkg_comerciantes AS

    FUNCTION reporte_comerciantes_activos
        RETURN comerciante_cursor
    IS
        cur comerciante_cursor;  -- Cursor local que será devuelto
    BEGIN
        -- Abrir el cursor con la consulta del reporte de comerciantes activos
        OPEN cur FOR
            SELECT
                c.nombre_razon_social,
                c.municipio,
                c.telefono,
                c.correo_electronico,
                c.fecha_registro,
                c.estado,
                COUNT(e.id_establecimiento) AS cantidad_establecimientos,
                NVL(SUM(e.ingresos), 0)     AS total_ingresos,
                NVL(SUM(e.numero_empleados), 0) AS cantidad_empleados
            FROM comerciantes c
            LEFT JOIN establecimientos e 
              ON c.id_comerciante = e.id_comerciante
            WHERE c.estado = 'Activo'
            GROUP BY 
              c.nombre_razon_social, 
              c.municipio, 
              c.telefono, 
              c.correo_electronico,
              c.fecha_registro, 
              c.estado
            ORDER BY COUNT(e.id_establecimiento) DESC;  -- Ordenar comerciantes por número de establecimientos (descendente)
        RETURN cur;  -- Devolver el cursor abierto al invocador
    END reporte_comerciantes_activos;

END pkg_comerciantes;
/
-- (Al ejecutar pkg_comerciantes.reporte_comerciantes_activos, obtendremos un cursor que se puede recorrer para leer el reporte.)
