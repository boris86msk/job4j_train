package ru.storage.service;

import org.springframework.web.multipart.MultipartFile;
import ru.storage.dto.TrainInfo;
import ru.storage.entity.Train;

public interface TrainService {
    TrainInfo saveTrain(MultipartFile file, Train train);
}
