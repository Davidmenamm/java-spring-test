package com.application.microservicio_cuentas.service;

import com.application.microservicio_cuentas.exception.CuentaNoEncontradaException;
import com.application.microservicio_cuentas.exception.MovimientoNoEncontradoException;
import com.application.microservicio_cuentas.exception.SaldoNoDisponibleException;
import com.application.microservicio_cuentas.model.Cuenta;
import com.application.microservicio_cuentas.model.Movimiento;
import com.application.microservicio_cuentas.repository.CuentaRepository;
import com.application.microservicio_cuentas.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private CuentaRepository cuentaRepository;

    // registra un movimiento y actualiza el saldo de la cuenta.
    @Transactional
    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getNumeroCuenta())
                .orElseThrow(() -> new CuentaNoEncontradaException(movimiento.getNumeroCuenta()));
        
        // Validaciones adicionales
        if ("Retiro".equals(movimiento.getTipoMovimiento()) && movimiento.getValor() > 0) {
            throw new IllegalArgumentException("El valor de un retiro debe ser negativo");
        }
        if ("Depósito".equals(movimiento.getTipoMovimiento()) && movimiento.getValor() < 0) {
            throw new IllegalArgumentException("El valor de un depósito debe ser positivo");
        }

        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
        if (nuevoSaldo < 0) {
            throw new SaldoNoDisponibleException("Saldo no disponible para la cuenta: " + movimiento.getNumeroCuenta());
        }
        
        cuenta.setSaldoInicial(nuevoSaldo);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setFecha(new Date()); // Fecha actual
        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }

    // Obtiener todos los movimientos.
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    // Obtiene un movimiento por su ID.
    public Movimiento getMovimientoById(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new MovimientoNoEncontradoException(id));
    }

    // Actualiza un movimiento existente.
    @Transactional
    public Movimiento updateMovimiento(Long id, Movimiento movimiento) {
        Movimiento existingMovimiento = getMovimientoById(id);
        existingMovimiento.setFecha(movimiento.getFecha());
        existingMovimiento.setTipoMovimiento(movimiento.getTipoMovimiento());
        existingMovimiento.setValor(movimiento.getValor());
        existingMovimiento.setSaldo(movimiento.getSaldo());
        existingMovimiento.setNumeroCuenta(movimiento.getNumeroCuenta());
        return movimientoRepository.save(existingMovimiento);
    }

    // Elimina un movimiento por su ID.
    @Transactional
    public void deleteMovimiento(Long id) {
        if (!movimientoRepository.existsById(id)) {
            throw new MovimientoNoEncontradoException(id);
        }
        movimientoRepository.deleteById(id);
    }

    // Genera un reporte de movimientos por cuenta y rango de fechas.
    public List<Movimiento> getReporte(String numeroCuenta, Date fechaInicio, Date fechaFin) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaFin);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date adjustedFechaFin = cal.getTime();
        return movimientoRepository.findByNumeroCuentaAndFechaBetween(numeroCuenta, fechaInicio, adjustedFechaFin);
    }
}