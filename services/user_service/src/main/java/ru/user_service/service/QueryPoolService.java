package ru.user_service.service;

import org.springframework.stereotype.Service;
import ru.user_service.dto.Train;

import java.util.HashMap;

@Service
public class QueryPoolService {
    private static final HashMap<String, Train> POOL = new HashMap<>();

    public static boolean checkRequest(String num) {
        return POOL.containsKey(num);
    }

    public static void addResult(Train train) {
        long trainNumber = train.getTrainNumber();
        POOL.put(String.format("%08d", trainNumber), train);
    }

    public static Train getTrainByNum(String key) {
        Train train = POOL.get(key);
        return train;
    }
}
