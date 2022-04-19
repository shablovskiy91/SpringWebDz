package com.shablovskiy91.contactManager;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "CONTACT")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long contactId;
    @Column(name = "fullname", nullable = false)
    @CsvBindByName(column = "fullname")
    private String fullName;
    @Column(name = "telnumber")
    @CsvBindByName(column = "telnumber")
    private String telNumber;
    @Column(name = "email")
    @CsvBindByName(column = "email")
    private String email;

    public Contact() {
    }

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

    public Contact(String fullName) {
        this.fullName = fullName;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setEmail(String email) {
        this.email = email;
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