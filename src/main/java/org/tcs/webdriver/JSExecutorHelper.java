package org.tcs.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static org.tcs.webdriver.DriverManager.getDriver;
import static org.tcs.webdriver.WaitHelper.waitForVisibility;

public interface JSExecutorHelper extends ElementHelper {
    Logger LOGGER = LogManager.getLogger(JSExecutorHelper.class);

    default JavascriptExecutor js() {
        return (JavascriptExecutor) getDriver();
    }

    // 🔹 Scroll Methods
    default void scrollToElement(By locator) {
        WebElement element = waitForVisibility(locator);
        js().executeScript("arguments[0].scrollIntoView(true);", element);
        LOGGER.info("🖱️ Scrolled to element: {}", locator);
    }

    default void scrollToBottom() {
        js().executeScript("window.scrollTo(0, document.body.scrollHeight);");
        LOGGER.info("⬇️ Scrolled to bottom of page");
    }

    default void scrollToTop() {
        js().executeScript("window.scrollTo(0, 0);");
        LOGGER.info("⬆️ Scrolled to top of page");
    }

    default void scrollByPixels(int x, int y) {
        js().executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
        LOGGER.info("↕ Scrolled by pixels: X={}, Y={}", x, y);
    }

    // 🔹 Click & Input
    default void clickByJS(By locator) {
        WebElement element = waitForVisibility(locator);
        js().executeScript("arguments[0].click();", element);
        LOGGER.info("✔ Clicked element using JS: {}", locator);
    }

    default void sendKeysByJS(By locator, String value) {
        WebElement element = waitForVisibility(locator);
        js().executeScript("arguments[0].value='" + value + "';", element);
        LOGGER.info("⌨ Sent keys '{}' using JS to element: {}", value, locator);
    }

    // 🔹 Attribute Handling
    default void setAttribute(By locator, String attribute, String value) {
        WebElement element = waitForVisibility(locator);
        js().executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                element, attribute, value);
        LOGGER.info("📝 Set attribute '{}'='{}' for element: {}", attribute, value, locator);
    }

    default String getAttributeByJS(By locator, String attribute) {
        WebElement element = waitForVisibility(locator);
        Object value = js().executeScript("return arguments[0].getAttribute(arguments[1]);",
                element, attribute);
        LOGGER.info("ℹ Got attribute '{}'='{}' via JS from {}", attribute, value, locator);
        return value != null ? value.toString() : null;
    }

    default void removeElement(By locator) {
        WebElement element = waitForVisibility(locator);
        js().executeScript("arguments[0].remove();", element);
        LOGGER.warn("❌ Removed element from DOM: {}", locator);
    }

    // 🔹 Visual Helpers
    default void highlightElement(By locator) {
        WebElement element = waitForVisibility(locator);
        js().executeScript("arguments[0].style.border='3px solid red'", element);
        LOGGER.info("🎨 Highlighted element with red border: {}", locator);
    }

    default void flashElement(By locator) {
        WebElement element = waitForVisibility(locator);
        String originalStyle = element.getAttribute("style");
        for (int i = 0; i < 3; i++) {
            js().executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    element, "border: 3px solid yellow; background: pink;");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            js().executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    element, originalStyle);
        }
        LOGGER.info("✨ Flashed element for visibility: {}", locator);
    }

    // 🔹 Page Info & Control
    default String getInnerText() {
        String text = js().executeScript("return document.documentElement.innerText;").toString();
        LOGGER.info("ℹ Retrieved full innerText of page");
        return text;
    }

    default String getInnerHTML(By locator) {
        WebElement element = waitForVisibility(locator);
        String html = js().executeScript("return arguments[0].innerHTML;", element).toString();
        LOGGER.info("ℹ InnerHTML of {} => {}", locator, html);
        return html;
    }

    default void refreshPage() {
        js().executeScript("history.go(0)");
        LOGGER.info("🔄 Page refreshed using JS");
    }

    default String getPageTitleByJS() {
        String title = js().executeScript("return document.title;").toString();
        LOGGER.info("📄 Page title via JS => {}", title);
        return title;
    }

    default String getPageURLByJS() {
        String url = js().executeScript("return document.URL;").toString();
        LOGGER.info("🌐 Page URL via JS => {}", url);
        return url;
    }

    // 🔹 Zoom Controls
    default void zoomPage(int percentage) {
        js().executeScript("document.body.style.zoom='" + percentage + "%'");
        LOGGER.info("🔍 Zoom set to {}%", percentage);
    }
}
