package nl.example.docker.backend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.example.docker.backend.data.Fruit;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Import(OracleTestConfiguration.class)
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
        var response = restTemplate.getForEntity(baseUrl + "all", String.class).getBody();
        assertThat(response, not(blankOrNullString()));
        var fruits = Arrays.asList(MAPPER.readValue(response, FruitDTO[].class));
        assertThat("check size", fruits.size(), is(5));
        assertThat("check if all userId's are unique", fruits.stream().map(FruitDTO::getId).distinct().count(), CoreMatchers.is((long) fruits.size()));
    }
}