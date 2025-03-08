package com.application.microservicio_cuentas.service;

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

    // Obtener todas las cuentas
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    // Guardar cuenta y enviar mensaje
    public Cuenta saveCuenta(Cuenta cuenta) {
        Cuenta savedCuenta = cuentaRepository.save(cuenta);
        streamBridge.send("output", "Cuenta creada: " + savedCuenta.getNumeroCuenta());
        return savedCuenta;
    }

    // Actualizar cuenta por número de cuenta
    public Cuenta updateCuenta(String numeroCuenta, Cuenta cuenta) {
        Cuenta existingCuenta = cuentaRepository.findById(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        existingCuenta.setTipoCuenta(cuenta.getTipoCuenta());
        existingCuenta.setSaldoInicial(cuenta.getSaldoInicial());
        existingCuenta.setEstado(cuenta.isEstado());
        existingCuenta.setClienteIdentificacion(cuenta.getClienteIdentificacion());
        return cuentaRepository.save(existingCuenta);
    }

    // Eliminar cuenta por número de cuenta
    public void deleteCuenta(String numeroCuenta) {
        cuentaRepository.deleteById(numeroCuenta);
    }
}