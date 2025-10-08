package ru.user_service.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class Train {
    protected int id;
    protected String seriesName;
    protected long trainNumber;
    protected String dataBuild;
    protected int buildingMileage;
    protected String lastRepair;
    protected String condition;
    protected String imageUrl;
}
