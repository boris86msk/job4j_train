package ru.user_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.user_service.dto.Train;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ProducerService {
    private static final  String REQUEST_TOPIC = "num-request";
    private final KafkaTemplate<String, String> template;

    public ProducerService(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public CompletableFuture<Train> sendRequestToStorage(String key) {
        if (key != null && key.matches("^\\d{8}$")) {
            Train result = QueryPoolService.getTrainByNum(key);
            if (result != null) {
                return CompletableFuture.completedFuture(result);
            }
            template.send(REQUEST_TOPIC, key);
            log.info("Send message to topic '{}'", REQUEST_TOPIC);
            return getResponseAsync(key);
        }
        log.info("train number is not valid or null");
        return null;
    }

    private CompletableFuture<Train> getResponseAsync(String key) {
        return CompletableFuture.supplyAsync(() -> {
            Train train = null;
            int attempts = 10;
            while (attempts > 0) {
                Train result = QueryPoolService.getTrainByNum(key);
                if (result != null) {
                    train = result;
                    break;
                }
                attempts--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            return train;
        });
    }
}
