package com.shablovskiy91.contactManager.controller;

import com.shablovskiy91.contactManager.facade.ContactFacade;
import com.shablovskiy91.contactManager.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactFacade contactFacade;
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactFacade contactFacade, ContactService contactService) {
        this.contactFacade = contactFacade;
        this.contactService = contactService;
    }

    @PostMapping("")
    public ContactDto createContact(
            @RequestParam String fullName
    ) {
        return contactFacade.createContact(fullName);
    }

    @PostMapping("/delete")
    public void deleteContact(
            @RequestParam long contactId
    ) {
        contactFacade.deleteContact(contactId);
    }

    @PostMapping("/upload-csv-file")
    public ResponseEntity<String> uploadCSVFile(
            @RequestParam ("file") MultipartFile file
    ) {
        try {

            contactService.uploadCSVFile(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            //.body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.toString());
        }

    }

    @GetMapping
    public List<ContactDto> getAllContacts() {
        return contactFacade.getAllContactDtos();
    }

    @GetMapping("/{contactId}")
    public ContactDto getContact(
            @PathVariable long contactId
    ) {
        return contactFacade.getContactDto(contactId);
    }

    @PutMapping("/{contactId}")
    public ContactDto putDataInContact(
            @PathVariable long contactId,
            @RequestParam String fullName,
            @RequestParam String telNumber,
            @RequestParam String email
    ) {
        contactFacade.updateContact(contactId, fullName, telNumber, email);
        return contactFacade.getContactDto(contactId);
    }



}

