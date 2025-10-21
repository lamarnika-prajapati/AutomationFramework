package org.tcs.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

import static org.tcs.webdriver.DriverManager.getDriver;
import static org.tcs.webdriver.WaitHelper.waitForVisibility;

/**
 * ValidationHelper provides reusable validation methods for
 * page titles, URLs, element visibility, text, attributes, states, and counts.
 *
 * ✅ Inline logging with symbols for easy debugging
 * ✅ Boolean return values for test-friendly usage
 * ✅ Covers common real-world validation needs
 */
public interface ValidationHelper extends ElementHelper {
    Logger LOGGER = LogManager.getLogger(ValidationHelper.class);

    /* ---------------- Page Validations ---------------- */

    default boolean waitForPageTitle(String title) {
        try {
            boolean result = WaitHelper.getWait().until(ExpectedConditions.titleIs(title));
            LOGGER.info("✅ Page title matched exactly: '{}'", title);
            return result;
        } catch (Exception e) {
            LOGGER.error("❌ Page title did not match '{}'", title, e);
            return false;
        }
    }

    default boolean validatePageTitleContains(String partialTitle) {
        try {
            boolean result = WaitHelper.getWait().until(ExpectedConditions.titleContains(partialTitle));
            LOGGER.info("✅ Page title contains: '{}'", partialTitle);
            return result;
        } catch (Exception e) {
            LOGGER.error("❌ Page title does not contain '{}'", partialTitle, e);
            return false;
        }
    }

    default boolean validatePageURL(String fraction) {
        try {
            boolean result = WaitHelper.getWait().until(ExpectedConditions.urlContains(fraction));
            LOGGER.info("✅ Page URL contains '{}'", fraction);
            return result;
        } catch (Exception e) {
            LOGGER.error("❌ Page URL does not contain '{}'", fraction, e);
            return false;
        }
    }

    /* ---------------- Element Validations ---------------- */

    default boolean validateElementPresent(By locator) {
        try {
            waitForVisibility(locator);
            LOGGER.info("✅ Element present: {}", locator);
            return true;
        } catch (Exception e) {
            LOGGER.error("❌ Element not present: {}", locator, e);
            return false;
        }
    }

    default boolean validateElementNotPresent(By locator) {
        try {
            WaitHelper.getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
            LOGGER.info("✅ Element not present as expected: {}", locator);
            return true;
        } catch (Exception e) {
            LOGGER.error("❌ Element still present: {}", locator, e);
            return false;
        }
    }

    default boolean validateElementClickable(By locator) {
        try {
            WaitHelper.getWait().until(ExpectedConditions.elementToBeClickable(locator));
            LOGGER.info("✅ Element clickable: {}", locator);
            return true;
        } catch (Exception e) {
            LOGGER.error("❌ Element not clickable: {}", locator, e);
            return false;
        }
    }

    default boolean validateElementVisible(By locator) {
        try {
            WaitHelper.getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            LOGGER.info("✅ Element visible: {}", locator);
            return true;
        } catch (Exception e) {
            LOGGER.error("❌ Element not visible: {}", locator, e);
            return false;
        }
    }

    default boolean validateElementEnabled(By locator) {
        try {
            WebElement element = waitForVisibility(locator);
            boolean enabled = element.isEnabled();
            LOGGER.info(enabled ? "✅ Element enabled: {}" : "❌ Element disabled: {}", locator);
            return enabled;
        } catch (Exception e) {
            LOGGER.error("❌ Could not validate enabled state of element: {}", locator, e);
            return false;
        }
    }

    default boolean validateElementSelected(By locator) {
        try {
            WebElement element = waitForVisibility(locator);
            boolean selected = element.isSelected();
            LOGGER.info(selected ? "✅ Element selected: {}" : "❌ Element not selected: {}", locator);
            return selected;
        } catch (Exception e) {
            LOGGER.error("❌ Could not validate selection state of element: {}", locator, e);
            return false;
        }
    }

    default boolean validateElementText(By locator, String expectedText) {
        try {
            boolean result = WaitHelper.getWait().until(ExpectedConditions.textToBe(locator, expectedText));
            LOGGER.info("✅ Element {} contains expected text '{}'", locator, expectedText);
            return result;
        } catch (Exception e) {
            LOGGER.error("❌ Element {} did not contain expected text '{}'", locator, expectedText, e);
            return false;
        }
    }

    default boolean validateElementContainsText(By locator, String partialText) {
        try {
            boolean result = WaitHelper.getWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, partialText));
            LOGGER.info("✅ Element {} contains partial text '{}'", locator, partialText);
            return result;
        } catch (Exception e) {
            LOGGER.error("❌ Element {} did not contain partial text '{}'", locator, partialText, e);
            return false;
        }
    }

    default boolean validateElementAttribute(By locator, String attribute, String value) {
        try {
            boolean result = WaitHelper.getWait().until(ExpectedConditions.attributeToBe(locator, attribute, value));
            LOGGER.info("✅ Element {} attribute '{}' has value '{}'", locator, attribute, value);
            return result;
        } catch (Exception e) {
            LOGGER.error("❌ Element {} attribute '{}' did not match value '{}'", locator, attribute, value, e);
            return false;
        }
    }

    default boolean validateElementAttributeContains(By locator, String attribute, String partialValue) {
        try {
            boolean result = WaitHelper.getWait().until(ExpectedConditions.attributeContains(locator, attribute, partialValue));
            LOGGER.info("✅ Element {} attribute '{}' contains '{}'", locator, attribute, partialValue);
            return result;
        } catch (Exception e) {
            LOGGER.error("❌ Element {} attribute '{}' does not contain '{}'", locator, attribute, partialValue, e);
            return false;
        }
    }

    /* ---------------- List & Count Validations ---------------- */

    default boolean validateNumberOfElements(By locator, int expectedCount) {
        try {
            List<WebElement> elements =
                    WaitHelper.getWait().until(ExpectedConditions.numberOfElementsToBe(locator, expectedCount));

            LOGGER.info("✅ Found exactly {} elements for {}", expectedCount, locator);
            return elements.size() == expectedCount;
        } catch (TimeoutException te) {
            int actualCount = getDriver().findElements(locator).size();
            LOGGER.error("❌ Expected {} elements for {}, but found {}", expectedCount, locator, actualCount);
            return false;
        } catch (Exception e) {
            LOGGER.error("❌ Could not validate element count for {}", locator, e);
            return false;
        }
    }

    default boolean validateAtLeastNumberOfElements(By locator, int minCount) {
        try {
            List<WebElement> elements =
                    WaitHelper.getWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, minCount));

            LOGGER.info("✅ Found at least {} elements for {} (actual: {})", minCount, locator, elements.size());
            return elements.size() > minCount;
        } catch (TimeoutException te) {
            int actualCount = getDriver().findElements(locator).size();
            LOGGER.error("❌ Expected more than {} elements for {}, but found {}", minCount, locator, actualCount);
            return false;
        } catch (Exception e) {
            LOGGER.error("❌ Could not validate minimum element count for {}", locator, e);
            return false;
        }
    }

    default boolean validateNoElements(By locator) {
        try {
            List<WebElement> elements = getDriver().findElements(locator);
            boolean result = elements.isEmpty();
            LOGGER.info(result ? "✅ No elements found for {}" : "❌ Unexpected elements found for {}", locator);
            return result;
        } catch (Exception e) {
            LOGGER.error("❌ Could not validate absence of elements for {}", locator, e);
            return false;
        }
    }
}
