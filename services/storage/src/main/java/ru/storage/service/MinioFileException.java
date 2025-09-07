package ru.storage.service;

public class MinioFileException extends RuntimeException {
    public MinioFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
