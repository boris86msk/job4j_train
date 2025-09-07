package ru.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.storage.entity.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
}
