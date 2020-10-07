package nl.example.docker.frontend.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

    @Container
    private static final BrowserWebDriverContainer Chrome = new BrowserWebDriverContainer().withCapabilities(new ChromeOptions());

    private static WebDriver browser;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeAll
    static void configureBrowser() {
        browser = Chrome.getWebDriver();
    }

    @BeforeEach
    void init() {
        baseUrl = "http://host.docker.internal:" + port;
    }

    @Test
    @DisplayName("Loading Angular App")
    void testLaunchAngular() {
        browser.get(baseUrl + "/");
        assertThat(browser.getTitle(), is("Docker Demo - Frontend"));
    }

    @Test
    @DisplayName("Forward Angular URL's")
    void testForwardAngular() {
        browser.get(baseUrl + "/unknown-to-spring");
        assertThat(browser.getTitle(), is("Docker Demo - Frontend"));
    }
}