package com.application.microservicio_clientes.controller;

import com.application.microservicio_clientes.model.Cliente;
import com.application.microservicio_clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.saveCliente(cliente);
    }

    @PutMapping("/{identificacion}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable String identificacion, @RequestBody Cliente cliente) {
        Cliente updatedCliente = clienteService.updateCliente(identificacion, cliente);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{identificacion}")
    public ResponseEntity<Void> deleteCliente(@PathVariable String identificacion) {
        clienteService.deleteCliente(identificacion);
        return ResponseEntity.noContent().build();
    }
}