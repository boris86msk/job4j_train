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
    @Column(name = "name_series")
    private String nameSeries;
    @Column(name = "train_number")
    private int trainNumber;
    @Column(name = "building_mileage")
    private int buildingMileage;
    @Column(name = "data_build")
    private LocalDate dataBuild;
    @Column(name = "last_repair")
    private String lastRepair;
    private String condition;
    @Column(name = "image_url")
    private String imageUrl;
}
