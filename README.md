# Microservicios Banco

Este proyecto implementa un sistema bancario basado en microservicios utilizando Java Spring Boot. Incluye los siguientes microservicios:

* **Microservicio Clientes:** Gestiona la información de clientes y personas.
* **Microservicio Cuentas:** Administra cuentas bancarias y movimientos.

## Tecnologías Utilizadas

* **Java Spring Boot:** Framework para el desarrollo de microservicios.
* **RabbitMQ:** Para la comunicación asíncrona entre microservicios.
* **MySQL:** Base de datos relacional para el almacenamiento de datos.
* **Docker:** Para la contenerización de las aplicaciones.
* **Docker Compose:** Para la orquestación de los contenedores.

## Requisitos

* Docker
* Docker Compose
* Git (opcional)

## Despliegue

1.  Clona el repositorio:

    \`\`\`bash
    git clone <URL\_REPOSITORIO>
    cd <NOMBRE\_REPOSITORIO>
    \`\`\`

2.  Construye y ejecuta los contenedores:

    \`\`\`bash
    docker-compose up --build
    \`\`\`

3.  Accede a los servicios:

    * **Microservicio Clientes:** <http://localhost:8080/api/clientes>
    * **Microservicio Cuentas:** <http://localhost:8081/api/cuentas>
    * **RabbitMQ:** <http://localhost:15672> (usuario: `guest`, contraseña: `guest`)

## Endpoints

### Microservicio Clientes (http://localhost:8080)

* `GET /api/clientes`: Lista todos los clientes.
* `POST /api/clientes`: Crea un nuevo cliente.
* `PUT /api/clientes/{identificacion}`: Actualiza un cliente existente por su identificación.
* `DELETE /api/clientes/{identificacion}`: Elimina un cliente por su identificación.

### Microservicio Cuentas (http://localhost:8081)

* `GET /api/cuentas`: Lista todas las cuentas.
* `POST /api/cuentas`: Crea una nueva cuenta.
* `PUT /api/cuentas/{numeroCuenta}`: Actualiza una cuenta existente por su número de cuenta.
* `DELETE /api/cuentas/{numeroCuenta}`: Elimina una cuenta por su número de cuenta.
* `GET /api/movimientos`: Lista todos los movimientos.
* `POST /api/movimientos`: Registra un nuevo movimiento.
* `PUT /api/movimientos/{id}`: Actualiza un movimiento existente por su ID.
* `DELETE /api/movimientos/{id}`: Elimina un movimiento por su ID.
* `GET /api/reportes`: Genera un reporte de movimientos.

## Comunicación entre Microservicios

* Los microservicios se comunican de forma asíncrona mediante RabbitMQ.
* El Microservicio Cuentas envía mensajes a la cola `cuenta-events`.
* El Microservicio Clientes escucha la cola para actualizar las vinculaciones.

## Base de Datos

* MySQL es gestionada por Docker Compose.
* Entidades:
    * **Clientes:** Persona y Cliente.
    * **Cuentas:** Cuenta y Movimientos.

## Solución de Problemas

* Verifica los contenedores activos con `docker ps`.
* Asegúrate de que los puertos 8080, 8081, 3306 y 5672/15672 no estén en uso.
* Revisa RabbitMQ en <http://localhost:15672>.

## Notas Adicionales

* Utiliza Postman o cURL para probar los endpoints.
* Personaliza las configuraciones en `application.properties` y `docker-compose.yml`.