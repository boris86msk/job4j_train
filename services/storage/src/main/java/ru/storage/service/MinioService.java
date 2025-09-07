package ru.storage.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {
    String uploadFile(MultipartFile file, String fileName);
    byte[] getFileContentAsByteArray(String fileName);
    void deleteFile(String fileName);
}
