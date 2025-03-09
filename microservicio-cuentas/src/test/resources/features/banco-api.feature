Feature: Banco API - Ejemplo Completo

  Background:
    * header Content-Type = 'application/json'
    * def clientesUrl = 'http://localhost:8080'
    * def cuentasUrl = 'http://localhost:8081'
    * def movimientosUrl = 'http://localhost:8081'
    * def reportesUrl = 'http://localhost:8081'

  # 1. Clientes
  Scenario: 1.1 Crear José Lema
    Given url clientesUrl + '/api/clientes'
    And request { "identificacion": "123456789", "nombre": "José Lema", "genero": "M", "edad": 35, "direccion": "Otavalo sn y principal", "telefono": "098254785", "clienteId": "CL001", "contrasena": "1234", "estado": true }
    When method POST
    Then status 200

  Scenario: 1.2 Crear Marianela Montalvo
    Given url clientesUrl + '/api/clientes'
    And request { "identificacion": "987654321", "nombre": "Marianela Montalvo", "genero": "F", "edad": 28, "direccion": "Amazonas y NNUU", "telefono": "097548965", "clienteId": "CL002", "contrasena": "5678", "estado": true }
    When method POST
    Then status 200

  Scenario: 1.3 Crear Juan Osorio
    Given url clientesUrl + '/api/clientes'
    And request { "identificacion": "456789123", "nombre": "Juan Osorio", "genero": "M", "edad": 40, "direccion": "13 junio y Equinoccial", "telefono": "098874587", "clienteId": "CL003", "contrasena": "1245", "estado": true }
    When method POST
    Then status 200

  Scenario: 1.4 Verificar todos los clientes
    Given url clientesUrl + '/api/clientes'
    When method GET
    Then status 200

  # 2. Cuentas
  Scenario: 2.1 Cuenta José Lema (Ahorros)
    Given url cuentasUrl + '/api/cuentas'
    And request { "numeroCuenta": "478758", "tipoCuenta": "Ahorros", "saldoInicial": 2000.0, "estado": true, "clienteIdentificacion": "123456789" }
    When method POST
    Then status 200

  Scenario: 2.2 Cuenta Marianela (Corriente)
    Given url cuentasUrl + '/api/cuentas'
    And request { "numeroCuenta": "225487", "tipoCuenta": "Corriente", "saldoInicial": 700.0, "estado": true, "clienteIdentificacion": "987654321" }
    When method POST
    Then status 200

  Scenario: 2.3 Cuenta Juan Osorio (Ahorros)
    Given url cuentasUrl + '/api/cuentas'
    And request { "numeroCuenta": "498778", "tipoCuenta": "Ahorros", "saldoInicial": 0.0, "estado": true, "clienteIdentificacion": "456789123" }
    When method POST
    Then status 200

  Scenario: 2.4 Verificar todas las cuentas
    Given url cuentasUrl + '/api/cuentas'
    When method GET
    Then status 200

  # 3. Movimientos
  Scenario: 3.1 Depósito José Lema
    Given url movimientosUrl + '/api/movimientos'
    And request { "tipoMovimiento": "Depósito", "valor": 1000.0, "numeroCuenta": "478758" }
    When method POST
    Then status 200

  Scenario: 3.2 Retiro José Lema
    Given url movimientosUrl + '/api/movimientos'
    And request { "tipoMovimiento": "Retiro", "valor": -200.0, "numeroCuenta": "478758" }
    When method POST
    Then status 200

  Scenario: 3.3 Depósito Marianela
    Given url movimientosUrl + '/api/movimientos'
    And request { "tipoMovimiento": "Depósito", "valor": 600.0, "numeroCuenta": "225487" }
    When method POST
    Then status 200

  Scenario: 3.4 Retiro Marianela
    Given url movimientosUrl + '/api/movimientos'
    And request { "tipoMovimiento": "Retiro", "valor": -300.0, "numeroCuenta": "225487" }
    When method POST
    Then status 200

  Scenario: 3.5 Depósito Juan Osorio
    Given url movimientosUrl + '/api/movimientos'
    And request { "tipoMovimiento": "Depósito", "valor": 500.0, "numeroCuenta": "498778" }
    When method POST
    Then status 200

  Scenario: 3.6 Segundo Depósito Juan
    Given url movimientosUrl + '/api/movimientos'
    And request { "tipoMovimiento": "Depósito", "valor": 100.0, "numeroCuenta": "498778" }
    When method POST
    Then status 200

  Scenario: 3.7 Verificar todos los movimientos
    Given url movimientosUrl + '/api/movimientos'
    When method GET
    Then status 200

  # 4. Reportes
  Scenario: 4.1 Reporte José Lema
    Given url reportesUrl + '/api/reportes'
    And param fechaInicio = '01/03/2025'
    And param fechaFin = '31/03/2025'
    And param cliente = '123456789'
    When method GET
    Then status 200

  Scenario: 4.2 Reporte Marianela
    Given url reportesUrl + '/api/reportes'
    And param fechaInicio = '01/03/2025'
    And param fechaFin = '31/03/2025'
    And param cliente = '987654321'
    When method GET
    Then status 200

  Scenario: 4.3 Reporte Juan Osorio
    Given url reportesUrl + '/api/reportes'
    And param fechaInicio = '01/03/2025'
    And param fechaFin = '31/03/2025'
    And param cliente = '456789123'
    When method GET
    Then status 200