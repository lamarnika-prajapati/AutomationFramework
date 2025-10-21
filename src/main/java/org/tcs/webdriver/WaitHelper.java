package org.tcs.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

import static org.tcs.readers.ConfigReader.getProperty;
import static org.tcs.webdriver.DriverManager.getDriver;

/**
 * Utility class for managing and providing FluentWait instances.
 * Adds reusable wait methods for common Selenium conditions.
 */
public class WaitHelper {
    private static final ThreadLocal<FluentWait<WebDriver>> wait = new ThreadLocal<>();
    static Logger LOGGER = LogManager.getLogger(WaitHelper.class);

    /**
     * Provides a thread-safe FluentWait object for the current WebDriver.
     * Timeout is configurable via config.properties (key: default.wait).
     */
    public static Wait<WebDriver> getWait() {
        String default_wait = getProperty("fluent.wait");
        int waitTimeout = Integer.parseInt(default_wait);
        if (wait.get() == null) {
            wait.set(new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(waitTimeout))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(ElementClickInterceptedException.class)
                    .ignoring(StaleElementReferenceException.class)
            );
            LOGGER.info("✅ Initialized FluentWait with default timeout {} seconds", waitTimeout);
        }
        return wait.get();
    }

    public static Wait<WebDriver> getWait(int waitTime) {
        LOGGER.info("✅ Returning a FluentWait with timeout {} seconds", waitTime);
        return new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(waitTime))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    /**
     * Sets the implicit wait for WebDriver from config.properties
     * (property key: implicit.wait).
     */
    public static void setImplicitWait() {
        String implicitWait = getProperty("implicit.wait");
        int seconds = Integer.parseInt(implicitWait);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        LOGGER.info("✅ Implicit wait set to {} seconds", seconds);
    }

    /**
     * Clears the current thread's FluentWait instance.
     */
    public static void removeWait() {
        if (wait.get() != null) {
            wait.remove();
            LOGGER.info("♻️ Removed FluentWait instance for current thread");
        }
    }

    // ---------- Enriched Utility Methods ---------- //

    public static WebElement waitForVisibility(By locator) {
        try {
            WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            LOGGER.info("✅ Element visible: {}", locator);
            return element;
        } catch (TimeoutException e) {
            LOGGER.error("❌ Timeout waiting for visibility of {}", locator, e);
            throw e;
        }
    }

    public static WebElement waitForClickable(By locator) {
        try {
            WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
            LOGGER.info("✅ Element clickable: {}", locator);
            return element;
        } catch (TimeoutException e) {
            LOGGER.error("❌ Timeout waiting for element clickable {}", locator, e);
            throw e;
        }
    }

    public static WebElement waitForClickable(WebElement element) {
        try {
            WebElement elementAvailable = getWait().until(ExpectedConditions.elementToBeClickable(element));
            LOGGER.info("✅ Element clickable: {}", elementAvailable.getText());
            return elementAvailable;
        } catch (TimeoutException e) {
            LOGGER.error("❌ Timeout waiting for element clickable {}", element.getText(), e);
            throw e;
        }
    }

    public static boolean waitForInvisibility(By locator) {
        try {
            boolean result = getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
            LOGGER.info("✅ Element invisible: {}", locator);
            return result;
        } catch (TimeoutException e) {
            LOGGER.error("❌ Timeout waiting for element invisibility {}", locator, e);
            return false;
        }
    }

    public static boolean waitForTitleContains(String partialTitle) {
        try {
            boolean result = getWait().until(ExpectedConditions.titleContains(partialTitle));
            LOGGER.info("✅ Page title contains '{}'", partialTitle);
            return result;
        } catch (TimeoutException e) {
            LOGGER.error("❌ Timeout waiting for page title to contain '{}'", partialTitle, e);
            return false;
        }
    }

    public static List<WebElement> waitForAllElementsVisible(By locator) {
        try {
            List<WebElement> elements = getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            LOGGER.info("✅ {} elements visible for {}", elements.size(), locator);
            return elements;
        } catch (TimeoutException e) {
            LOGGER.error("❌ Timeout waiting for visibility of elements {}", locator, e);
            throw e;
        }
    }

    public static void pause(int waitTime)
    {
        try {
            Thread.sleep(Duration.ofSeconds(waitTime));
        }
        catch (Exception e)
        {
            LOGGER.error("pause get failed");
            throw new RuntimeException(e);
        }

    }
}
