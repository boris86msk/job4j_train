package ru.user_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.user_service.dto.Train;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KafkaListenerServiceTest {
    @Test
    public void myTest() {
        Map<String, String> map = new HashMap<>();
        String key = "123";
        map.put(key, "OK");
        System.out.println(map.get(key));
        map.remove(key);
        map.get(key);
    }
}