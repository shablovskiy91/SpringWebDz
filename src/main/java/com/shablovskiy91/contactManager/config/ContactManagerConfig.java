package com.shablovskiy91.contactManager.config;

import com.shablovskiy91.contactManager.ContactDao;
import com.shablovskiy91.contactManager.InMemoryContactDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactManagerConfig {

    @Bean
    public ContactDao contactDao() {
        return new InMemoryContactDao();
    }

}
