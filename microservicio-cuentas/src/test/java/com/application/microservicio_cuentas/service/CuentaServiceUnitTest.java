package com.application.microservicio_cuentas.service;

import com.application.microservicio_cuentas.model.Cuenta;
import com.application.microservicio_cuentas.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.function.StreamBridge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CuentaServiceUnitTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private StreamBridge streamBridge;

    @InjectMocks
    private CuentaService cuentaService;

    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        // Configuración inicial de una cuenta para las pruebas
        cuenta = new Cuenta();
        cuenta.setNumeroCuenta("478758");
        cuenta.setTipoCuenta("Ahorros");
        cuenta.setSaldoInicial(2000.0);
        cuenta.setEstado(true);
        cuenta.setClienteIdentificacion("123456789");
    }

    @Test
    void testSaveCuenta_GuardaCuentaYEnviaMensaje() {
        // Configurar el comportamiento simulado
        when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuenta);
        when(streamBridge.send(eq("output"), anyString())).thenReturn(true);

        // Llamar al método a probar
        Cuenta result = cuentaService.saveCuenta(cuenta);

        // Assert y Verificar resultados
        assertEquals("478758", result.getNumeroCuenta());
        assertEquals("Ahorros", result.getTipoCuenta());
        assertEquals(2000.0, result.getSaldoInicial());
        assertEquals(true, result.isEstado());
        assertEquals("123456789", result.getClienteIdentificacion());

        // Verificar interacciones con los mocks
        verify(cuentaRepository, times(1)).save(cuenta);
        verify(streamBridge, times(1)).send("output", "Cuenta creada: 478758");
    }
}