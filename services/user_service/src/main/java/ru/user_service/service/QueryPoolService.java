package ru.user_service.service;

import org.springframework.stereotype.Service;
import ru.user_service.dto.Train;

import java.util.HashMap;

@Service
public class QueryPoolService {
    private static final HashMap<String, Train> pool = new HashMap<>();

    public static boolean checkRequest(String num) {
        return pool.containsKey(num);
    }

    public static void addResult (Train train) {
        long trainNumber = train.getTrainNumber();
        pool.put(String.format("%08d", trainNumber), train);
    }

    public static Train getTrainByNum(String key) {
        Train train = pool.get(key);
        return train;
    }
}
