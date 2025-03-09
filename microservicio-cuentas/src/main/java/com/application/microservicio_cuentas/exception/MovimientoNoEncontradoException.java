package com.application.microservicio_cuentas.exception;

public class MovimientoNoEncontradoException extends RuntimeException {
    public MovimientoNoEncontradoException(Long id) {
        super("No se encontró el movimiento con ID: " + id);
    }
}