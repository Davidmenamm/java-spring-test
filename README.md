# Microservicios Banco

Este proyecto implementa un sistema bancario basado en microservicios utilizando **Java Spring Boot**. Actualmente incluye el **Microservicio Cuentas**, que administra cuentas bancarias y movimientos, con soporte para pruebas unitarias, de integración y pruebas de API mediante Karate DSL.

## Tecnologías Utilizadas

- **Java 17 con Spring Boot 3.4.3**: Framework para el desarrollo del microservicio.
- **Spring Data JPA**: Para la persistencia de datos con MySQL y H2 (en pruebas).
- **RabbitMQ**: Comunicación asíncrona entre microservicios.
- **MySQL**: Base de datos relacional para producción.
- **H2**: Base de datos en memoria para pruebas.
- **Docker**: Contenerización de la aplicación.
- **Maven**: Gestión de dependencias y construcción.
- **JUnit 5 y Mockito**: Pruebas unitarias e integración.
- **Karate DSL**: Pruebas automatizadas de API.
- **Postman**: Pruebas manuales de API (opcional).

## Estructura del Proyecto

- **Microservicio Cuentas**: Gestiona cuentas (`Cuenta`) y movimientos (`Movimiento`).
  - **Modelos**: `Cuenta` y `Movimiento` con anotaciones JPA.
  - **Repositorios**: `CuentaRepository` y `MovimientoRepository` para operaciones CRUD.
  - **Servicios**: `CuentaService` y `MovimientoService` con lógica de negocio.
  - **Controladores**: `CuentaController`, `MovimientoController` y `ReporteController` para endpoints REST.
  - **Excepciones**: Manejo de errores como `SaldoNoDisponibleException`.
  - **Pruebas**: Unitarias (`CuentaServiceUnitTest`) e integración (`MovimientoServiceIntegrationTest`).

## Requisitos

- **Docker** y **Docker Compose** (para despliegue).
- **Maven** (para construir y ejecutar pruebas localmente).
- **Git** (opcional, para clonar el repositorio).
- **Postman** (opcional, para pruebas manuales).

## Despliegue

1. **Clonar el repositorio**:
   ```bash
   git clone <URL_REPOSITORIO>
   cd microservicio-cuentas

## Construir y ejecutar

**Con Docker:**
\`\`\`bash
docker-compose up --build
\`\`\`
Accede al microservicio en: [http://localhost:8081/api/cuentas](http://localhost:8081/api/cuentas).
RabbitMQ: [http://localhost:15672](http://localhost:15672) (usuario: guest, contraseña: guest).

**Ejecutar localmente sin Docker** (requiere MySQL y RabbitMQ locales):
\`\`\`bash
./mvnw spring-boot:run
\`\`\`

## Endpoints (Microservicio Cuentas - [http://localhost:8081](http://localhost:8081))

**Cuentas:**
- \`GET /api/cuentas\`: Lista todas las cuentas.
- \`POST /api/cuentas\`: Crea una nueva cuenta.
- \`PUT /api/cuentas/{numeroCuenta}\`: Actualiza una cuenta por número.
- \`DELETE /api/cuentas/{numeroCuenta}\`: Elimina una cuenta.

**Movimientos:**
- \`GET /api/movimientos\`: Lista todos los movimientos.
- \`POST /api/movimientos\`: Registra un movimiento (depósito/retiro).
- \`GET /api/movimientos/{id}\`: Obtiene un movimiento por ID.
- \`PUT /api/movimientos/{id}\`: Actualiza un movimiento.
- \`DELETE /api/movimientos/{id}\`: Elimina un movimiento.

**Reportes:**
- \`GET /api/reportes?fechaInicio=dd/MM/yyyy&fechaFin=dd/MM/yyyy&cliente=<identificacion>\`: Genera un reporte de movimientos por cliente y rango de fechas.

## Comunicación Asíncrona

Usa RabbitMQ para enviar eventos de creación de cuentas a la cola \`cuenta-events\`.
Configurado en \`application.properties\` con reintentos habilitados.

## Base de Datos

**Producción:** MySQL (gestionado por Docker Compose).
**Pruebas:** H2 en memoria (perfil \`test\`).

## Entidades:

**Cuenta:** Número de cuenta, tipo, saldo inicial, estado, identificación del cliente.
**Movimiento:** ID, fecha, tipo, valor, saldo, número de cuenta.

## Pruebas

**Pruebas Unitarias e Integración**
Ubicadas en \`src/test/java/com/application/microservicio_cuentas/service\`.
- **Unitarias:** \`CuentaServiceUnitTest\` usa Mockito para probar \`saveCuenta\`.
- **Integración:** \`MovimientoServiceIntegrationTest\` verifica el registro de movimientos y actualización de saldos con H2.
**Ejecutar:**
\`\`\`bash
./mvnw test
\`\`\`

**Pruebas de API con Karate DSL**
Integradas en el directorio de pruebas (asumiendo que están en \`src/test/java\` o similar).
Automatizan el flujo de creación de cuentas, movimientos y reportes.
Ejecutadas automáticamente con \`./mvnw test\`.

**Pruebas Manuales con Postman (Opcional)**
Usa \`postman.json\` (asumiendo que lo proporcionarás) para pruebas manuales si no usas Karate DSL.
**Pasos:**
1. Importa \`postman.json\` en Postman.
2. Configura variables:
    - \`cuenta_url\`: [http://localhost:8081](http://localhost:8081)
3. Ejecuta las solicitudes en orden:
    - Crear cuentas.
    - Registrar movimientos.
    - Generar reportes.
4. Verifica las respuestas con \`GET /api/cuentas\` y \`GET /api/movimientos\`.

## Configuración

- \`application.properties\`: Configura MySQL, RabbitMQ y HikariCP.
- \`application-test.properties\`: Configura H2 para pruebas.
- Personaliza puertos o credenciales si es necesario.

## Construcción de Imagen Docker

\`Dockerfile\` multi-etapa:
- Etapa 1: Compila con JDK 17.
- Etapa 2: Ejecuta con JRE 17.
**Ejecutar:**
\`\`\`bash
docker build -t microservicio-cuentas .
\`\`\`

## Solución de Problemas

- Verifica contenedores: \`docker ps\`.
- Puertos requeridos: 8081 (app), 3306 (MySQL), 5672/15672 (RabbitMQ).
- Logs: \`docker logs <container_id>\`.