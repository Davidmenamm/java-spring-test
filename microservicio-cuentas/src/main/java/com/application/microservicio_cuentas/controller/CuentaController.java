package com.application.microservicio_cuentas.controller;

import com.application.microservicio_cuentas.model.Cuenta;
import com.application.microservicio_cuentas.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    // Obtener todas las cuentas
    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    // Crear una nueva cuenta
    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.saveCuenta(cuenta);
    }

    // Actualizar cuenta por número de cuenta
    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable String numeroCuenta, @RequestBody Cuenta cuenta) {
        Cuenta updatedCuenta = cuentaService.updateCuenta(numeroCuenta, cuenta);
        return ResponseEntity.ok(updatedCuenta);
    }

    // Eliminar cuenta por número de cuenta
    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable String numeroCuenta) {
        cuentaService.deleteCuenta(numeroCuenta);
        return ResponseEntity.noContent().build();
    }
}