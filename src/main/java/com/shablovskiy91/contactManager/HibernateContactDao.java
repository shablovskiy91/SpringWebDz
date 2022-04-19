package com.shablovskiy91.contactManager;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("hibernate")
@Primary
public class HibernateContactDao implements ContactDao{

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateContactDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long addContact(Contact contact) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var contactId = (long) session.save(contact);
            transaction.commit();
            return contactId;
        }
    }

    @Override
    public Contact addContact(String fullName) {
        var contact = new Contact(fullName);
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var id = (Long) session.save(contact);
            transaction.commit();
            return contact;
        }
    }

    @Override
    public void addContacts(List<Contact> contacts) {
        for (var contact : contacts) {
            try (var session = sessionFactory.openSession()) {
                var transaction = session.beginTransaction();
                var id = (Long) session.save(contact);
                transaction.commit();
            }
        }
    }

    @Override
    public Contact getContact(long contactId) {
        try (var session = sessionFactory.openSession()) {
            return session.get(Contact.class, contactId);
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("SELECT c FROM Contact c", Contact.class);
            return query.getResultList();
        }
    }

    @Override
    public void setFullName(long contactId, String fullName) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var updatableContact = getContact(contactId);
            if (updatableContact != null) {
                updatableContact.setFullName(fullName);
                session.update(updatableContact);
            }
            transaction.commit();
        }
    }

    @Override
    public void setTelNumber(long contactId, String telNumber) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var updatableContact = getContact(contactId);
            if (updatableContact != null) {
                updatableContact.setTelNumber(telNumber);
                session.update(updatableContact);
            }
            transaction.commit();
        }
    }

    @Override
    public void setEmail(long contactId, String email) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var updatableContact = getContact(contactId);
            if (updatableContact != null) {
                updatableContact.setEmail(email);
                session.update(updatableContact);
            }
            transaction.commit();
        }
    }

    @Override
    public void deleteContact(long contactId) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var contact = getContact(contactId);
            if (contact != null) {
                session.delete(contact);
            }
            transaction.commit();
        }
    }
}
