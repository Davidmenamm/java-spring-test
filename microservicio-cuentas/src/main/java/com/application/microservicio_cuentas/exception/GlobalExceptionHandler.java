package com.application.microservicio_cuentas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Maneja excepciones globalmente
@ControllerAdvice
public class GlobalExceptionHandler {

    // Procesa saldo insuficiente
    @ExceptionHandler(SaldoNoDisponibleException.class)
    public ResponseEntity<String> handleSaldoNoDisponible(SaldoNoDisponibleException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Responde si no hay cuenta
    @ExceptionHandler(CuentaNoEncontradaException.class)
    public ResponseEntity<String> handleCuentaNoEncontrada(CuentaNoEncontradaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Gestiona movimientos no hallados
    @ExceptionHandler(MovimientoNoEncontradoException.class)
    public ResponseEntity<String> handleMovimientoNoEncontrado(MovimientoNoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Trata argumentos inválidos
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Responde a errores inesperados
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>("Error inesperado: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}