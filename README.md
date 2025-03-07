# Microservicios Banco

## Requisitos
- Docker
- Docker Compose

## Despliegue
1. Clonar el repositorio:
   ```bash
   git clone <URL_REPOSITORIO>
   cd <NOMBRE_REPOSITORIO>

    Construir y ejecutar los contenedores:
    bash

    docker-compose up --build
    Acceder a los servicios:
        Microservicio Clientes: http://localhost:8080/api/clientes
        Microservicio Cuentas: http://localhost:8081/api/cuentas
        RabbitMQ Management: http://localhost:15672 (usuario: guest, contraseña: guest)

Endpoints

    Clientes: CRUD en /api/clientes
    Cuentas: CRUD en /api/cuentas
    Movimientos: CRUD en /api/movimientos
    Reportes: GET /api/reportes?fecha-rango=dd/MM/yyyy-dd/MM/yyyy&cliente=<identificacion>