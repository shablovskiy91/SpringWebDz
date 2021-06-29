package com.shablovskiy91.contactManager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ContactDao {

    public Contact addContact(String fullName);

    // Optional - так как контакт может быть и не найден. Тогда мы вернём пустой Optional
    Optional<Contact> findContact(long contactId);
    Optional<Contact> findContactByFullName(String fullName);
    Optional<Contact> findContactByTelNumber(String telNumber);
    Contact getContact(long contactId);
    List<Contact> getAllContacts();
    void setFullName(long contactId, String fullName);
    void setTelNumber(long contactId, String telNumber);
    void setEmail(long contactId, String email);

}
