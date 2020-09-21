package nl.example.docker.backend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.example.docker.backend.data.Fruit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FruitApiTest {

    static final ObjectMapper MAPPER = new ObjectMapper();

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    private String baseUrl;

    @BeforeEach
    void init() {
        baseUrl = "http://localhost:" + port + "/fruit/";
    }


    @Test
    void all() throws JsonProcessingException {
        String response = restTemplate.getForEntity(baseUrl + "all", String.class).getBody();
        assertThat(response, not(blankOrNullString()));
        Fruit[] fruits = MAPPER.readValue(response, Fruit[].class);
        assertThat(fruits.length, is(5));
    }

    @Test
    void id() throws JsonProcessingException {
        int id = 2;
        String response = restTemplate.getForEntity(baseUrl + id, String.class).getBody();
        Fruit fruit = MAPPER.readValue(response, Fruit.class);
        assertThat(fruit.getFruitId(), is(id));
    }
}