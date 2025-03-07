package com.application.microservicio_cuentas.service;

import com.application.microservicio_cuentas.exception.SaldoNoDisponibleException;
import com.application.microservicio_cuentas.model.Cuenta;
import com.application.microservicio_cuentas.model.Movimiento;
import com.application.microservicio_cuentas.repository.CuentaRepository;
import com.application.microservicio_cuentas.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private CuentaRepository cuentaRepository;

    @Transactional
    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getNumeroCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
        if (nuevoSaldo < 0) {
            throw new SaldoNoDisponibleException("Saldo no disponible");
        }
        cuenta.setSaldoInicial(nuevoSaldo);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setFecha(new Date());
        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> getReporte(String numeroCuenta, Date fechaInicio, Date fechaFin) {
        return movimientoRepository.findByNumeroCuentaAndFechaBetween(numeroCuenta, fechaInicio, fechaFin);
    }
}