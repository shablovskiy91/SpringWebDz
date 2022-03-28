package com.shablovskiy91.contactManager.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.shablovskiy91.contactManager.Contact;
import com.shablovskiy91.contactManager.ContactDao;
import com.shablovskiy91.contactManager.controller.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService implements IContactService {

    private final ContactDao contactDao;

    @Autowired
    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public void uploadCSVFile(MultipartFile file) throws Exception {
        List<ContactDto> contactDtos = new ArrayList<>();
        // validate file
        if (file.isEmpty()) {
            throw new NullPointerException("CSV-file to upload is empty");
        } else {
            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<Contact> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Contact.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of contacts
                List<Contact> contacts = csvToBean.parse();

                // add contacts to Database
                contactDao.addContacts(contacts);

            } catch (Exception e) {
                throw e;
            }
        }
    }
}
