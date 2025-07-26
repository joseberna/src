-- Reto 2: Configuración de usuario de desarrollo y triggers de auditoría

-- 1. Crear nuevo usuario de desarrollo (ejecutar con un usuario con privilegios DBA)
CREATE USER dev_user IDENTIFIED BY dev_pass
    DEFAULT TABLESPACE users
    TEMPORARY TABLESPACE temp
    QUOTA UNLIMITED ON users;

-- 2. Asignar privilegios necesarios al nuevo usuario
GRANT CONNECT, CREATE SESSION, CREATE TABLE, CREATE TRIGGER TO dev_user;
GRANT RESOURCE TO dev_user;  -- (Opcional: RESOURCE incluye privilegios de creación básicos, aunque es un rol tradicional/deprecated)

-- 3. Triggers de auditoría para actualizar automáticamente fecha y usuario en cambios

-- Trigger de auditoría en COMERCIANTES: antes de INSERTAR o ACTUALIZAR
CREATE OR REPLACE TRIGGER trg_comerciante_audit
BEFORE INSERT OR UPDATE ON comerciantes
FOR EACH ROW
BEGIN
    -- Al insertar o actualizar un comerciante, registramos fecha y usuario que hace el cambio
    :new.fecha_actualizacion := SYSDATE;
    :new.usuario_actualizacion := USER;
END;
/
-- (Este trigger asigna SYSDATE y USER a cada nuevo registro o cambio en 'comerciantes')

-- Trigger de auditoría en ESTABLECIMIENTOS: antes de INSERTAR o ACTUALIZAR
CREATE OR REPLACE TRIGGER trg_establecimiento_audit
BEFORE INSERT OR UPDATE ON establecimientos
FOR EACH ROW
BEGIN
    -- Al insertar o actualizar un establecimiento, registramos fecha y usuario del cambio
    :new.fecha_actualizacion := SYSDATE;
    :new.usuario_actualizacion := USER;
END;
/
-- (Ambos triggers de auditoría evitan el uso explícito de esos campos en las sentencias DML del usuario final)
