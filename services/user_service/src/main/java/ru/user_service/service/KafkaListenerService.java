package ru.user_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.user_service.dto.Train;

import java.io.IOException;
import java.io.StringReader;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerService {
    private static final String RESPONSE_TOPIC = "train-response";

    @KafkaListener(topics = RESPONSE_TOPIC)
    public void put(String stringTrain) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(stringTrain);
        Train train = mapper.readValue(reader, Train.class);

        QueryPoolService.addResult(train);
        log.info("Received a response in '{}'", RESPONSE_TOPIC);
    }
}
