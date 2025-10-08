package ru.storage.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TrainDto {
    int id;
    @NotNull(message = "поле не может быть пустым")
    private String nameSeries;
    @NotNull(message = "поле не может быть пустым")
    private int trainNumber;
    @NotNull(message = "поле не может быть пустым")
    private int buildingMileage;
    @NotNull(message = "поле не может быть пустым")
    private LocalDate dataBuild;
    private String lastRepair;
    @NotNull(message = "поле не может быть пустым")
    private String condition;
}
