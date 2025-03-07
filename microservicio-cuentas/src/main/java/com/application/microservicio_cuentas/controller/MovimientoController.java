package com.application.microservicio_cuentas.controller;

import com.application.microservicio_cuentas.model.Movimiento;
import com.application.microservicio_cuentas.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public Movimiento registrarMovimiento(@RequestBody Movimiento movimiento) {
        return movimientoService.registrarMovimiento(movimiento);
    }

    @GetMapping("/reportes")
    public List<Movimiento> getReporte(
            @RequestParam("numeroCuenta") String numeroCuenta,
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaFin) {
        return movimientoService.getReporte(numeroCuenta, fechaInicio, fechaFin);
    }
}