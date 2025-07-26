-- Reto 3: Inserción de datos de prueba en las tablas

-- 1. Insertar Usuarios
INSERT INTO usuarios (nombre, correo_electronico, contrasena, rol)
VALUES ('Admin Principal', 'admin@demo.com', 'admin123', 'Administrador');
INSERT INTO usuarios (nombre, correo_electronico, contrasena, rol)
VALUES ('Usuario Auxiliar', 'auxiliar@demo.com', 'aux123', 'Auxiliar');

-- 2. Insertar Comerciantes
INSERT INTO comerciantes (nombre_razon_social, municipio, telefono, correo_electronico, estado)
VALUES ('Comercial El Sol', 'Bogotá', '3001234567', 'sol@correo.com', 'Activo');
INSERT INTO comerciantes (nombre_razon_social, municipio, telefono, correo_electronico, estado)
VALUES ('Mercantil Andes', 'Medellín', '3102223344', 'andes@correo.com', 'Activo');
INSERT INTO comerciantes (nombre_razon_social, municipio, telefono, correo_electronico, estado)
VALUES ('Distribuciones Pacífico', 'Cali', NULL, NULL, 'Inactivo');
INSERT INTO comerciantes (nombre_razon_social, municipio, telefono, correo_electronico, estado)
VALUES ('Alianza Norte', 'Barranquilla', '3019988776', 'norte@correo.com', 'Activo');
INSERT INTO comerciantes (nombre_razon_social, municipio, telefono, correo_electronico, estado)
VALUES ('Ferretería Omega', 'Cartagena', NULL, NULL, 'Activo');

-- 3. Insertar Establecimientos 
-- (Distribuidos entre comerciantes con IDs del 1 al 5, asumidos según las inserciones anteriores)
INSERT INTO establecimientos (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Sucursal A', 1500000.00, 10, 1);
INSERT INTO establecimientos (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Sucursal B', 2200000.00, 7, 1);
INSERT INTO establecimientos (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Sucursal C', 1850000.50, 4, 2);
INSERT INTO establecimientos (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Sucursal D', 950000.00, 2, 3);
INSERT INTO establecimientos (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Sucursal E', 1740000.00, 5, 2);
INSERT INTO establecimientos (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Sucursal F', 890000.00, 3, 4);
INSERT INTO establecimientos (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Sucursal G', 1050000.00, 6, 4);
INSERT INTO establecimientos (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Sucursal H', 430000.00, 1, 5);
INSERT INTO establecimientos (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Sucursal I', 1290000.00, 4, 5);
INSERT INTO establecimientos (nombre, ingresos, numero_empleados, id_comerciante)
VALUES ('Sucursal J', 780000.00, 2, 3);

-- 4. Confirmar los cambios en la base de datos
COMMIT;





-- RETO 6: Datos de Municipios
-- Insertar municipios base
INSERT INTO municipios (nombre, codigo_dane) VALUES ('Bogotá', '11001');
INSERT INTO municipios (nombre, codigo_dane) VALUES ('Medellín', '05001');
INSERT INTO municipios (nombre, codigo_dane) VALUES ('Cali', '76001');
INSERT INTO municipios (nombre, codigo_dane) VALUES ('Barranquilla', '08001');
INSERT INTO municipios (nombre, codigo_dane) VALUES ('Cartagena', '13001');
-- Algunos municipios adicionales
INSERT INTO municipios (nombre, codigo_dane) VALUES ('Bucaramanga', '68001');
INSERT INTO municipios (nombre, codigo_dane) VALUES ('Santa Marta', '47001');
INSERT INTO municipios (nombre, codigo_dane) VALUES ('Pereira', '66001');
INSERT INTO municipios (nombre, codigo_dane) VALUES ('Manizales', '17001');
INSERT INTO municipios (nombre, codigo_dane) VALUES ('Villavicencio', '50001');

COMMIT;
