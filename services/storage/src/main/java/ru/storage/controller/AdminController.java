package ru.storage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.storage.dto.TrainDto;
import ru.storage.dto.TrainInfo;
import ru.storage.entity.Train;
import ru.storage.service.TrainService;

import java.time.LocalDate;

@Tag(name = "AdminController", description = "Database filling API")
@RestController
@AllArgsConstructor
@RequestMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
public class AdminController {
    TrainService trainService;

    @Operation(summary = "add data model",
            description = "Метод наполнения хранилища объектами (Train)")
    @PostMapping(value = "/add")
    public ResponseEntity<TrainInfo> uploadTrain(@RequestParam String nameSeries,
                                                 @RequestParam int trainNumber,
                                                 @RequestParam int buildingMileage,
                                                 @RequestParam LocalDate dataBuild,
                                                 @RequestParam String lastRepair,
                                                 @RequestParam String condition,
                                                 @RequestBody MultipartFile file) {
        ModelMapper mapper = new ModelMapper();
        TrainDto trainDto = new TrainDto(nameSeries, trainNumber, buildingMileage, dataBuild, lastRepair, condition);
        Train train = mapper.map(trainDto, Train.class);
        TrainInfo info = trainService.saveTrain(file, train);
        return ResponseEntity.ok(info);
    }

}
