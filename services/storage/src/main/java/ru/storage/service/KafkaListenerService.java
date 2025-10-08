package ru.storage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.spring.guides.gs_producing_web_service.XmlTrain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.storage.entity.Train;
import ru.storage.repository.TrainRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerService {
    private final TrainRepository trainRepository;
    private final KafkaProducerService producer;
    private final TrainMapping mapping;

    @KafkaListener(topics = "num-request")
    public void put(final String trainNum) throws JsonProcessingException {
        long parseLong = Long.parseLong(trainNum);
        log.info("message from 'num-request' " + trainNum);
        Optional<Train> byTrainNumber = trainRepository.findByTrainNumber(parseLong);
        if (byTrainNumber.isPresent()) {
            Train train = byTrainNumber.get();
            XmlTrain xmlTrain = mapping.trainToXmlTrain(train);
            producer.sendTrain(xmlTrain);
        }
    }
}
