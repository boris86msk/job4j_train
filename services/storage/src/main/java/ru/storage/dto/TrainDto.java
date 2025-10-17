package ru.storage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TrainDto {
    @Schema(description = "Наименование серии", example = "ВЛ-40")
    @NotNull(message = "поле не может быть пустым")
    private String nameSeries;
    @Schema(description = "Восьмизначный серийный номер", example = "78254731")
    @NotNull(message = "поле не может быть пустым")
    private int trainNumber;
    @Schema(description = "Пробег от постройки (км)", example = "85730")
    @NotNull(message = "поле не может быть пустым")
    private int buildingMileage;
    @Schema(description = "Дата постройки", example = "1991-01-01")
    @NotNull(message = "поле не может быть пустым")
    private LocalDate dataBuild;
    @Schema(description = "Вид последнего ремонта", example = "ТР-1")
    private String lastRepair;
    @Schema(description = "Состояние", example = "В эксплуатации")
    @NotNull(message = "поле не может быть пустым")
    private String condition;
}
