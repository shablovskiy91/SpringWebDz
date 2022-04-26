package com.shablovskiy91.contactManager.facade;

import com.shablovskiy91.contactManager.Contact;
import com.shablovskiy91.contactManager.controller.ContactDto;
import com.shablovskiy91.contactManager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContactFacade {

    @Autowired
    private final ContactRepository contactRepository;

    public ContactFacade(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactDto createContact(String fullName) {
        Contact contact = new Contact(fullName);
        contactRepository.save(contact);
        return new ContactDto(contact);
    }

    public ContactDto getContactDto(long contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        return new ContactDto(contact.get());
    }

    public List<ContactDto> getAllContactDtos() {
        List<ContactDto> contactDtos = new ArrayList<>();
        for (Contact contact : contactRepository.findAll()) {
            contactDtos.add(new ContactDto(contact));
        }
        return contactDtos;
    }

    public ContactDto updateContact(long contactId, String fullName, String telNumber, String email) {
        Optional<Contact> updatableContact = contactRepository.findById(contactId);
        if (updatableContact.get().getFullName() == null || !updatableContact.get().getFullName().equals(fullName)) {
            contactRepository.setFullName(contactId, fullName);
        }
        if (updatableContact.get().getTelNumber() == null || !updatableContact.get().getTelNumber().equals(telNumber)) {
            contactRepository.setTelNumber(contactId, telNumber);
        }
        if (updatableContact.get().getEmail() == null || !updatableContact.get().getEmail().equals(email)) {
            contactRepository.setEmail(contactId, email);
        }
        Optional<Contact> contact = contactRepository.findById(contactId);
        return new ContactDto(contact.get());
    }

    public void deleteContact(long contactId) {
        contactRepository.deleteById(contactId);
    }
}
