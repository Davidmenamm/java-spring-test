# Guía Completa de Operaciones con Docker, Curl y API

## Paso 1: Iniciar los servicios

### 1.1. Eliminar volúmenes previos (empezar desde cero)
docker-compose down -v

### 1.2. Iniciar Docker Compose
docker-compose up --build

Esperar a que los servicios estén listos (MySQL y RabbitMQ deben pasar sus healthchecks).

------------------------------------------------------------

## Paso 2: Crear clientes con curl

### Cliente 1: José Lema
curl -X POST http://localhost:8080/api/clientes \
-H "Content-Type: application/json" \
-d '{"identificacion":"123456789","nombre":"José Lema","genero":"M","edad":35,"direccion":"Otavalo sn y principal","telefono":"098254785","clienteId":"CL001","contrasena":"1234","estado":true}'

Respuesta esperada:
{
    "identificacion": "123456789",
    "nombre": "José Lema",
    "genero": "M",
    "edad": 35,
    "direccion": "Otavalo sn y principal",
    "telefono": "098254785",
    "clienteId": "CL001",
    "contrasena": "1234",
    "estado": true
}

### Cliente 2: Marianela Montalvo
curl -X POST http://localhost:8080/api/clientes \
-H "Content-Type: application/json" \
-d '{"identificacion":"987654321","nombre":"Marianela Montalvo","genero":"F","edad":28,"direccion":"Amazonas y NNUU","telefono":"097548965","clienteId":"CL002","contrasena":"5678","estado":true}'

Respuesta esperada:
{
    "identificacion": "987654321",
    "nombre": "Marianela Montalvo",
    "genero": "F",
    "edad": 28,
    "direccion": "Amazonas y NNUU",
    "telefono": "097548965",
    "clienteId": "CL002",
    "contrasena": "5678",
    "estado": true
}

### Cliente 3: Juan Osorio
curl -X POST http://localhost:8080/api/clientes \
-H "Content-Type: application/json" \
-d '{"identificacion":"456789123","nombre":"Juan Osorio","genero":"M","edad":40,"direccion":"13 junio y Equinoccial","telefono":"098874587","clienteId":"CL003","contrasena":"1245","estado":true}'

Respuesta esperada:
{
    "identificacion": "456789123",
    "nombre": "Juan Osorio",
    "genero": "M",
    "edad": 40,
    "direccion": "13 junio y Equinoccial",
    "telefono": "098874587",
    "clienteId": "CL003",
    "contrasena": "1245",
    "estado": true
}

### Verificar todos los clientes
curl http://localhost:8080/api/clientes

Respuesta esperada:
[
    {
        "identificacion": "123456789",
        "nombre": "José Lema",
        "genero": "M",
        "edad": 35,
        "direccion": "Otavalo sn y principal",
        "telefono": "098254785",
        "clienteId": "CL001",
        "contrasena": "1234",
        "estado": true
    },
    {
        "identificacion": "987654321",
        "nombre": "Marianela Montalvo",
        "genero": "F",
        "edad": 28,
        "direccion": "Amazonas y NNUU",
        "telefono": "097548965",
        "clienteId": "CL002",
        "contrasena": "5678",
        "estado": true
    },
    {
        "identificacion": "456789123",
        "nombre": "Juan Osorio",
        "genero": "M",
        "edad": 40,
        "direccion": "13 junio y Equinoccial",
        "telefono": "098874587",
        "clienteId": "CL003",
        "contrasena": "1245",
        "estado": true
    }
]

------------------------------------------------------------

## Paso 3: Crear cuentas con curl

### Cuenta 1: José Lema (Ahorros)
curl -X POST http://localhost:8081/api/cuentas \
-H "Content-Type: application/json" \
-d '{"numeroCuenta":"478758","tipoCuenta":"Ahorros","saldoInicial":2000.0,"estado":true,"clienteIdentificacion":"123456789"}'

Respuesta esperada:
{
    "numeroCuenta": "478758",
    "tipoCuenta": "Ahorros",
    "saldoInicial": 2000.0,
    "estado": true,
    "clienteIdentificacion": "123456789"
}

### Cuenta 2: Marianela Montalvo (Corriente)
curl -X POST http://localhost:8081/api/cuentas \
-H "Content-Type: application/json" \
-d '{"numeroCuenta":"225487","tipoCuenta":"Corriente","saldoInicial":700.0,"estado":true,"clienteIdentificacion":"987654321"}'

