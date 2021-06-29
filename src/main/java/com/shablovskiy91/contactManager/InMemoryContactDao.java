package com.shablovskiy91.contactManager;

import java.util.*;

public class InMemoryContactDao implements ContactDao {
    private long contactId = 0L;

    private Map<Long, Contact> contactIdMap;

    public InMemoryContactDao() {
        this.contactIdMap = new HashMap<>();
    }


    @Override
    public Contact addContact(String fullName) {
        contactId++;
        Contact contact = new Contact(contactId, fullName);
        contactIdMap.put(contactId, contact);
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
        return findContact(contactId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    @Override
    public List<Contact> getAllContacts() {
        return new ArrayList<Contact>(contactIdMap.values());
    }

    @Override
    public Optional<Contact> findContact(long contactId) {
        return Optional.ofNullable(contactIdMap.get(contactId));
    }

    @Override
    public void setFullName(long contactId, String fullName) {
        var contact = getContact(contactId);
        contact.setFullName(fullName);
    }

    @Override
    public void setTelNumber(long contactId, String telNumber) {
        var contact = getContact(contactId);
        contact.setTelNumber(telNumber);
    }

    @Override
    public void setEmail(long contactId, String email) {
        var contact = getContact(contactId);
        contact.setEmail(email);
    }
}
