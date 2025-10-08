package ru.user_service.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.user_service.dto.Train;
import ru.user_service.service.ProducerService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/train")
public class UserController {
    private final ProducerService producer;

    @GetMapping("/one")
    public ResponseEntity<Train> getTrain(@RequestParam("message") String message) throws InterruptedException {
        log.info("A request was received for train number '" + message + "'");
        Train train = producer.sendRequestToStorage(message);
        return ResponseEntity.ok(train);
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllTrains() {
        return ResponseEntity.ok("request 'for all' in the development process");
    }
}
