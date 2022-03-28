package com.shablovskiy91.contactManager;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Contact {
    private long contactId;
    @CsvBindByName(column = "fullname")
    private  String fullName;
    @CsvBindByName(column = "telnumber")
    private  String telNumber;
    @CsvBindByName(column = "email")
    private  String email;


    public Contact(long contactId, String fullName, String telNumber, String email) {
        this.contactId = contactId;
        this.fullName = fullName;
        this.telNumber = telNumber;
        this.email = email;
    }

    public Contact(String fullName, String telNumber, String email) {
        this.fullName = fullName;
        this.telNumber = telNumber;
        this.email = email;
    }

    public Contact(long contactId, String fullName) {
        this.contactId = contactId;
        this.fullName = fullName;
    }

    public Contact() {
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId) && Objects.equals(fullName, contact.fullName) &&
                Objects.equals(telNumber, contact.telNumber) &&
                Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, fullName, telNumber, email);
    }

    @Override
    public String toString() {
        return "Contact id " + contactId + " data: " + "\r\n" +
                "Full Name: " + fullName + "\r\n" +
                "Tel Number: " + telNumber + "\r\n" +
                "Email: " + email + "\r\n";
    }
}