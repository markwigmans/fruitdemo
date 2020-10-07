package nl.example.docker.frontend.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/backend")
@Slf4j
@RequiredArgsConstructor
public class BackendController {

    private final RestTemplate restTemplate;

    @Value("${backend.url:http://localhost:8081/backend}")
    private String backendUrl;

    // unclear why pattern matching doesn't work with path variable {*path}
    @GetMapping("/**")
    public String backend(HttpServletRequest request) {
        log.info("backend forward request: '{}'", request.getRequestURI());
        String postfix= request.getRequestURI().replaceFirst("/backend", "");

        return restTemplate.getForObject(backendUrl + postfix, String.class);
    }
}