Respuesta esperada:
{
    "numeroCuenta": "225487",
    "tipoCuenta": "Corriente",
    "saldoInicial": 700.0,
    "estado": true,
    "clienteIdentificacion": "987654321"
}

### Cuenta 3: Juan Osorio (Ahorros)
curl -X POST http://localhost:8081/api/cuentas \
-H "Content-Type: application/json" \
-d '{"numeroCuenta":"498778","tipoCuenta":"Ahorros","saldoInicial":0.0,"estado":true,"clienteIdentificacion":"456789123"}'

Respuesta esperada:
{
    "numeroCuenta": "498778",
    "tipoCuenta": "Ahorros",
    "saldoInicial": 0.0,
    "estado": true,
    "clienteIdentificacion": "456789123"
}

### Verificar todas las cuentas
curl http://localhost:8081/api/cuentas

Respuesta esperada:
[
    {
        "numeroCuenta": "478758",
        "tipoCuenta": "Ahorros",
        "saldoInicial": 2000.0,
        "estado": true,
        "clienteIdentificacion": "123456789"
    },
    {
        "numeroCuenta": "225487",
        "tipoCuenta": "Corriente",
        "saldoInicial": 700.0,
        "estado": true,
        "clienteIdentificacion": "987654321"
    },
    {
        "numeroCuenta": "498778",
        "tipoCuenta": "Ahorros",
        "saldoInicial": 0.0,
        "estado": true,
        "clienteIdentificacion": "456789123"
    }
]

------------------------------------------------------------

## Paso 4: Registrar movimientos con curl

### Movimientos para José Lema (Cuenta 478758)

#### Depósito de 1000
curl -X POST http://localhost:8081/api/movimientos \
-H "Content-Type: application/json" \
-d '{"tipoMovimiento":"Depósito","valor":1000.0,"numeroCuenta":"478758"}'

Respuesta:
{
    "id": 1,
    "fecha": "2025-03-07T<time>",
    "tipoMovimiento": "Depósito",
    "valor": 1000.0,
    "saldo": 3000.0,
    "numeroCuenta": "478758"
}

#### Retiro de 200
curl -X POST http://localhost:8081/api/movimientos \
-H "Content-Type: application/json" \
-d '{"tipoMovimiento":"Retiro","valor":-200.0,"numeroCuenta":"478758"}'

Respuesta:
{
    "id": 2,
    "fecha": "2025-03-07T<time>",
    "tipoMovimiento": "Retiro",
    "valor": -200.0,
    "saldo": 2800.0,
    "numeroCuenta": "478758"
}

### Movimientos para Marianela Montalvo (Cuenta 225487)

#### Depósito de 600
curl -X POST http://localhost:8081/api/movimientos \
-H "Content-Type: application/json" \
-d '{"tipoMovimiento":"Depósito","valor":600.0,"numeroCuenta":"225487"}'

Respuesta:
{
    "id": 3,
    "fecha": "2025-03-07T<time>",
    "tipoMovimiento": "Depósito",
    "valor": 600.0,
    "saldo": 1300.0,
    "numeroCuenta": "225487"
}

#### Retiro de 300
curl -X POST http://localhost:8081/api/movimientos \
-H "Content-Type: application/json" \
-d '{"tipoMovimiento":"Retiro","valor":-300.0,"numeroCuenta":"225487"}'

Respuesta:
{
    "id": 4,
    "fecha": "2025-03-07T<time>",
    "tipoMovimiento": "Retiro",
    "valor": -300.0,
    "saldo": 1000.0,
    "numeroCuenta": "225487"
}

### Movimientos para Juan Osorio (Cuenta 498778)

#### Depósito de 500
curl -X POST http://localhost:8081/api/movimientos \
-H "Content-Type: application/json" \
-d '{"tipoMovimiento":"Depósito","valor":500.0,"numeroCuenta":"498778"}'

Respuesta:
{
    "id": 5,
    "fecha": "2025-03-07T<time>",
    "tipoMovimiento": "Depósito",
    "valor": 500.0,
    "saldo": 500.0,
    "numeroCuenta": "498778"
}

#### Depósito de 100
curl -X POST http://localhost:8081/api/movimientos \
-H "Content-Type: application/json" \
-d '{"tipoMovimiento":"Depósito","valor":100.0,"numeroCuenta":"498778"}'

