package ru.storage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.storage.service.KafkaProducerService;
import ru.storage.service.MinioFileException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private String returnMessage;
    private final KafkaProducerService producerService;

    public GlobalExceptionHandler(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        returnMessage = "Ошибка входных параметров: " + ex.getMessage();
        producerService.sendExceptionMessage(returnMessage);
        return new ResponseEntity<>(returnMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MinioFileException.class)
    public ResponseEntity<String> badRequies(Exception ex) {
        returnMessage = "Ошибка доступа к сервису MinIO: " + ex.getMessage();
        producerService.sendExceptionMessage(returnMessage);
        return new ResponseEntity<>(returnMessage, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<String> dbException(Exception ex) {
        returnMessage = "Ошибка доступа к сервису базы данных): " + ex.getMessage() + " / " + ex;
        producerService.sendExceptionMessage(returnMessage);
        return new ResponseEntity<>(returnMessage, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> badequies(Exception ex) {
        returnMessage = "storage service error " + ex.getMessage() + " / " + ex;
        producerService.sendExceptionMessage(returnMessage);
        return new ResponseEntity<>(returnMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
