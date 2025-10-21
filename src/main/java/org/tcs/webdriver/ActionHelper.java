package org.tcs.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.tcs.webdriver.DriverManager.getDriver;
import static org.tcs.webdriver.WaitHelper.waitForVisibility;

/**
 * ActionHelper - Wrapper library around Selenium's Actions class.
 * Provides advanced mouse/keyboard interactions with detailed logging.
 */
public interface ActionHelper {
    Logger LOGGER = LogManager.getLogger(ActionHelper.class);

    // Core Actions instance
    default Actions actions() {
        return new Actions(getDriver());
    }

    // ================= MOUSE ACTIONS =================

    default void mouseHover(By locator) {
        WebElement element = waitForVisibility(locator);
        try {
            LOGGER.info("Attempting Mouse Hover on element: {}", locator);
            actions().moveToElement(element).perform();
            LOGGER.info("✅ Mouse Hover successful on: {}", locator);
        } catch (Exception e) {
            LOGGER.error("❌ Mouse Hover failed on: {}", locator, e);
            throw e;
        }
    }

    default void doubleClick(By locator) {
        WebElement element = waitForVisibility(locator);
        try {
            LOGGER.info("Attempting Double Click on: {}", locator);
            actions().doubleClick(element).perform();
            LOGGER.info("✅ Double Click successful on: {}", locator);
        } catch (Exception e) {
            LOGGER.error("❌ Double Click failed on: {}", locator, e);
            throw e;
        }
    }

    default void rightClick(By locator) {
        WebElement element = waitForVisibility(locator);
        try {
            LOGGER.info("Attempting Right Click on: {}", locator);
            actions().contextClick(element).perform();
            LOGGER.info("✅ Right Click successful on: {}", locator);
        } catch (Exception e) {
            LOGGER.error("❌ Right Click failed on: {}", locator, e);
            throw e;
        }
    }

    default void clickAndHold(By locator) {
        WebElement element = waitForVisibility(locator);
        try {
            LOGGER.info("Attempting Click and Hold on: {}", locator);
            actions().clickAndHold(element).perform();
            LOGGER.info("✅ Click and Hold successful on: {}", locator);
        } catch (Exception e) {
            LOGGER.error("❌ Click and Hold failed on: {}", locator, e);
            throw e;
        }
    }

    default void release(By locator) {
        WebElement element = waitForVisibility(locator);
        try {
            LOGGER.info("Attempting Mouse Release on: {}", locator);
            actions().release(element).perform();
            LOGGER.info("✅ Mouse Release successful on: {}", locator);
        } catch (Exception e) {
            LOGGER.error("❌ Mouse Release failed on: {}", locator, e);
            throw e;
        }
    }

    default void dragAndDrop(By source, By target) {
        try {
            WebElement src = waitForVisibility(source);
            WebElement dest = waitForVisibility(target);
            LOGGER.info("Attempting Drag and Drop from [{}] to [{}]", source, target);
            actions().dragAndDrop(src, dest).perform();
            LOGGER.info("✅ Drag and Drop successful from [{}] to [{}]", source, target);
        } catch (Exception e) {
            LOGGER.error("❌ Drag and Drop failed from [{}] to [{}]", source, target, e);
            throw e;
        }
    }

    default void dragAndDropByOffset(By locator, int xOffset, int yOffset) {
        WebElement element = waitForVisibility(locator);
        try {
            LOGGER.info("Attempting Drag and Drop by offset (x: {}, y: {}) on {}", xOffset, yOffset, locator);
            actions().dragAndDropBy(element, xOffset, yOffset).perform();
            LOGGER.info("✅ Drag and Drop by offset successful on: {}", locator);
        } catch (Exception e) {
            LOGGER.error("❌ Drag and Drop by offset failed on: {}", locator, e);
            throw e;
        }
    }

