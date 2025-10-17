package ru.storage.service.minio;

public class MinioFileException extends RuntimeException {
    public MinioFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
