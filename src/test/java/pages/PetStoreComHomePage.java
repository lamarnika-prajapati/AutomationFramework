package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.tcs.webdriver.BasePage;

import java.util.List;

public class PetStoreComHomePage extends BasePage {
    Logger LOGGER = LogManager.getLogger(PetStoreComHomePage.class);
    WebDriver driver;
    By searchBarLocator = By.xpath("//span[text()='Search']//parent::a[@href=\"/search\"]");
    By searchInputLocator = By.xpath("//input[@type='search']");
    By searchedProductResultList = By.xpath("//div[contains(@class,'grid-product__meta')]/div[contains(@class,'grid-product__title grid-product__title--heading')]");
    By logoutButton = By.xpath("//a[@href=\"/account/logout\"]");
    By cartButton = By.xpath("//span[@class='cart-link']");

    public PetStoreComHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnSearchBarIcon() {
        LOGGER.info("Clicking on search bar");
        pause(1);
        getWait().until(ExpectedConditions.elementToBeClickable(searchBarLocator)).click();
    }

    public void enterProductInSearchBox(String productCategory) {
        LOGGER.info("entering product to search");
        getWait().until(ExpectedConditions.elementToBeClickable(searchInputLocator)).sendKeys(productCategory, Keys.ENTER);
    }

    public void clickOnProductToViewProductDetails(String productTitle) {
        LOGGER.info("Click on product : {}", productTitle);
        List<WebElement> allResultListOfSearchedProduct = waitForAllElementsVisible(searchedProductResultList);
        LOGGER.info("total of the searched result for product {} : {}", productTitle, allResultListOfSearchedProduct.size());

        for (WebElement product : allResultListOfSearchedProduct) {
            if (product.getText().equals(productTitle)) {
                LOGGER.info("Clicking on expected product {} to view the product details", product.getText());
                click(product);
                break;
            }
        }

    }

    public void clickOnCartLink()
    {
        pause(2);
        click(cartButton);
    }

    public boolean isLogoutButtonDisplayed() {
        return isDisplayed(logoutButton);
    }


}
