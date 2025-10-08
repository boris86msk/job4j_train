package ru.user_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.user_service.dto.Train;

@Service
@Slf4j
public class ProducerService {
    private static final  String REQUEST_TOPIC = "num-request";
    private int readPoolCount = 10;
    private final KafkaTemplate<String, String> template;

    public ProducerService(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public Train sendRequestToStorage(String key) throws InterruptedException {
        if (key != null && key.matches("^\\d{8}$")) {
            Train result = QueryPoolService.getTrainByNum(key);
            if (result != null) {
                return result;
            }
            template.send(REQUEST_TOPIC, key);
            log.info("Send message to topic '" + REQUEST_TOPIC + "'");
            return getResponse(key);
        }
        log.info("train number is not valid or null");
        return null;
    }

    private Train getResponse(String key) throws InterruptedException {
        Train train = null;
        while (readPoolCount > 0)  {
            Thread.sleep(1000);
            Train result = QueryPoolService.getTrainByNum(key);
            if (result == null) {
                readPoolCount -= 1;
            } else {
                train = result;
                break;
            }
        }
        readPoolCount = 10;
        return train;
    }
}
