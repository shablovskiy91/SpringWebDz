package com.shablovskiy91.contactManager.config;

import com.shablovskiy91.contactManager.Contact;
import com.shablovskiy91.contactManager.ContactDao;
import com.shablovskiy91.contactManager.HibernateContactDao;
import com.shablovskiy91.contactManager.JdbcContactDao;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory() {
        var configuration = new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Contact.class)
                .configure();
        return configuration.buildSessionFactory();
    }
}
