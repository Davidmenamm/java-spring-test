package com.application.microservicio_clientes.repository;

import com.application.microservicio_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio para Cliente
public interface ClienteRepository extends JpaRepository<Cliente, String> {}