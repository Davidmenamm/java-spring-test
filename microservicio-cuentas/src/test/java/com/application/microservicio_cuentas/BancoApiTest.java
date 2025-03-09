package com.application.microservicio_cuentas;

import com.intuit.karate.junit5.Karate;


// Karate tests api
class BancoApiTest {

    @Karate.Test
    Karate testBancoApi() {
        return Karate.run("classpath:features/banco-api.feature").relativeTo(getClass());
    }
}