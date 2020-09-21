package nl.example.docker.backend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.example.docker.backend.data.Fruit;
import nl.example.docker.backend.data.FruitRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/fruit")
@Slf4j
@Tag(name = "Fruit API", description = "Fruit Interface")
@RequiredArgsConstructor
public class FruitApi {

    private final FruitRepository repository;

    @GetMapping("/all")
    @Operation(summary = "Vraag alle Fruit")
    public Iterable<Fruit> all() {
        log.info("all()");
        return repository.findAll();
    }

    @GetMapping("/{fruitId}")
    @Operation(summary = "Vraag fruit op met fruit-id")
    public Optional<Fruit> find(@Parameter(description = "id of customer") @PathVariable int fruitId) {
        log.info("find({})", fruitId);
        return repository.findByFruitId(fruitId);
    }
}
