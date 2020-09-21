package nl.example.docker.backend.data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final FruitRepository repository;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent applicationReadyEvent) {
        saveIfNotExist(new Fruit(null, 1, "Banaan"));
        saveIfNotExist(new Fruit(null, 2, "Druif"));
        saveIfNotExist(new Fruit(null, 3, "Appel"));
        saveIfNotExist(new Fruit(null, 4, "Sinaasappel"));
        saveIfNotExist(new Fruit(null, 5, "Aardbei"));
    }

    void saveIfNotExist(Fruit fruit) {
        if (repository.findByFruitId(fruit.getFruitId()).isEmpty()) {
            repository.save(fruit);
        }
    }
}
