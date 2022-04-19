package com.shablovskiy91.contactManager;

/**
 * Unit tests for {@link ContactDao}.
 *
 * Аннотация @Sql подтягивает SQL-скрипт contact.sql, который будет применен к базе перед выполнением теста.
 * Contact.sql создает таблицу CONTACT с полями (ID, FULLNAME, TELNUMBER, EMAIL) и вставляет в нее 2 записи.
 *
 * Тесты проверяют корректность реализации ContactDao.
 */

import com.shablovskiy91.contactManager.config.ApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql("classpath:contact.sql")
public class ContactDaoTests {
    @Autowired ContactDao contactDao;

        private static final Contact IVAN = new Contact(
        1000L, "Ivan Ivanov", "1234567", "iivanov@gmail.com"
        );

        private static final Contact MARIA = new Contact(
        2000L, "Maria Ivanova", "7654321", "mivanova@gmail.com"
        );

    /**
    * There are two contacts inserted in the database in contact.sql.
    */
    private static final List<Contact> PERSISTED_CONTACTS = List.of(IVAN, MARIA);

    @Test
    void addContact() {
        var contact = new Contact("Jackie Chan", "1234567890", "jchan@gmail.com");
        long contactId = contactDao.addContact(contact);
        contact.setContactId(contactId);

        var contactInDb = contactDao.getContact(contactId);


        assertThat(contactInDb).isEqualTo(contact);
    }

    @Test
    void getContact() {
        var contact = contactDao.getContact(IVAN.getContactId());
        assertThat(contact).isEqualTo(IVAN);
    }

    @Test
    void getAllContacts() {
        var contacts = contactDao.getAllContacts();

        assertThat(contacts).containsAll(PERSISTED_CONTACTS);
    }

    @Test
    void updatePhoneNumber() {
        var contact = new Contact("Jekyll Hide", "", "jhide@gmail.com");
        var contactId = contactDao.addContact(contact);
        var newPhone = "777-77-77";
        contactDao.setTelNumber(contactId, newPhone);

        var updatedContact = contactDao.getContact(contactId);
        assertThat(updatedContact.getTelNumber()).isEqualTo(newPhone);
    }

    @Test
    void updateEmail() {
        var contact = new Contact("Captain America", "", "");
        var contactId = contactDao.addContact(contact);

        var newEmail = "cap@gmail.com";
        contactDao.setEmail(contactId, newEmail);

        var updatedContact = contactDao.getContact(contactId);
        assertThat(updatedContact.getEmail()).isEqualTo(newEmail);
    }

@Test
    void deleteContact() {
        var contact = new Contact("To be Deleted", "", "");
        var contactId = contactDao.addContact(contact);

        contactDao.deleteContact(contactId);

        assertThat(contactDao.getContact(contactId)).isNull();

    }
}