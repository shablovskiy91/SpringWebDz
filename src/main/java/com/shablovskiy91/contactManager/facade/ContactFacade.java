package com.shablovskiy91.contactManager.facade;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.shablovskiy91.contactManager.Contact;
import com.shablovskiy91.contactManager.ContactDao;
import com.shablovskiy91.contactManager.controller.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

    public ContactDto updateContact(long contactId, String fullName, String telNumber, String email) {
        Contact updatableContact = contactDao.getContact(contactId);
        if (updatableContact.getFullName() == null || !updatableContact.getFullName().equals(fullName)) {
            contactDao.setFullName(contactId, fullName);
        }
        if (updatableContact.getTelNumber() == null || !updatableContact.getTelNumber().equals(telNumber)) {
            contactDao.setTelNumber(contactId, telNumber);
        }
        if (updatableContact.getEmail() == null || !updatableContact.getEmail().equals(email)) {
            contactDao.setEmail(contactId, email);
        }
        return new ContactDto(contactDao.getContact(contactId));
    }

    public void deleteContact(long contactId) {
        contactDao.deleteContact(contactId);
    }
}
