package nl.example.docker.backend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.example.docker.backend.data.FruitRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Operation(summary = "Vraag alle Fruit op")
    public Iterable<FruitDTO> all() {
        log.info("all()");
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
