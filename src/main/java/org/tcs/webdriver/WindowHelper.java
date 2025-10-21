package org.tcs.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.tcs.webdriver.DriverManager.getDriver;

/**
 * Utility interface providing helper methods for handling multiple browser windows/tabs.
 */
public interface WindowHelper {
    Logger LOGGER = LogManager.getLogger(WindowHelper.class);

    /**
     * Switches to a browser window based on its index in the window handles list.
     *
     * @param index index of the window (0-based)
     */
    default void switchToWindowByIndex(int index) {
        List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        if (index < 0 || index >= windows.size()) {
            throw new IllegalArgumentException("Invalid window index: " + index + ". Total windows: " + windows.size());
        }
        getDriver().switchTo().window(windows.get(index));
        LOGGER.info("✅ Switched to window at index {}: Title='{}'", index, getDriver().getTitle());
    }

    /**
     * Switches to a browser window matching the given title.
     *
     * @param title exact title of the window
     */
    default void switchToWindowByTitle(String title) {
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
            if (getDriver().getTitle().equals(title)) {
                LOGGER.info("✅ Switched to window with title '{}'", title);
                return;
            }
        }
        throw new RuntimeException("❌ No window found with title: " + title);
    }

    /**
     * Switches to the parent (main/original) browser window.
     */
    default void switchToParentWindow() {
        getDriver().switchTo().defaultContent();
        LOGGER.info("✅ Switched to parent (default) window");
    }

    /**
     * Closes all browser windows except the parent window.
     */
    default void closeAllOtherWindows() {
        String parent = getDriver().getWindowHandle();
        Set<String> handles = getDriver().getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(parent)) {
                getDriver().switchTo().window(handle).close();
                LOGGER.info("❌ Closed child window: {}", handle);
            }
        }
        getDriver().switchTo().window(parent);
        LOGGER.info("✅ Back to parent window");
    }

    /**
     * Gets the number of open browser windows.
     *
     * @return total window count
     */
    default int getNumberOfWindows() {
        int count = getDriver().getWindowHandles().size();
        LOGGER.info("📊 Total number of open windows: {}", count);
        return count;
    }

    /**
     * Gets the title of the currently active browser window.
     *
     * @return current window title
     */
    default String getCurrentWindowTitle() {
        String title = getDriver().getTitle();
        LOGGER.info("📌 Current window title: {}", title);
        return title;
    }

    /**
     * Gets the handle (ID) of the currently active browser window.
     *
     * @return window handle
     */
    default String getCurrentWindowHandle() {
        String handle = getDriver().getWindowHandle();
        LOGGER.info("📌 Current window handle: {}", handle);
        return handle;
    }

    /**
     * Switches to the last opened window (most recently added tab).
     */
    default void switchToLastWindow() {
        List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(windows.get(windows.size() - 1));
        LOGGER.info("✅ Switched to last window: Title='{}'", getDriver().getTitle());
    }

    default void switchToMainWindow() {
        String mainWindow = getDriver().getWindowHandles().iterator().next();
        getDriver().switchTo().window(mainWindow);
        LOGGER.info("🔀 Switched to main window");
    }

    default void closeAllTabsAndSwitchToMainWindow() {
        Set<String> windows = getDriver().getWindowHandles();
        String mainWindow = getDriver().getWindowHandle();
        for (String window : windows) {
            if (!window.equals(mainWindow)) {
                getDriver().switchTo().window(window);
                getDriver().close();
                LOGGER.info("❌ Closed extra window: {}", window);
            }
        }
        getDriver().switchTo().window(mainWindow);
        LOGGER.info("🔀 Switched back to main window");
    }

    default void navigateBack() {
        getDriver().navigate().back();
        LOGGER.info("⬅ Navigated back");
    }

    default void navigateForward() {
        getDriver().navigate().forward();
        LOGGER.info("➡ Navigated forward");
    }
}
