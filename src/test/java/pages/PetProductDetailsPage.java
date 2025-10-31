package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tcs.webdriver.BasePage;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class PetProductDetailsPage extends BasePage {
    Logger LOGGER= LogManager.getLogger(PetProductDetailsPage.class);
    WebDriver driver;
    By productTitle = By.xpath("//h1[contains(@class,'product-single__title')]");
    By productPrice = By.xpath("//span[contains(@class,'product__price') and contains(@id,'ProductPrice')]");
    By addToCartButton = By.xpath("//span[contains(@id,'AddToCartText')]");

    public PetProductDetailsPage(WebDriver driver)
    {
        this.driver=driver;
    }
    public boolean isSelectedProductDetailsDisplayed(String expectedProductTitle, String expectedPrice)
    {
        boolean isProductDetailsDisplayed=false;
        if(getText(productTitle).equals(expectedProductTitle) && getText(productPrice).equals(expectedPrice) && isDisplayed(addToCartButton))
        {
            isProductDetailsDisplayed=true;
        }
        return isProductDetailsDisplayed;
    }

    public boolean isSelectedProductNameMatched(String expectedProductName)
    {
        boolean isProductDetailsMatched=false;
        if(getText(productTitle).equals(expectedProductName))
        {
            isProductDetailsMatched=true;
        }
        return isProductDetailsMatched;
    }

    public Map<String,String> getProductDetails()
    {
        Map<String,String> productDetails=new TreeMap<>();
        productDetails.put("product name",getText(productTitle));
        productDetails.put("product price",getText(productPrice));
        productDetails.put("product quantity","1");
        return productDetails;
    }

    public void clickOnAddToCartButton()
    {
        LOGGER.info("Clicking on add to cart button to add product into the cart");
        click(addToCartButton);
    }




}
