package org.tcs.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;*/
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.tcs.readers.ConfigReader;


import java.time.Duration;

import static org.tcs.webdriver.WaitHelper.removeWait;
import static org.tcs.webdriver.WaitHelper.setImplicitWait;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);

    private static void createDriver(String driverType) {
        LOGGER.info("ℹ️ Creating a new {} driver for thread {}...",
                driverType.toUpperCase(), Thread.currentThread().getId());

        driverType = driverType.toLowerCase();
        switch (driverType) {
            case "chrome":
                ChromeOptions options=new ChromeOptions();
                options.addArguments("--incognito");
                driver.set(new ChromeDriver(options));
                LOGGER.info("✅ ChromeDriver successfully created for thread {}", Thread.currentThread().getId());
                break;
            case "firefox":
                driver.set(new FirefoxDriver());
                LOGGER.info("✅ FirefoxDriver successfully created for thread {}", Thread.currentThread().getId());
                break;
            case "edge":
                driver.set(new EdgeDriver());
                LOGGER.info("✅ EdgeDriver successfully created for thread {}", Thread.currentThread().getId());
                break;
            default:
                LOGGER.error("❌ Unsupported browser type: {}", driverType);
                throw new RuntimeException("Browser type not yet supported.");
        }

        driver.get().manage().window().maximize();
        setImplicitWait();
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            LOGGER.warn("⚠️ No existing WebDriver found for thread {}. Creating a new one...",
                    Thread.currentThread().getId());
            createDriver(ConfigReader.getProperty("browser"));
        } else {
            LOGGER.debug("ℹ️ Returning existing WebDriver for thread {}", Thread.currentThread().getId());
        }
        return driver.get();
    }

    public static void quitDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            LOGGER.info("ℹ️ Quitting WebDriver for thread {}...", Thread.currentThread().getId());
            try {
                currentDriver.quit();
                LOGGER.info("✅ WebDriver quit successfully for thread {}", Thread.currentThread().getId());
            } catch (Exception e) {
                LOGGER.error("❌ Error while quitting WebDriver for thread {} - {}",
                        Thread.currentThread().getId(), e.getMessage());
            } finally {
                driver.remove();
                removeWait();
            }
        } else {
            LOGGER.warn("⚠️ No WebDriver instance found to quit for thread {}", Thread.currentThread().getId());
        }
    }
}