    default void moveByOffset(int x, int y) {
        try {
            LOGGER.info("Attempting to move mouse cursor by offset (x: {}, y: {})", x, y);
            actions().moveByOffset(x, y).perform();
            LOGGER.info("✅ Mouse moved successfully by offset (x: {}, y: {})", x, y);
        } catch (Exception e) {
            LOGGER.error("❌ Failed to move mouse by offset (x: {}, y: {})", x, y, e);
            throw e;
        }
    }

    default void moveToElement(By locator) {
        WebElement element = waitForVisibility(locator);
        try {
            LOGGER.info("Attempting to move to element: {}", locator);
            actions().moveToElement(element).perform();
            LOGGER.info("✅ Move To Element successful: {}", locator);
        } catch (Exception e) {
            LOGGER.error("❌ Move To Element failed: {}", locator, e);
            throw e;
        }
    }

    default void moveToElementWithOffset(By locator, int x, int y) {
        WebElement element = waitForVisibility(locator);
        try {
            LOGGER.info("Attempting to move to element {} with offset (x: {}, y: {})", locator, x, y);
            actions().moveToElement(element, x, y).perform();
            LOGGER.info("✅ Move To Element with offset successful: {}", locator);
        } catch (Exception e) {
            LOGGER.error("❌ Move To Element with offset failed: {}", locator, e);
            throw e;
        }
    }

    // ================= KEYBOARD ACTIONS =================

    default void sendKeys(By locator, CharSequence keys) {
        WebElement element = waitForVisibility(locator);
        try {
            LOGGER.info("Attempting to send keys [{}] to element: {}", keys, locator);
            actions().sendKeys(element, keys).perform();
            LOGGER.info("✅ Keys [{}] sent successfully to: {}", keys, locator);
        } catch (Exception e) {
            LOGGER.error("❌ Failed to send keys [{}] to: {}", keys, locator, e);
            throw e;
        }
    }

    default void sendKeys(CharSequence keys) {
        try {
            LOGGER.info("Attempting to send keys globally: {}", keys);
            actions().sendKeys(keys).perform();
            LOGGER.info("✅ Keys [{}] sent globally", keys);
        } catch (Exception e) {
            LOGGER.error("❌ Failed to send keys globally: {}", keys, e);
            throw e;
        }
    }

    default void keyDown(By locator, Keys key) {
        WebElement element = waitForVisibility(locator);
        try {
            LOGGER.info("Attempting Key Down [{}] on element: {}", key.name(), locator);
            actions().keyDown(element, key).perform();
            LOGGER.info("✅ Key Down [{}] successful on: {}", key.name(), locator);
        } catch (Exception e) {
            LOGGER.error("❌ Key Down [{}] failed on: {}", key.name(), locator, e);
            throw e;
        }
    }

    default void keyUp(By locator, Keys key) {
        WebElement element = waitForVisibility(locator);
        try {
            LOGGER.info("Attempting Key Up [{}] on element: {}", key.name(), locator);
            actions().keyUp(element, key).perform();
            LOGGER.info("✅ Key Up [{}] successful on: {}", key.name(), locator);
        } catch (Exception e) {
            LOGGER.error("❌ Key Up [{}] failed on: {}", key.name(), locator, e);
            throw e;
        }
    }

    default void keyDown(Keys key) {
        try {
            LOGGER.info("Attempting Key Down [{}]", key.name());
            actions().keyDown(key).perform();
            LOGGER.info("✅ Key Down [{}] successful", key.name());
        } catch (Exception e) {
            LOGGER.error("❌ Key Down [{}] failed", key.name(), e);
            throw e;
        }
    }

    default void keyUp(Keys key) {
        try {
            LOGGER.info("Attempting Key Up [{}]", key.name());
            actions().keyUp(key).perform();
            LOGGER.info("✅ Key Up [{}] successful", key.name());
        } catch (Exception e) {
            LOGGER.error("❌ Key Up [{}] failed", key.name(), e);
            throw e;
        }
    }
}
