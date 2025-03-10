package com.application.microservicio_clientes.repository;

import com.application.microservicio_clientes.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio para Persona
public interface PersonaRepository extends JpaRepository<Persona, String> {}