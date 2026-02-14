-- Ingresar los roles
INSERT INTO roles (nombre, descripcion) VALUES 
('Administrador', 'Acceso total al sistema y gestión de equipos'),
('Empleado', 'Acceso limitado para consulta de activos asignados');

-- Agregar los puestos
INSERT INTO puestos (puesto) VALUES 
('Jefe de informática'),
('Secretaria'),
('Contador');

-- Poblar tabla empleados
INSERT INTO empleados (nombre, apellido, telefono, puesto_id, fecha_nacimiento) VALUES 
('Roberto', 'García', '5555-0101', 1, '1985-05-20'), -- Jefe de informática
('Lucía', 'Méndez', '5555-0202', 2, '1992-08-15'),   -- Secretaria
('Carlos', 'Sánchez', '5555-0303', 3, '1988-11-30'); -- Contador

-- Ingresar los 3 usuarios con sus respectivos permisos
INSERT INTO usuarios (usuario, email, password, estado, rol_id, empleado_id) VALUES 
('rgarcia_admin', 'roberto@empresa.com', 'pass_admin_123', 1, 1, 1),
('lmendez_user', 'lucia@empresa.com', 'pass_secretaria_456', 1, 2, 2),
('csanchez_user', 'carlos@empresa.com', 'pass_contador_789', 1, 2, 3);

-- Obtener los datos de todos los usuarios
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

-- Crear una vista con el nombre vw_usuarios, utilizar la consulta anterior
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

-- Modificar teléfono y fecha de nacimiento del empleado con id 3
UPDATE empleados 
SET 
    telefono = '22334455', 
    fecha_nacimiento = '2000-01-01'
WHERE empleado_id = 3;

SELECT nombre, apellido, telefono, fecha_nacimiento 
FROM empleados 
WHERE empleado_id = 3;

-- Eliminar los datos del empleado con id 3 y fecha de nacimiento 01/01/2000 
DELETE FROM usuarios 
WHERE empleado_id = 3;

DELETE FROM empleados 
WHERE empleado_id = 3 
AND fecha_nacimiento = '2000-01-01';

SELECT * FROM empleados