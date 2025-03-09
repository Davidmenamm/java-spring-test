CREATE DATABASE IF NOT EXISTS banco;
USE banco;

-- Tabla persona
CREATE TABLE persona (
    identificacion VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100),
    genero VARCHAR(10),
    edad INT,
    direccion VARCHAR(200),
    telefono VARCHAR(20)
);

-- Tabla cliente
CREATE TABLE cliente (
    identificacion VARCHAR(20) PRIMARY KEY,
    cliente_id VARCHAR(20),
    contrasena VARCHAR(50),
    estado BOOLEAN,
    FOREIGN KEY (identificacion) REFERENCES persona(identificacion)
);

-- Tabla cuenta
CREATE TABLE cuenta (
    numero_cuenta VARCHAR(20) PRIMARY KEY,
    tipo_cuenta VARCHAR(20),
    saldo_inicial DOUBLE,
    estado BOOLEAN,
    cliente_identificacion VARCHAR(20),
    FOREIGN KEY (cliente_identificacion) REFERENCES persona(identificacion)
);

-- Tabla movimiento
CREATE TABLE movimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME,
    tipo_movimiento VARCHAR(20),
    valor DOUBLE,
    saldo DOUBLE,
    numero_cuenta VARCHAR(20),
    FOREIGN KEY (numero_cuenta) REFERENCES cuenta(numero_cuenta)
);