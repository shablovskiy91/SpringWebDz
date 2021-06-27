package com.shablovskiy91.contactManager;

import java.util.Optional;

public interface ContactDao {

    public Contact addContact(String fullName, String telNumber);

    // Optional - так как контакт может быть и не найден. Тогда мы вернём пустой Optional
    Optional<Contact> findContactByFullName(String fullName);

    Optional<Contact> findContactByTelNumber(String telNumber);

    Contact getContact(long contactId);

    void setFullName(long contactId, String fullName);

    void setTelNumber(long contactId, String telNumber);

    void setEmail(long contactId, String email);

}
