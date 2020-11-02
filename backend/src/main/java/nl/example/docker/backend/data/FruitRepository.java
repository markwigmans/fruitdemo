package nl.example.docker.backend.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Fruit> findByFruitId(int fruitId);
}
