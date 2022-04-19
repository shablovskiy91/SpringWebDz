package com.shablovskiy91.contactManager.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shablovskiy91.contactManager.Contact;

@JsonDeserialize
public class ContactDto {

    @JsonProperty("id")
    private final long contactId;

    @JsonProperty("fullName")
    private final String fullName;

    @JsonProperty("telNumber")
    private final String telNumber;

    @JsonProperty("email")
    private final String email;

    public ContactDto(Contact contact) {
        this.contactId = contact.getContactId();
        this.fullName = contact.getFullName();
        this.telNumber = contact.getTelNumber();
        this.email = contact.getEmail();
    }

    public long getContactId() {
        return contactId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getEmail() {
        return email;
    }

}
