-- 1. Ingresar los roles
INSERT INTO roles (nombre, descripcion) VALUES 
('Administrador', 'Acceso total al sistema y gestión de equipos'),
('Empleado', 'Acceso limitado para consulta de activos asignados');

-- 2. Agregar los puestos
INSERT INTO puestos (puesto) VALUES 
('Jefe de informática'),
('Secretaria'),
('Contador');

-- 3. Agregar un empleado para cada puesto
-- Nota: Asumimos que los IDs de puestos son 1, 2 y 3 respectivamente según el orden de inserción arriba.
INSERT INTO empleados (nombre, apellido, telefono, puesto_id, fecha_nacimiento) VALUES 
('Roberto', 'García', '5555-0101', 1, '1985-05-20'), -- Jefe de informática
('Lucía', 'Méndez', '5555-0202', 2, '1992-08-15'),   -- Secretaria
('Carlos', 'Sánchez', '5555-0303', 3, '1988-11-30'); -- Contador

-- 4. Ingresar los 3 usuarios con sus respectivos permisos
-- Jefe de informática (Empleado 1) -> Administrador (Rol 1)
-- Secretaria (Empleado 2) -> Empleado (Rol 2)
-- Contador (Empleado 3) -> Empleado (Rol 2)
INSERT INTO usuarios (usuario, email, password, estado, rol_id, empleado_id) VALUES 
('rgarcia_admin', 'roberto@empresa.com', 'pass_admin_123', 1, 1, 1),
('lmendez_user', 'lucia@empresa.com', 'pass_secretaria_456', 1, 2, 2),
('csanchez_user', 'carlos@empresa.com', 'pass_contador_789', 1, 2, 3);


SELECT 
    CONCAT(e.nombre, ' ', e.apellido) AS 'Nombre completo del empleado',
    p.puesto AS 'Puesto',
    u.email AS 'Email',
    r.nombre AS 'Nombre del rol',
    CASE 
        WHEN u.estado = 1 THEN 'Activo' 
        ELSE 'Inactivo' 
    END AS 'Estado'
FROM usuarios u
JOIN empleados e ON u.empleado_id = e.empleado_id
JOIN puestos p ON e.puesto_id = p.puesto_id
JOIN roles r ON u.rol_id = r.rol_id;


CREATE VIEW vw_usuarios AS
SELECT 
    CONCAT(e.nombre, ' ', e.apellido) AS 'Nombre completo del empleado',
    p.puesto AS 'Puesto',
    u.email AS 'Email',
    r.nombre AS 'Nombre del rol',
    CASE 
        WHEN u.estado = 1 THEN 'Activo' 
        ELSE 'Inactivo' 
    END AS 'Estado'
FROM usuarios u
JOIN empleados e ON u.empleado_id = e.empleado_id
JOIN puestos p ON e.puesto_id = p.puesto_id
JOIN roles r ON u.rol_id = r.rol_id;

SELECT * FROM vw_usuarios;


UPDATE empleados 
SET 
    telefono = '22334455', 
    fecha_nacimiento = '2000-01-01'
WHERE empleado_id = 3;

SELECT nombre, apellido, telefono, fecha_nacimiento 
FROM empleados 
WHERE empleado_id = 3;

-- 1. Primero eliminamos al usuario asociado para evitar errores de restricción
DELETE FROM usuarios 
WHERE empleado_id = 3;

-- 2. Ahora eliminamos al empleado con los criterios específicos solicitados
DELETE FROM empleados 
WHERE empleado_id = 3 
AND fecha_nacimiento = '2000-01-01';


SELECT * FROM empleados