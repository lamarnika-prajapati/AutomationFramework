package org.tcs.webdriver;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import static org.tcs.webdriver.DriverManager.getDriver;

/**
 * Screenshot helper utility for taking screenshots in different formats.
 * Provides methods to capture full page, element-specific, and base64 encoded screenshots.
 */
public interface ScreenshotHelper {
    Logger LOGGER = LogManager.getLogger(ScreenshotHelper.class);

    /**
     * Takes a full-page screenshot and saves it in the "screenshots" folder.
     *
     * @param fileName name of the screenshot file (without extension)
     * @return file path of the saved screenshot
     */
    default String takeScreenshot(String fileName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = "Screenshots/" + fileName + "_" + timestamp + ".png";
        try {
            File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(path));
            LOGGER.info("✅ Screenshot saved successfully at {}", path);
        } catch (IOException e) {
            LOGGER.error("❌ Failed to save screenshot: {}", e.getMessage());
        }
        return path;
    }

    /**
     * Takes a screenshot of a specific element.
     *
     * @param locator element locator
     * @param fileName name of the screenshot file
     * @return file path of the saved element screenshot
     */
    default String takeElementScreenshot(By locator, String fileName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = "screenshots/" + fileName + "_element_" + timestamp + ".png";
        try {
            WebElement element = getDriver().findElement(locator);
            File src = element.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(path));
            LOGGER.info("✅ Element screenshot saved at {}", path);
        } catch (IOException | NoSuchElementException e) {
            LOGGER.error("❌ Failed to capture element screenshot: {}", e.getMessage());
        }
        return path;
    }

    /**
     * Takes a screenshot and returns it as Base64 string (useful for embedding in reports).
     *
     * @return Base64 encoded screenshot string
     */
    default String takeScreenshotAsBase64() {
        try {
            String base64 = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
            LOGGER.info("✅ Screenshot captured as Base64");
            return base64;
        } catch (WebDriverException e) {
            LOGGER.error("❌ Failed to capture screenshot as Base64: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Takes a screenshot and returns raw bytes (for advanced reporting integrations).
     *
     * @return screenshot in byte[] format
     */
    default byte[] takeScreenshotAsBytes() {
        try {
            byte[] bytes = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            LOGGER.info("✅ Screenshot captured as byte array");
            return bytes;
        } catch (WebDriverException e) {
            LOGGER.error("❌ Failed to capture screenshot as bytes: {}", e.getMessage());
            return new byte[0];
        }
    }
}
