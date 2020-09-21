package nl.example.docker.backend.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

    Optional<Fruit> findByFruitId(int fruitId);
}
