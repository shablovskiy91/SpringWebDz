package com.shablovskiy91.contactManager;

public class Contact {
    private final long id;
    private String fullName;
    private String telNumber;
    private String email;

    public Contact(long id, String fullName, String telNumber, String email) {
        this.id = id;
        this.fullName = fullName;
        this.telNumber = telNumber;
        this.email = email;
    }

    public long getId() {
        return id;
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
    public String toString() {
        return "Contact id " + id + " data: " + "\r\n" +
                "Full Name: " + fullName + "\r\n" +
                "Tel Number: " + telNumber + "\r\n" +
                "Email: " + email + "\r\n";
    }
}