package com.gmail.ribil39;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

/*
        spring.mail.host=smtp.yandex.ru
        spring.mail.username=test-sergey-mail@yandex.ru
spring.mail.password=2346780
        spring.mail.port=465
        spring.mail.protocol=smtps
        spring.mail.properties.mail.smtp.auth = true
        spring.mail.properties.mail.smtp.starttls.enable = true

        mail.debug=true*/
