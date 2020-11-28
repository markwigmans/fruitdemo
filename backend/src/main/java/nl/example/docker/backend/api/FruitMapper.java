package nl.example.docker.backend.api;

import nl.example.docker.backend.data.Fruit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FruitMapper {
    @Mapping(source = "fruitId", target = "id")
    FruitDTO toDTO(Fruit entity);
}

