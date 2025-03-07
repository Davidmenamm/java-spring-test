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

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.saveCuenta(cuenta);
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable String numeroCuenta, @RequestBody Cuenta cuenta) {
        Cuenta updatedCuenta = cuentaService.updateCuenta(numeroCuenta, cuenta);
        return ResponseEntity.ok(updatedCuenta);
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable String numeroCuenta) {
        cuentaService.deleteCuenta(numeroCuenta);
        return ResponseEntity.noContent().build();
    }
}