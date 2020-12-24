package nl.example.docker.backend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ContextConfiguration(initializers = {FruitApiTest.Initializer.class})
class FruitApiTest {

    @Container
    private static final OracleContainer oracleContainer = new OracleContainer("oracleinanutshell/oracle-xe-11g:latest");

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;

    private String baseUrl;

    @BeforeEach
    void init() {
        baseUrl = "http://localhost:" + port + "/fruit/";
    }

    @Test
    void all() throws JsonProcessingException {
        var response = restTemplate.getForEntity(baseUrl + "all", String.class).getBody();
        assertThat(response, not(blankOrNullString()));
        var fruits = Arrays.asList(objectMapper.readValue(response, FruitDTO[].class));
        assertThat("check size", fruits.size(), is(5));
        assertThat("check if all userId's are unique", fruits.stream().map(FruitDTO::getId).distinct().count(), CoreMatchers.is((long) fruits.size()));
    }

    /**
     * Initialize test environment
     */
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + oracleContainer.getJdbcUrl(),
                    "spring.datasource.username=" + oracleContainer.getUsername(),
                    "spring.datasource.password=" + oracleContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}