package com.application.microservicio_cuentas.repository;

import com.application.microservicio_cuentas.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByNumeroCuentaAndFechaBetween(String numeroCuenta, Date fechaInicio, Date fechaFin);
}