package com.application.microservicio_cuentas.exception;

public class CuentaNoEncontradaException extends RuntimeException {
    public CuentaNoEncontradaException(String numeroCuenta) {
        super("No se encontró la cuenta con número: " + numeroCuenta);
    }
}