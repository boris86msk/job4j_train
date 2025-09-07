package ru.storage.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.storage.dto.TrainDto;
import ru.storage.dto.TrainInfo;
import ru.storage.entity.Train;
import ru.storage.service.TrainService;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class TestController {
    TrainService trainService;

    @PostMapping
    public ResponseEntity<TrainInfo> uploadTrain(@Valid @ModelAttribute TrainDto trainDto,
                                                 @RequestParam("file") MultipartFile file) {
        ModelMapper mapper = new ModelMapper();
        Train train = mapper.map(trainDto, Train.class);
        TrainInfo info = trainService.saveTrain(file, train);
        return ResponseEntity.ok(info);
    }

}
