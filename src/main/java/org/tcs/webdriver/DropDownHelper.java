package org.tcs.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.tcs.webdriver.WaitHelper.waitForVisibility;

public interface DropDownHelper {
    Logger LOGGER = LogManager.getLogger(DropDownHelper.class);

    default Select getSelect(By locator) {
        WebElement dropdown = waitForVisibility(locator);
        LOGGER.debug("ℹ️ Located dropdown element at {}", locator);
        return new Select(dropdown);
    }

    default void selectByVisibleText(By locator, String text) {
        LOGGER.info("✅ Selecting option by visible text: '{}'", text);
        getSelect(locator).selectByVisibleText(text);
    }

    default void selectByValue(By locator, String value) {
        LOGGER.info("✅ Selecting option by value: '{}'", value);
        getSelect(locator).selectByValue(value);
    }

    default void selectByIndex(By locator, int index) {
        LOGGER.info("✅ Selecting option by index: {}", index);
        getSelect(locator).selectByIndex(index);
    }

    default String getDropdownSelectedValue(By locator) {
        String selectedValue = getSelect(locator).getFirstSelectedOption().getText();
        LOGGER.info("ℹ️ Currently selected value: '{}'", selectedValue);
        return selectedValue;
    }

    default List<String> getAllDropDownValues(By locator) {
        List<WebElement> optionsWebElements = getSelect(locator).getOptions();
        List<String> allOptionsValues = new ArrayList<>();
        for (WebElement option : optionsWebElements) {
            allOptionsValues.add(option.getText());
        }
        LOGGER.info("ℹ️ Available dropdown options: {}", allOptionsValues);
        return allOptionsValues;
    }

    default void deSelectUsingVisibleText(By locator, String text) {
        LOGGER.info("⚠️ De-selecting option by visible text: '{}'", text);
        getSelect(locator).deselectByVisibleText(text);
    }

    default void deSelectUsingIndex(By locator, int index) {
        LOGGER.info("⚠️ De-selecting option by index: {}", index);
        getSelect(locator).deselectByIndex(index);
    }

    default void deSelectUsingValue(By locator, String value) {
        LOGGER.info("⚠️ De-selecting option by value: '{}'", value);
        getSelect(locator).deselectByValue(value);
    }

    default void deSelectAll(By locator) {
        LOGGER.info("⚠️ De-selecting ALL options for dropdown {}", locator);
        getSelect(locator).deselectAll();
    }

    default boolean isMultiple(By locator) {
        boolean multiple = getSelect(locator).isMultiple();
        if (multiple) {
            LOGGER.info("ℹ️ Dropdown {} allows multiple selections", locator);
        } else {
            LOGGER.info("ℹ️ Dropdown {} does NOT allow multiple selections", locator);
        }
        return multiple;
    }

    default void selectMultipleByVisibleText(By locator, List<String> texts) {
        Select select = getSelect(locator);
        if (!select.isMultiple()) {
            LOGGER.error("❌ Dropdown {} does NOT support multiple selections!", locator);
            throw new UnsupportedOperationException("Dropdown does not support multiple selection");
        }
        for (String text : texts) {
            LOGGER.info("✅ Selecting option (multi) by visible text: '{}'", text);
            select.selectByVisibleText(text);
        }
    }
}
