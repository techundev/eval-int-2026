-- Tablas Independientes
-- -----------------------------------------------------

CREATE TABLE marcas (
    marca_id INT PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(50) NOT NULL
);

CREATE TABLE tipo_equipo (
    tipo_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE puestos (
    puesto_id INT PRIMARY KEY AUTO_INCREMENT,
    puesto VARCHAR(50) NOT NULL
);

CREATE TABLE roles (
    rol_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT
);

-- Tablas con Dependencias
-- -----------------------------------------------------

CREATE TABLE empleados (
    empleado_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    puesto_id INT,
    fecha_nacimiento DATE,
    FOREIGN KEY (puesto_id) REFERENCES puestos(puesto_id)
);

-- Tablas con Dependencias
-- -----------------------------------------------------

CREATE TABLE usuarios (
    usuario_id INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    estado TINYINT(1) DEFAULT 1,
    rol_id INT,
    empleado_id INT,
    FOREIGN KEY (rol_id) REFERENCES roles(rol_id),
    FOREIGN KEY (empleado_id) REFERENCES empleados(empleado_id)
);

CREATE TABLE equipos (
    equipo_id INT PRIMARY KEY AUTO_INCREMENT,
    no_serie VARCHAR(100) UNIQUE,
    marca_id INT,
    descripcion TEXT,
    fecha_compra DATE,
    precio DECIMAL(10, 2),
    tipo_equipo INT,
    empleado_id INT,
    FOREIGN KEY (marca_id) REFERENCES marcas(marca_id),
    FOREIGN KEY (tipo_equipo) REFERENCES tipo_equipo(tipo_id),
    FOREIGN KEY (empleado_id) REFERENCES empleados(empleado_id)
);