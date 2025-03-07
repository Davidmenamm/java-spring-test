package com.application.microservicio_clientes.repository;

import com.application.microservicio_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {}