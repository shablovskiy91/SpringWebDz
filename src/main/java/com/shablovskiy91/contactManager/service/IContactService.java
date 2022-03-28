package com.shablovskiy91.contactManager.service;

import com.shablovskiy91.contactManager.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IContactService {
    void uploadCSVFile(MultipartFile file) throws Exception;
}
