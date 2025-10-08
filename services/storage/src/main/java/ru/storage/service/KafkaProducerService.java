package ru.storage.service;

import io.spring.guides.gs_producing_web_service.XmlTrain;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
 private final KafkaTemplate<Object, Object> template;

    protected void sendTrain(XmlTrain train) {
        template.send("train-response", train);
    }

    public void sendExceptionMessage(String message) {
        template.send("exception_top", message);
    }
}
