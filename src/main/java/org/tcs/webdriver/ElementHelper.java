package org.tcs.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.tcs.webdriver.WaitHelper.*;


public interface ElementHelper {
    Logger LOGGER = LogManager.getLogger(ElementHelper.class);

    default void click(By locator) {
        LOGGER.info("🖱️ Clicking element: {}", locator);
        waitForClickable(locator).click();
    }

    default void click(WebElement element) {
        LOGGER.info("🖱️ Clicking element: {}", element.getText());
        waitForClickable(element).click();
    }


    default void sendKeys(By locator, String text) {
        LOGGER.info("⌨️ Typing '{}' into element: {}", text, locator);
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    default void clear(By locator) {
        LOGGER.info("🧹 Clearing text from element: {}", locator);
        waitForVisibility(locator).clear();
    }

    default String getText(By locator) {
        String text = waitForVisibility(locator).getText();
        LOGGER.info("ℹ️ Text from {} => '{}'", locator, text);
        return text;
    }

    default boolean isDisplayed(By locator) {
        try {
            boolean displayed = waitForVisibility(locator).isDisplayed();
            LOGGER.info("✔ Element {} is displayed", locator);
            return displayed;
        } catch (TimeoutException e) {
            LOGGER.warn("❌ Element {} is NOT displayed", locator);
            return false;
        }
    }

    default boolean isEnabled(By locator) {
        boolean enabled = waitForVisibility(locator).isEnabled();
        if (enabled) {
            LOGGER.info("✔ Element {} is enabled", locator);
        } else {
            LOGGER.warn("❌ Element {} is disabled", locator);
        }
        return enabled;
    }

    default String getAttribute(By locator, String attribute) {
        String value = waitForVisibility(locator).getAttribute(attribute);
        LOGGER.info("ℹ️ Attribute '{}' of {} => '{}'", attribute, locator, value);
        return value;
    }

    // 🔹 New utility methods

    default String getCssValue(By locator, String cssProperty) {
        String value = waitForVisibility(locator).getCssValue(cssProperty);
        LOGGER.info("🎨 CSS property '{}' of {} => '{}'", cssProperty, locator, value);
        return value;
    }

    default String getTagName(By locator) {
        String tag = waitForVisibility(locator).getTagName();
        LOGGER.info("🏷️ Tag name of {} => '{}'", locator, tag);
        return tag;
    }

    default boolean isSelected(By locator) {
        boolean selected = waitForVisibility(locator).isSelected();
        if (selected) {
            LOGGER.info("✔ Element {} is selected", locator);
        } else {
            LOGGER.info("❌ Element {} is not selected", locator);
        }
        return selected;
    }

    default void submit(By locator) {
        LOGGER.info("📨 Submitting element: {}", locator);
        waitForVisibility(locator).submit();
    }

    default int getElementCount(By locator) {
        List<WebElement> elements = DriverManager.getDriver().findElements(locator);
        int count = elements.size();
        LOGGER.info("ℹ️ Found {} elements for locator {}", count, locator);
        return count;
    }

    default boolean exists(By locator) {
        int count = getElementCount(locator);
        boolean exists = count > 0;
        if (exists) {
            LOGGER.info("✔ Element {} exists ({} found)", locator, count);
        } else {
            LOGGER.warn("❌ Element {} does not exist", locator);
        }
        return exists;
    }

    default boolean isElementPresent(By locator, int waitTime) {
        try {
            WebElement webElement = getWait(waitTime).until(ExpectedConditions.visibilityOfElementLocated(locator));
            LOGGER.info("Element {} is present on the page", webElement.getText());
            return true;
        } catch (TimeoutException e) {
            LOGGER.info("Element {} is not present on the page", locator);
            return false;
        }
    }

}
