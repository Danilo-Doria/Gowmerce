package com.danilodoria.gowmerce_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Esto activa el mecanismo de auditoría automática de JPA ej(@CreatedDate/@LastModifiedDate)
public class GowmerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GowmerceApplication.class, args);
    }

}
