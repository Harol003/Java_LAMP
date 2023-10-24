-- Crear la base de datos ITSolutions
CREATE DATABASE ITSolutions;

-- Usar la base de datos
USE ITSolutions;

-- Crear tabla para clientes
CREATE TABLE Clientes (
    ClienteID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Edad INT,
    EstadoCivil VARCHAR(50)
);

-- Comentarios
COMMENT ON DATABASE ITSolutions IS 'Base de datos para ITSolutions, tienda de partes de computadoras';
COMMENT ON TABLE Clientes IS 'Tabla para almacenar datos de clientes';

-- Crear tabla para inventario de partes de computadoras
CREATE TABLE Inventario (
    ParteID INT AUTO_INCREMENT PRIMARY KEY,
    Parte VARCHAR(255) NOT NULL,
    Precio DECIMAL(10, 2),
    CantidadStock INT,
    EstadoGarantia BOOLEAN
);

-- Comentarios
COMMENT ON TABLE Inventario IS 'Tabla para almacenar datos del inventario de partes de computadoras';

-- Crear tabla para empleados
CREATE TABLE Empleados (
    EmpleadoID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    CodigoEmpleado VARCHAR(10) NOT NULL,
    Area VARCHAR(50),
    Sede VARCHAR(50)
);

-- Comentarios
COMMENT ON TABLE Empleados IS 'Tabla para almacenar datos de empleados';

-- Crear tabla para proveedores
CREATE TABLE Proveedores (
    Nit VARCHAR(15) PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Telefono VARCHAR(15),
    Correo VARCHAR(255),
    DireccionFisica VARCHAR(255)
);

-- Comentarios
COMMENT ON TABLE Proveedores IS 'Tabla para almacenar datos de proveedores';
