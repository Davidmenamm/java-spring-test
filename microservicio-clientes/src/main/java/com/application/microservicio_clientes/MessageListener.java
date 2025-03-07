package com.application.microservicio_clientes;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MessageListener {

    @Bean
    public Consumer<String> input() {
        return message -> System.out.println("Mensaje recibido desde cuentas: " + message);
    }
}