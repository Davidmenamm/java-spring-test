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

-- -- Insertar datos en persona
-- INSERT INTO persona (identificacion, nombre, genero, edad, direccion, telefono) VALUES
-- ('123456789', 'José Lema', 'M', 35, 'Otavalo sn y principal', '098254785'),
-- ('987654321', 'Marianela Montalvo', 'F', 28, 'Amazonas y NNUU', '097548965'),
-- ('456789123', 'Juan Osorio', 'M', 40, '13 junio y Equinoccial', '098874587');

-- -- Insertar datos en cliente
-- INSERT INTO cliente (identificacion, cliente_id, contrasena, estado) VALUES
-- ('123456789', 'CL001', '1234', TRUE),
-- ('987654321', 'CL002', '5678', TRUE),
-- ('456789123', 'CL003', '1245', TRUE);

-- -- Insertar datos en cuenta
-- INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_identificacion) VALUES
-- ('478758', 'Ahorros', 2000.0, TRUE, '123456789'),
-- ('225487', 'Corriente', 700.0, TRUE, '987654321'),
-- ('498778', 'Ahorros', 0.0, TRUE, '456789123'),
-- ('496825', 'Ahorros', 0.0, TRUE, '987654321'),
-- ('585545', 'Corriente', 1000.0, TRUE, '123456789');

-- -- Insertar datos en movimiento
-- INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, numero_cuenta) VALUES
-- ('2025-03-01 10:00:00', 'Depósito', 600.0, 700.0, '225487'),
-- ('2025-03-02 14:00:00', 'Retiro', -540.0, 0.0, '496825'),
-- ('2025-03-03 09:00:00', 'Depósito', 1000.0, 2000.0, '478758'),
-- ('2025-03-04 15:00:00', 'Retiro', -200.0, 1800.0, '478758');