package ru.storage.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.storage.dto.TrainInfo;
import ru.storage.entity.Train;
import ru.storage.repository.TrainRepository;
import ru.storage.service.minio.MinioService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TrainServiceImpl implements TrainService {
    MinioService minioService;
    TrainRepository trainRepository;

    @Transactional
    public TrainInfo saveTrain(MultipartFile file, Train train) {
        String uniqueFileName = "image_" + UUID.randomUUID();
        String imageUrl = minioService.uploadFile(file, uniqueFileName);
        train.setImageUrl(imageUrl);
        Train save = trainRepository.save(train);
        int id = save.getId();
        return new TrainInfo(id, imageUrl);
    }
}
