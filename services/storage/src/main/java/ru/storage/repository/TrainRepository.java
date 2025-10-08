package ru.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.storage.entity.Train;

import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    Optional<Train> findByTrainNumber(long trainNumber);
}
