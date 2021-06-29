package com.shablovskiy91.contactManager.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.shablovskiy91.contactManager.facade.ContactFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactFacade contactFacade;

    @Autowired
    public ContactController(ContactFacade contactFacade) {
        this.contactFacade = contactFacade;
    }

    @PostMapping
    public ContactDto createContact(
            @RequestParam String fullName
    ) {
        return contactFacade.createContact(fullName);
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

