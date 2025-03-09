package com.application.microservicio_cuentas.service;

import com.application.microservicio_cuentas.exception.CuentaNoEncontradaException;
import com.application.microservicio_cuentas.model.Cuenta;
import com.application.microservicio_cuentas.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;
    private final StreamBridge streamBridge;

    @Autowired
    public CuentaService(CuentaRepository cuentaRepository, StreamBridge streamBridge) {
        this.cuentaRepository = cuentaRepository;
        this.streamBridge = streamBridge;
    }

    // Obtiene todas las cuentas registradas.
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    // Guarda una nueva cuenta y envía un evento a RabbitMQ.
    public Cuenta saveCuenta(Cuenta cuenta) {
        if (cuenta.getSaldoInicial() < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        Cuenta savedCuenta = cuentaRepository.save(cuenta);
        streamBridge.send("output", "Cuenta creada: " + savedCuenta.getNumeroCuenta());
        return savedCuenta;
    }

    // Actualiza una cuenta existente.
    public Cuenta updateCuenta(String numeroCuenta, Cuenta cuenta) {
        Cuenta existingCuenta = cuentaRepository.findById(numeroCuenta)
                .orElseThrow(() -> new CuentaNoEncontradaException(numeroCuenta));
        if (cuenta.getSaldoInicial() < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        existingCuenta.setTipoCuenta(cuenta.getTipoCuenta());
        existingCuenta.setSaldoInicial(cuenta.getSaldoInicial());
        existingCuenta.setEstado(cuenta.isEstado());
        existingCuenta.setClienteIdentificacion(cuenta.getClienteIdentificacion());
        return cuentaRepository.save(existingCuenta);
    }

    // Elimina una cuenta por su número.
    public void deleteCuenta(String numeroCuenta) {
        if (!cuentaRepository.existsById(numeroCuenta)) {
            throw new CuentaNoEncontradaException(numeroCuenta);
        }
        cuentaRepository.deleteById(numeroCuenta);
    }
}