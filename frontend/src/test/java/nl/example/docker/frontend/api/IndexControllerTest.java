package nl.example.docker.frontend.api;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

    private static WebDriver driver;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeAll
    static void configureBrowser() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void init() {
        var options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        baseUrl = "localhost:" + port;
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Loading Angular App")
    void testLaunchAngular() {
        driver.get(baseUrl + "/");
        assertThat(driver.getTitle(), is("Docker Demo - Frontend"));
    }

    @Test
    @DisplayName("Forward Angular URL's")
    void testForwardAngular() {
        driver.get(baseUrl + "/unknown-to-spring");
        assertThat(driver.getTitle(), is("Docker Demo - Frontend"));
    }
}