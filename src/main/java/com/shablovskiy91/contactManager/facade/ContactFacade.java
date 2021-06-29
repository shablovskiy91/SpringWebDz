package com.shablovskiy91.contactManager.facade;

import com.shablovskiy91.contactManager.Contact;
import com.shablovskiy91.contactManager.ContactDao;
import com.shablovskiy91.contactManager.ContactManagerMain;
import com.shablovskiy91.contactManager.controller.ContactController;
import com.shablovskiy91.contactManager.controller.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContactFacade {

    private final ContactDao contactDao;

    @Autowired
    public ContactFacade(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public ContactDto createContact(String fullName) {
        Contact contact = contactDao.addContact(fullName);
        return new ContactDto(contact);
    }

    public ContactDto getContactDto(long contactId) {
        return new ContactDto(contactDao.getContact(contactId));
    }

    public List<ContactDto> getAllContactDtos() {
        List<ContactDto> contactDtos = new ArrayList<>();
        for (Contact contact : contactDao.getAllContacts()) {
            contactDtos.add(new ContactDto(contact));
        }
        return contactDtos;
    }

    public ContactDto updateContact(long id, String fullName, String telNumber, String email) {
        Contact updatableContact = contactDao.getContact(id);
        if (updatableContact.getFullName() == null || !updatableContact.getFullName().equals(fullName)) {
            updatableContact.setFullName(fullName);
        }
        if (updatableContact.getTelNumber() == null || !updatableContact.getTelNumber().equals(telNumber)) {
            updatableContact.setTelNumber(telNumber);
        }
        if (updatableContact.getEmail() == null || !updatableContact.getEmail().equals(email)) {
            updatableContact.setEmail(email);
        }
        return new ContactDto(contactDao.getContact(id));
    }

}
