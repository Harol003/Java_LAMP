-- Crear la base de datos
CREATE DATABASE MiBaseDeDatos;

-- Usar la base de datos
USE MiBaseDeDatos;

-- Crear la tabla para almacenar los datos
CREATE TABLE Personas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    edad INT,
    estado_civil VARCHAR(50)
);

-- Comentarios
COMMENT ON DATABASE MiBaseDeDatos IS 'Base de datos para almacenar datos personales';
COMMENT ON TABLE Personas IS 'Tabla para almacenar información de personas';
COMMENT ON COLUMN Personas.id IS 'Identificador único de la persona';
COMMENT ON COLUMN Personas.nombre IS 'Nombre de la persona';
COMMENT ON COLUMN Personas.edad IS 'Edad de la persona';
COMMENT ON COLUMN Personas.estado_civil IS 'Estado civil de la persona';