package com.shablovskiy91.contactManager.repository;

import com.shablovskiy91.contactManager.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Modifying
    @Transactional
    @Query("update Contact c set c.fullName = :fullName where c.id = :contactId")
    void setFullName(
            @Param("contactId")
                    long contactId,
            @Param("fullName")
                    String fullName);

    @Modifying
    @Transactional
    @Query("update Contact c set c.telNumber = :telNumber where c.id = :contactId")
    void setTelNumber(
            @Param("contactId")
                    long contactId,
            @Param("telNumber")
                    String telNumber);
    @Modifying
    @Transactional
    @Query("update Contact c set c.email = :email where c.id = :contactId")
    void setEmail(
            @Param("contactId")
                    long contactId,
            @Param("email")
                    String email);
}