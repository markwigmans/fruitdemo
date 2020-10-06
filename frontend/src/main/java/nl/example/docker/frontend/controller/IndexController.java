package nl.example.docker.frontend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@Slf4j
public class IndexController {

    @Value("${backend.url:http://localhost:8080}")
    private String apiUrl;

    @RequestMapping("/")
    public ModelAndView index() {
        log.debug("index() request");
        Map<String, Object> params = Map.of("apiUrl", apiUrl);
        return new ModelAndView("index", params);
    }

    // single page application, so forward all calls
    @RequestMapping("/**/{path:[^\\.]+}")
    public String forward() {
        log.debug("forward request");
        return "forward:/";
    }
}
