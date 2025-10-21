package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.tcs.webdriver.BasePage;

import java.io.IOException;
import java.time.Duration;

import static org.tcs.webdriver.DriverManager.getDriver;
import static org.tcs.webdriver.DriverManager.quitDriver;

public class Hook extends BasePage {
    private WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(Hook.class);

    @Before
    public void setupBrowser() {
        driver = getDriver();
        LOGGER.info("Browser setup completed");
    }

    /**
     * Capture screenshot only when scenario fails.
     */
    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());

            }
        } catch (Exception e) {
            System.out.println("⚠️ Failed to capture screenshot on failure: " + e.getMessage());
        } finally {
            quitDriver();
        }
    }

    @AfterStep
    public void captureScreenshot(Scenario scenario) throws IOException {
      //  final byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        final byte[] screenshot=takeScreenshotAsBytes();
        scenario.attach(screenshot, "image/png", scenario.getName());
    }
}


