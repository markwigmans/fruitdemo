package nl.example.docker.backend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.example.docker.backend.data.Fruit;
import nl.example.docker.backend.data.FruitRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/fruit")
@Slf4j
@Tag(name = "Fruit API", description = "Fruit Interface")
@RequiredArgsConstructor
public class FruitApi {

    private final FruitRepository repository;
    private final FruitMapper mapper;

    @GetMapping("/all")
    @Operation(summary = "Vraag alle Fruit")
    public Iterable<FruitDTO> all() {
        log.info("all()");
        List<Fruit> fruits = repository.findAll();
        return fruits.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{fruitId}")
    @Operation(summary = "Vraag fruit op met fruit-id")
    public ResponseEntity<FruitDTO> find(@Parameter(description = "id of customer") @PathVariable int fruitId) {
        log.info("find({})", fruitId);
        Optional<Fruit> fruit = repository.findByFruitId(fruitId);
        return fruit.map(value -> ResponseEntity.ok(mapper.toDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
