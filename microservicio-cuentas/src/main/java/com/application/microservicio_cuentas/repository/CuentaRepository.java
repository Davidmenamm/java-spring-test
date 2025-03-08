package com.application.microservicio_cuentas.repository;

import com.application.microservicio_cuentas.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio para Cuenta
public interface CuentaRepository extends JpaRepository<Cuenta, String> {}