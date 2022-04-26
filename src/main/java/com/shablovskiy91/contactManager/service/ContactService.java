package com.shablovskiy91.contactManager.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.shablovskiy91.contactManager.Contact;
import com.shablovskiy91.contactManager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class ContactService implements IContactService {

    @Autowired
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void uploadCSVFile(MultipartFile file) throws Exception {
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
                contactRepository.saveAll(contacts);

            } catch (Exception e) {
                throw e;
            }
        }
    }
}
