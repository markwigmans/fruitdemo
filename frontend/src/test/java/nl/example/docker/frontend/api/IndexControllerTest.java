package nl.example.docker.frontend.api;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularButtonText;
import com.paulhammant.ngwebdriver.NgWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

    private static WebDriver driver;
    private static NgWebDriver ngDriver;

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
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        ngDriver = new NgWebDriver((JavascriptExecutor) driver);
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
        ngDriver.waitForAngularRequestsToFinish();\
        assertThat(driver.getTitle(), is("Docker Demo - Frontend"));
    }

    @Test
    @DisplayName("Forward Angular URL's")
    void testForwardAngular() {
        driver.get(baseUrl + "/unknown-to-spring");
        ngDriver.waitForAngularRequestsToFinish();
        assertThat(driver.getTitle(), is("Docker Demo - Frontend"));
    }
}