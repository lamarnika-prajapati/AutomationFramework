package org.tcs.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.tcs.webdriver.DriverManager.getDriver;

public interface AlertHelper {
    Logger LOGGER = LogManager.getLogger(AlertHelper.class);

    /**
     * Waits for and returns the current alert.
     */
    default Alert getAlert() {
        LOGGER.debug("Waiting for alert to be present...");
        Alert alert = WaitHelper.getWait().until(ExpectedConditions.alertIsPresent());
        LOGGER.info("✅ Alert appeared with text: [{}]", alert.getText());
        return alert;
    }

    /**
     * Accepts the alert if present.
     */
    default void acceptAlert() {
        try {
            Alert alert = getAlert();
            LOGGER.info("Attempting to accept alert with text: [{}]", alert.getText());
            alert.accept();
            LOGGER.info("✅ Alert accepted successfully.");
        } catch (Exception e) {
            LOGGER.error("❌ Failed to accept alert.", e);
            throw e;
        }
    }

    /**
     * Dismisses the alert if present.
     */
    default void dismissAlert() {
        try {
            Alert alert = getAlert();
            LOGGER.info("Attempting to dismiss alert with text: [{}]", alert.getText());
            alert.dismiss();
            LOGGER.info("✅ Alert dismissed successfully.");
        } catch (Exception e) {
            LOGGER.error("❌ Failed to dismiss alert.", e);
            throw e;
        }
    }

    /**
     * Gets the alert text.
     */
    default String getAlertText() {
        try {
            Alert alert = getAlert();
            String text = alert.getText();
            LOGGER.info("✅ Retrieved alert text: [{}]", text);
            return text;
        } catch (Exception e) {
            LOGGER.error("❌ Failed to retrieve alert text.", e);
            throw e;
        }
    }

    /**
     * Sends keys to the alert input box.
     */
    default void sendKeysToAlert(String text) {
        try {
            Alert alert = getAlert();
            LOGGER.info("Sending text [{}] to alert.", text);
            alert.sendKeys(text);
            LOGGER.info("✅ Text sent to alert successfully.");
        } catch (Exception e) {
            LOGGER.error("❌ Failed to send keys to alert.", e);
            throw e;
        }
    }

    /**
     * Checks if an alert is present without interacting with it.
     */
    default boolean isAlertPresent() {
        try {
            getDriver().switchTo().alert();
            LOGGER.info("✅ Alert is present.");
            return true;
        } catch (NoAlertPresentException e) {
            LOGGER.warn("⚠️ No alert is present.");
            return false;
        }
    }
}
