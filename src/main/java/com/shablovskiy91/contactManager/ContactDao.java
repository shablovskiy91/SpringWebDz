package com.shablovskiy91.contactManager;

import java.util.List;

public interface ContactDao {

    long addContact(Contact contact);
    Contact addContact(String fullName);
    List<Contact> addContacts (List<Contact> contacts);
    Contact getContact(long contactId);
    List<Contact> getAllContacts();
    void setFullName(long contactId, String fullName);
    void setTelNumber(long contactId, String telNumber);
    void setEmail(long contactId, String email);
    void deleteContact(long contactId);
}
