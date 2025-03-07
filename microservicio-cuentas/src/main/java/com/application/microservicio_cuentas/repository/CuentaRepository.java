package com.application.microservicio_cuentas.repository;

import com.application.microservicio_cuentas.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {}