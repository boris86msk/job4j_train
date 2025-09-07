package ru.storage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "train")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String series;
    private String nameSeries;
    private int trainNumber;
    private int buildingMileage;
    private LocalDate dataBuild;
    private String lastRepair;
    private String condition;
    private String imageUrl;
}
