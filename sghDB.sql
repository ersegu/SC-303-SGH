


-- Se crea la DB
CREATE DATABASE SGHDB;

USE SGHDB;

-- Se crean las tablas
CREATE TABLE pacientes (
    codigo int AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    fechaNacimiento VARCHAR(20),
    direccion VARCHAR(200),
    telefono VARCHAR(20),
    correo VARCHAR(100),
    identificacion VARCHAR(50),
    historialMedico VARCHAR(255)
);

CREATE TABLE medicos (
    codigoMedico INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fechaNacimiento VARCHAR(20) NOT NULL,
    identificacion VARCHAR(100) NOT NULL,
    salario DOUBLE NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    disponibilidad VARCHAR(100) NOT NULL
);

CREATE TABLE Enfermeros (
	codigoEnfermero INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR (100) NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR (100) NOT NULL,
    salario DOUBLE NOT NULL,
    experiencia INT NOT NULL
); 

CREATE TABLE Habitaciones (
	codigoHabitacion INT AUTO_INCREMENT PRIMARY KEY,
    numeroHabitacion INT NOT NULL,
    disponible BOOLEAN NOT NULL,
    codigoPaciente INT
);

CREATE TABLE Facturas (
	codigoFactura INT AUTO_INCREMENT PRIMARY KEY,
    codigoCita INT NOT NULL,
    codigoPaciente INT NOT NULL,
    montoFactura DOUBLE NOT NULL
);

CREATE TABLE Citas (
	codigoCita INT AUTO_INCREMENT PRIMARY KEY,
    codigoMedico INT NOT NULL,
    codigoPaciente INT NOT NULL,
    fechaCita VARCHAR (50) NOT NULL,
    horaCita VARCHAR (50) NOT NULL
);

CREATE TABLE Usuarios (
	codigo INT auto_increment PRIMARY KEY,
    idUsuario varchar (100) not null unique,
    contrasenha varchar (200) not null,
    nombre varchar (100) not null,
    rol varchar (20) not null
);




