package nl.example.docker.frontend;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Generated // prevent JoCoCo from complaining
public class FrontendServer {

    public static void main(String[] args) {
        SpringApplication.run(FrontendServer.class, args);
    }
}
