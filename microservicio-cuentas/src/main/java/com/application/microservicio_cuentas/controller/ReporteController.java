package com.application.microservicio_cuentas.controller;

import com.application.microservicio_cuentas.model.Cuenta;
import com.application.microservicio_cuentas.model.Movimiento;
import com.application.microservicio_cuentas.service.CuentaService;
import com.application.microservicio_cuentas.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReporteController {
    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private MovimientoService movimientoService;

    // Generar reporte de movimientos por cliente y fechas
    @GetMapping("/reportes")
    public Map<String, Object> generarReporte(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaFin,
            @RequestParam("cliente") String clienteIdentificacion) {
        List<Cuenta> cuentas = cuentaService.getAllCuentas().stream()
                .filter(c -> c.getClienteIdentificacion().equals(clienteIdentificacion))
                .toList();
        Map<String, Object> reporte = new HashMap<>();
        cuentas.forEach(cuenta -> {
            List<Movimiento> movimientos = movimientoService.getReporte(
                    cuenta.getNumeroCuenta(), fechaInicio, fechaFin);
            Map<String, Object> cuentaData = new HashMap<>();
            cuentaData.put("saldo", cuenta.getSaldoInicial());
            cuentaData.put("movimientos", movimientos);
            reporte.put(cuenta.getNumeroCuenta(), cuentaData);
        });
        return reporte;
    }
}