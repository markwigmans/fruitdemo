package nl.example.docker.backend.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final FruitRepository repository;

    @Override
    @Transactional
    public void onApplicationEvent(@NonNull ApplicationReadyEvent applicationReadyEvent) {
        saveIfNotExist(new Fruit(null, 1, "Banaan"));
        saveIfNotExist(new Fruit(null, 2, "Druif"));
        saveIfNotExist(new Fruit(null, 3, "Appel"));
        saveIfNotExist(new Fruit(null, 4, "Sinaasappel"));
        saveIfNotExist(new Fruit(null, 5, "Aardbei"));
    }

    void saveIfNotExist(Fruit fruit) {
        log.info("check to add '{}'", fruit);
        if (repository.findByFruitId(fruit.getFruitId()).isEmpty()) {
            log.info("'{}' saved", fruit);
            repository.save(fruit);
        }
    }
}
