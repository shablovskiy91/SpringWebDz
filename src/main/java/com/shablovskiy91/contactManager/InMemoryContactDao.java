package com.shablovskiy91.contactManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryContactDao implements ContactDao {
    private long contactId = 1L;

    private Map<Long, Contact> contactIdMap;

    public InMemoryContactDao() {
        this.contactIdMap = new HashMap<>();
    }


    @Override
    public Contact addContact(String fullName, String telNumber) {
        contactId++;
        Contact contact= new Contact(contactId,  )
        return contact;
    }

    @Override
    public Optional<Contact> findContactByFullName(String fullName) {
        return Optional.empty();
    }

    @Override
    public Optional<Contact> findContactByTelNumber(String telNumber) {
        return Optional.empty();
    }

    @Override
    public Contact getContact(long contactId) {
        return null;
    }

    @Override
    public void setFullName(long contactId, String fullName) {

    }

    @Override
    public void setTelNumber(long contactId, String telNumber) {

    }

    @Override
    public void setEmail(long contactId, String email) {

    }
}
