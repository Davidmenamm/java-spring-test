package com.application.microservicio_cuentas.service;

import com.application.microservicio_cuentas.model.Cuenta;
import com.application.microservicio_cuentas.model.Movimiento;
import com.application.microservicio_cuentas.repository.CuentaRepository;
import com.application.microservicio_cuentas.repository.MovimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class MovimientoServiceIntegrationTest {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        // Limpiar la base de datos antes de cada prueba
        movimientoRepository.deleteAll();
        cuentaRepository.deleteAll();

        // Crear una cuenta inicial
        cuenta = new Cuenta();
        cuenta.setNumeroCuenta("478758");
        cuenta.setTipoCuenta("Ahorros");
        cuenta.setSaldoInicial(2000.0);
        cuenta.setEstado(true);
        cuenta.setClienteIdentificacion("123456789");
        cuentaRepository.save(cuenta);
    }

    @Test
    void testRegistrarMovimiento_DepositoActualizaSaldo() {
        // Crear un movimiento de depósito
        Movimiento movimiento = new Movimiento();
        movimiento.setNumeroCuenta("478758");
        movimiento.setTipoMovimiento("Depósito");
        movimiento.setValor(500.0);

        // Registrar el movimiento
        Movimiento resultado = movimientoService.registrarMovimiento(movimiento);

        // Verificar que el movimiento se guardó
        assertNotNull(resultado.getId());
        assertEquals("Depósito", resultado.getTipoMovimiento());
        assertEquals(500.0, resultado.getValor());
        assertEquals(2500.0, resultado.getSaldo()); // 2000 + 500

        // Verificar que el saldo de la cuenta se actualizó
        Cuenta cuentaActualizada = cuentaRepository.findById("478758").orElseThrow();
        assertEquals(2500.0, cuentaActualizada.getSaldoInicial());
    }
}