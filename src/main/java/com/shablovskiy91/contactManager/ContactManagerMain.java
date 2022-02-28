package com.shablovskiy91.contactManager;

import com.shablovskiy91.contactManager.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ContactManagerMain {
    public static void main(String[] args) {
        SpringApplication.run(ContactManagerMain.class, args);

        var applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    }
}
