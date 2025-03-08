package com.application.microservicio_clientes.service;

import com.application.microservicio_clientes.model.Cliente;
import com.application.microservicio_clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Obtener todos los clientes
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    // Guardar cliente
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Actualizar cliente por identificación
    public Cliente updateCliente(String identificacion, Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(identificacion)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        existingCliente.setClienteId(cliente.getClienteId());
        existingCliente.setContrasena(cliente.getContrasena());
        existingCliente.setEstado(cliente.isEstado());
        existingCliente.setNombre(cliente.getNombre());
        existingCliente.setGenero(cliente.getGenero());
        existingCliente.setEdad(cliente.getEdad());
        existingCliente.setDireccion(cliente.getDireccion());
        existingCliente.setTelefono(cliente.getTelefono());
        return clienteRepository.save(existingCliente);
    }

    // Eliminar cliente por identificación
    public void deleteCliente(String identificacion) {
        clienteRepository.deleteById(identificacion);
    }
}