package com.application.microservicio_cuentas.service;

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

    // Registrar movimiento y actualizar saldo
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
        movimiento.setFecha(new Date()); // Fecha actual
        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }

    // Obtener todos los movimientos
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    // Obtener movimiento por ID
    public Movimiento getMovimientoById(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
    }

    // Actualizar movimiento por ID
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

    // Eliminar movimiento por ID
    @Transactional
    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }

    // Obtener reporte de movimientos por cuenta y rango de fechas
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