Respuesta:
{
    "id": 6,
    "fecha": "2025-03-07T<time>",
    "tipoMovimiento": "Depósito",
    "valor": 100.0,
    "saldo": 600.0,
    "numeroCuenta": "498778"
}

### Verificar todos los movimientos
curl http://localhost:8081/api/movimientos

Respuesta esperada:
[
    {
        "id": 1,
        "fecha": "2025-03-07T<time>",
        "tipoMovimiento": "Depósito",
        "valor": 1000.0,
        "saldo": 3000.0,
        "numeroCuenta": "478758"
    },
    {
        "id": 2,
        "fecha": "2025-03-07T<time>",
        "tipoMovimiento": "Retiro",
        "valor": -200.0,
        "saldo": 2800.0,
        "numeroCuenta": "478758"
    },
    {
        "id": 3,
        "fecha": "2025-03-07T<time>",
        "tipoMovimiento": "Depósito",
        "valor": 600.0,
        "saldo": 1300.0,
        "numeroCuenta": "225487"
    },
    {
        "id": 4,
        "fecha": "2025-03-07T<time>",
        "tipoMovimiento": "Retiro",
        "valor": -300.0,
        "saldo": 1000.0,
        "numeroCuenta": "225487"
    },
    {
        "id": 5,
        "fecha": "2025-03-07T<time>",
        "tipoMovimiento": "Depósito",
        "valor": 500.0,
        "saldo": 500.0,
        "numeroCuenta": "498778"
    },
    {
        "id": 6,
        "fecha": "2025-03-07T<time>",
        "tipoMovimiento": "Depósito",
        "valor": 100.0,
        "saldo": 600.0,
        "numeroCuenta": "498778"
    }
]

------------------------------------------------------------

## Paso 5: Generar reportes con curl

### Reporte para José Lema
curl "http://localhost:8081/api/reportes?fechaInicio=01/03/2025&fechaFin=31/03/2025&cliente=123456789"

Respuesta esperada:
{
    "478758": {
        "saldo": 2800.0,
        "movimientos": [
            {
                "id": 1,
                "fecha": "2025-03-07T<time>",
                "tipoMovimiento": "Depósito",
                "valor": 1000.0,
                "saldo": 3000.0,
                "numeroCuenta": "478758"
            },
            {
                "id": 2,
                "fecha": "2025-03-07T<time>",
                "tipoMovimiento": "Retiro",
                "valor": -200.0,
                "saldo": 2800.0,
                "numeroCuenta": "478758"
            }
        ]
    }
}

### Reporte para Marianela Montalvo
curl "http://localhost:8081/api/reportes?fechaInicio=01/03/2025&fechaFin=31/03/2025&cliente=987654321"

Respuesta esperada:
{
    "225487": {
        "saldo": 1000.0,
        "movimientos": [
            {
                "id": 3,
                "fecha": "2025-03-07T<time>",
                "tipoMovimiento": "Depósito",
                "valor": 600.0,
                "saldo": 1300.0,
                "numeroCuenta": "225487"
            },
            {
                "id": 4,
                "fecha": "2025-03-07T<time>",
                "tipoMovimiento": "Retiro",
                "valor": -300.0,
                "saldo": 1000.0,
                "numeroCuenta": "225487"
            }
        ]
    }
}

### Reporte para Juan Osorio
curl "http://localhost:8081/api/reportes?fechaInicio=01/03/2025&fechaFin=31/03/2025&cliente=456789123"

Respuesta esperada:
{
    "498778": {
        "saldo": 600.0,
        "movimientos": [
            {
                "id": 5,
                "fecha": "2025-03-07T<time>",
                "tipoMovimiento": "Depósito",
                "valor": 500.0,
                "saldo": 500.0,
                "numeroCuenta": "498778"
            },
            {
                "id": 6,
                "fecha": "2025-03-07T<time>",
                "tipoMovimiento": "Depósito",
                "valor": 100.0,
                "saldo": 600.0,
                "numeroCuenta": "498778"
            }
        ]
    }
}

------------------------------------------------------------

## Verificación Final

### Clientes
curl http://localhost:8080/api/clientes
Deberías ver los 3 clientes creados.

### Cuentas
curl http://localhost:8081/api/cuentas
Deberías ver las 3 cuentas con saldos actualizados (2800.0, 1000.0, 600.0).

### Movimientos
curl http://localhost:8081/api/movimientos
Deberías ver los 6 movimientos registrados.
