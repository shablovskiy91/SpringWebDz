package com.shablovskiy91.contactManager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ContactDao {

    public Contact addContact(long contactId, String fullName);
    public Contact addContact(String fullName);
    Contact getContact(long contactId);
    List<Contact> getAllContacts();
    void setFullName(long contactId, String fullName);
    void setTelNumber(long contactId, String telNumber);
    void setEmail(long contactId, String email);

}
