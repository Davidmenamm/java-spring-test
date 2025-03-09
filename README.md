# Microservicios Banco (Resumen)

Este proyecto implementa un sistema bancario con microservicios Java Spring Boot, centrado en el **Microservicio Cuentas** (gestión de cuentas y movimientos) con pruebas unitarias, integración y API (Karate DSL).

## Demo Aplicacion (video en la carpeta mediaexamples)
![Demo Video](media-examples/demo.gif)

## Tecnologías

- Java 17, Spring Boot 3.4.3
- Spring Data JPA (MySQL, H2)
- RabbitMQ
- Docker, Maven
- JUnit 5, Mockito, Karate DSL
- Postman (opcional)

## Estructura Proyecto (Microservicio Cuentas)

- **Modelos:** `Cuenta`, `Movimiento` (JPA)
- **Repositorios:** `CuentaRepository`, `MovimientoRepository` (CRUD)
- **Servicios:** `CuentaService`, `MovimientoService` (Lógica de negocio)
- **Controladores:** `CuentaController`, `MovimientoController`, `ReporteController` (REST endpoints)
- **Excepciones:** Manejo de errores (ej. `SaldoNoDisponibleException`)
- **Pruebas:** Unitarias, Integración

## Requisitos

- Docker, Docker Compose
- Maven
- Git (opcional)
- Postman (opcional)

## Despliegue y Ejecución

1. **Clonar:** `git clone <URL_REPOSITORIO> ; cd microservicio-cuentas`
2. **Docker:** `docker-compose up --build` (http://localhost:8081/api/cuentas, RabbitMQ: http://localhost:15672)
3. **Local (sin Docker):** `./mvnw spring-boot:run` (requiere MySQL, RabbitMQ locales)

## Endpoints (Cuentas - http://localhost:8081)

- **Cuentas:**
    - `GET /api/cuentas`: Listar
    - `POST /api/cuentas`: Crear
    - `PUT /api/cuentas/{numeroCuenta}`: Actualizar
    - `DELETE /api/cuentas/{numeroCuenta}`: Eliminar
- **Movimientos:**
    - `GET /api/movimientos`: Listar
    - `POST /api/movimientos`: Registrar (depósito/retiro)
    - `GET /api/movimientos/{id}`: Obtener por ID
    - `PUT /api/movimientos/{id}`: Actualizar
    - `DELETE /api/movimientos/{id}`: Eliminar
- **Reportes:**
    - `GET /api/reportes?fechaInicio=dd/MM/yyyy&fechaFin=dd/MM/yyyy&cliente=<identificacion>`: Reporte por cliente y fechas

## Comunicación Asíncrona

RabbitMQ para eventos de creación de cuentas (cola `cuenta-events`).

## Base de Datos

- **Producción:** MySQL (Docker Compose)
- **Pruebas:** H2 (memoria, perfil `test`)

## Entidades

- **Cuenta:** numeroCuenta, tipo, saldoInicial, estado, identificacionCliente
- **Movimiento:** id, fecha, tipo, valor, saldo, numeroCuenta

## Pruebas

- **Unitarias/Integración:** `src/test/java/com/application/microservicio_cuentas/service` (`CuentaServiceUnitTest`, `MovimientoServiceIntegrationTest`). Ejecutar: `./mvnw test`
- **API (Karate DSL):**  Automatización de flujos (creación cuenta, movimientos, reportes). Ejecutar: `./mvnw test`
- **Manuales (Postman - Opcional):** Importar `postman.json`, configurar variables (ej. `cuenta_url=http://localhost:8081`), ejecutar solicitudes.

## Configuración

- `application.properties`: MySQL, RabbitMQ, HikariCP
- `application-test.properties`: H2 (pruebas)
- Personalizar puertos/credenciales si necesario.

## Docker

- `Dockerfile` multi-etapa (compilación JDK 17, ejecución JRE 17).
- Construir: `docker build -t microservicio-cuentas .`

## Solución Problemas

- Verificar contenedores: `docker ps`
- Puertos: 8081, 3306, 5672/15672
- Logs: `docker logs <container_id>`