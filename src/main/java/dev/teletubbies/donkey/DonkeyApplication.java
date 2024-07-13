package dev.teletubbies.donkey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DonkeyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DonkeyApplication.class, args);
    }
}
