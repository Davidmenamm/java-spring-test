package com.application.microservicio_cuentas.exception;

// Excepción para saldo no disponible
public class SaldoNoDisponibleException extends RuntimeException {
    public SaldoNoDisponibleException(String message) {
        super(message);
    }
}