DECLARE
  cur pkg_comerciantes.comerciante_cursor;
  v_nombre comerciantes.nombre_razon_social%TYPE;
  v_municipio comerciantes.municipio%TYPE;
  v_telefono comerciantes.telefono%TYPE;
  v_correo comerciantes.correo_electronico%TYPE;
  v_fecha comerciantes.fecha_registro%TYPE;
  v_estado comerciantes.estado%TYPE;
  v_establecimientos NUMBER;
  v_ingresos NUMBER;
  v_empleados NUMBER;
BEGIN
  cur := pkg_comerciantes.reporte_comerciantes_activos;
  
  LOOP
    FETCH cur INTO v_nombre, v_municipio, v_telefono, v_correo, v_fecha, v_estado,
                  v_establecimientos, v_ingresos, v_empleados;
    EXIT WHEN cur%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(
      '[' || v_nombre || '] ' || v_municipio || ' | Tel: ' || v_telefono ||
      ' | Estabs: ' || v_establecimientos || ' | Ingresos: ' || v_ingresos ||
      ' | Empleados: ' || v_empleados
    );
  END LOOP;

  CLOSE cur;
END;
