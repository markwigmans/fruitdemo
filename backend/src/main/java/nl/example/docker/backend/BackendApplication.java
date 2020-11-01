package nl.example.docker.backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Docker Demo - Backend", version = "v1", description = "REST interface voor Docker Demo Backend"))
@Slf4j
public class BackendApplication {

    public static void main(String[] args) {
        // Oracle driver doesn't like timezone 'etc/UTC', this prevents this.
        System.setProperty("oracle.jdbc.timezoneAsRegion", "false");
        SpringApplication.run(BackendApplication.class, args);
    }
}
