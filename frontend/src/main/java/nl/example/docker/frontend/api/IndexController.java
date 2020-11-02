package nl.example.docker.frontend.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@Slf4j
public class IndexController {

    @Value("${api.url:http://localhost:8081/backend}")
    private String apiUrl;

    @GetMapping("/")
    public ModelAndView index() {
        log.info("index() request");
        Map<String, Object> params = Map.of("apiUrl", apiUrl);
        return new ModelAndView("index", params);
    }

    // single page application, so forward all calls
    @GetMapping("/**/{path:[^\\.]+}")
    public String forward() {
        log.info("forward request");
        return "forward:/";
    }
}
