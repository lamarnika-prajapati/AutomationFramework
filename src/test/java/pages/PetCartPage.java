package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.tcs.webdriver.BasePage;

import java.util.*;

public class PetCartPage extends BasePage {
    Logger LOGGER = LogManager.getLogger(PetCartPage.class);
    WebDriver driver;
    By productTitle = By.xpath("//a[@class='ajaxcart__product-name']");
    // By productQuantity = By.xpath("//a[@class=\"ajaxcart__product-name\"]/parent::div/following-sibling::div//div[@class='ajaxcart__quantity']//input[@type='text']");
    By productQuantity = By.xpath("./parent::div/following-sibling::div//div[@class='ajaxcart__quantity']//input[@type='text']");
    // By decreaseQuantity = By.xpath("//a[@class=\"ajaxcart__product-name\"]/parent::div/following-sibling::div//div[@class='ajaxcart__quantity']//button[contains(@aria-label,'Reduce item quantity')]");
    By decreaseQuantity = By.xpath("./parent::div/following-sibling::div//div[@class='ajaxcart__quantity']//button[contains(@aria-label,'Reduce item quantity')]");
    // By increaseQuantity = By.xpath("//a[@class=\"ajaxcart__product-name\"]/parent::div/following-sibling::div//div[@class='ajaxcart__quantity']//button[contains(@aria-label,'Increase item quantity')]");
    By increaseQuantity = By.xpath("./parent::div/following-sibling::div//div[@class='ajaxcart__quantity']//button[contains(@aria-label,'Increase item quantity')]");
    //   By productPrice = By.xpath("//a[@class=\"ajaxcart__product-name\"]/parent::div/following-sibling::div//div//span[@class='ajaxcart__price']");
    By productPrice = By.xpath("./parent::div/following-sibling::div//div//span[@class='ajaxcart__price']");
    By closeCartButton = By.xpath("//span[text()='Close cart']/parent::button");
    By signInPopup = By.xpath("//div[@class=\"modal__inner\"]//parent::button[contains(@class,'modal__close')]");
    By cartButton = By.xpath("//span[@class='cart-link']");
    By subTotalPrice = By.xpath("//p[@class='ajaxcart__price']");
    By emptyCartMessage = By.xpath("//p[text()='Your cart is currently empty.']");
   // List<WebElement> listOfProductsFromCart = waitForAllElementsVisible(productTitle);

    public PetCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getAllProductsFromCart()
    {
        return waitForAllElementsVisible(productTitle);
    }

    public String getProductName(WebElement product)
    {
        return product.getText();
    }
    public String getProductPrice(WebElement product)
    {
        return product.findElement(productPrice).getText();
    }
    public String getProductQuantity(WebElement product)
    {
        return product.findElement(productQuantity).getAttribute("value");
    }

    public float getPriceInNumber(String price)
    {
        return Float.parseFloat(price.replace("$",""));
    }
    public String getSubTotal()
    {
        return getText(subTotalPrice);
    }

    public int getQuantityInNumber(String quantity)
    {
        return Integer.parseInt(quantity);
    }

    public float getSubTotalInNumber(String subTotal)
    {
        return Float.parseFloat(subTotal.replace("$",""));
    }

    public void clickOnIncreaseQualityButton()
    {

    }
    public void clickOnDecreaseQualityButton()
    {

    }

    public boolean isAddedProductToCartDisplayedInCartPage(String expectedProductTitle, String expectedPrice, String defaultQuantity) {
        boolean isProductDetailsDisplayed = false;
      //  List<WebElement> listOfProductsFromCart = waitForAllElementsVisible(productTitle);
        LOGGER.info("total products added in the Cart {}", getAllProductsFromCart().size());

        for (WebElement product : getAllProductsFromCart()) {
            if (product.getText().equalsIgnoreCase(expectedProductTitle)) {
                if (product.findElement(productPrice).getText().equals(expectedPrice)
                        && product.findElement(productQuantity).getAttribute("value").equals(defaultQuantity)) {
                    isProductDetailsDisplayed = true;
                    break;

                }
            }
        }
        return isProductDetailsDisplayed;
    }

    public boolean isAllAddedProductToCartDisplayedCorrectlyInCartPage(List<Map<String, String>> listOfAllAddedProductsInTheCart) {
        boolean isAllProductDetailsDisplayed = false;
     //   List<WebElement> listOfAllProductsFromCart = waitForAllElementsVisible(productTitle);
        List<Map<String, String>> listOfAllProductDisplayedInTheCart = new ArrayList<>();


        LOGGER.info("total products added in the Cart {}", getAllProductsFromCart().size());

        for (WebElement product : getAllProductsFromCart()) {
            Map<String, String> productDisplayedInTheCart = new LinkedHashMap<>();
            productDisplayedInTheCart.put("product name", getProductName(product).toUpperCase());
            productDisplayedInTheCart.put("product price", getProductPrice(product));
            productDisplayedInTheCart.put("product quantity", getProductQuantity(product));
            listOfAllProductDisplayedInTheCart.add(productDisplayedInTheCart);
        }

        System.out.println("Printing products displayed in the cart page");
        Collections.reverse(listOfAllProductDisplayedInTheCart);
        for (Map<String, String> productFromCart : listOfAllProductDisplayedInTheCart) {
            System.out.println(productFromCart.get("product name") + " " + productFromCart.get("product price") + " " + productFromCart.get("product quantity"));

        }

        System.out.println("Printing products added to the cart");
        for (Map<String, String> product : listOfAllAddedProductsInTheCart) {
            System.out.println(product.get("product name") + " " + product.get("product price") + " " + product.get("product quantity"));
        }


        if (listOfAllProductDisplayedInTheCart.equals(listOfAllAddedProductsInTheCart)) {
            System.out.println("added product matched with cart product");
            isAllProductDetailsDisplayed = true;
        }
        return isAllProductDetailsDisplayed;
    }

    public void clickOnCloseCartButton() {
        LOGGER.info("Clicking on close cart button to close the cart");
        click(closeCartButton);
    }

    public void handleSignInModel()
    {
        if (isElementPresent(signInPopup, 3)) {
            click(signInPopup);
            clickOnCartLink();
        }
    }

    public boolean updatedSubTotalAfterIncreasingQuantityOfProduct(String productTitle, String increaseQuantity) {
       // List<WebElement> listOfProductsFromCart = waitForAllElementsVisible(this.productTitle);
        Float currentSubTotal = 0.0f;
        boolean isSubTotalUpdatedAfterIncreasingProduct = false;
        for (int i = 0; i < getAllProductsFromCart().size(); i++) {
            WebElement product = driver.findElements(this.productTitle).get(i);
            if (product.getText().equalsIgnoreCase(productTitle)) {
               /* Float price = Float.parseFloat(product.findElement(productPrice).getText().replace("$", ""));
                currentSubTotal = Float.parseFloat(getText(subTotalPrice).replace("$", ""));*/
                Float price = getPriceInNumber(getProductPrice(product));
                currentSubTotal = getSubTotalInNumber(getSubTotal());
                for (int j = 1; j <= getQuantityInNumber(increaseQuantity); j++) {

                    String quantity = getProductQuantity(product);

                    LOGGER.info("current quantity of product {}", quantity);
                    try {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product.findElement(this.increaseQuantity));
                       /* if (isElementPresent(signInPopup, 3)) {
                            click(signInPopup);
                            clickOnCartLink();
                        }*/
                        handleSignInModel();
                        getWait().until(ExpectedConditions.elementToBeClickable(product.findElement(this.increaseQuantity))).click();
                    } catch (StaleElementReferenceException e) {
                        product = driver.findElements(this.productTitle).get(i);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product.findElement(this.increaseQuantity));
                        getWait().until(ExpectedConditions.elementToBeClickable(product.findElement(this.increaseQuantity))).click();
                    }
                    pause(3);
                    try {
                        getWait().until(ExpectedConditions.not(ExpectedConditions.attributeToBe(product.findElement(this.productQuantity), "value", quantity)));
                        currentSubTotal = currentSubTotal + price;

                    } catch (StaleElementReferenceException e) {
                        product = driver.findElements(this.productTitle).get(i);
                        getWait().until(ExpectedConditions.not(ExpectedConditions.attributeToBe(product.findElement(this.productQuantity), "value", quantity)));
                        currentSubTotal = currentSubTotal + price;
                    }
                    System.out.println("increased quantity is: " + (1 + Integer.parseInt(quantity)));
                    System.out.println("Updated sub total: " + currentSubTotal);
                }
                LOGGER.info("Quantity of product {} - {}", product.getText(), getProductQuantity(product));
                break;
            }
        }
        if (currentSubTotal == getSubTotalInNumber(getSubTotal())) {
            LOGGER.info("The updated sub total {} is matched with current sub total {}", currentSubTotal, getSubTotalInNumber(getSubTotal()));
            isSubTotalUpdatedAfterIncreasingProduct = true;
        }
        return isSubTotalUpdatedAfterIncreasingProduct;
    }

    public boolean updatedSubTotalAfterDecreasingQuantityOfProduct(String productTitle, String decreaseQuantity) {
       // List<WebElement> listOfProductsFromCart = waitForAllElementsVisible(this.productTitle);
        Float currentSubTotal = 0.0f;
        boolean isSubTotalUpdatedAfterDecreaseProduct = false;
        for (int i = 0; i < getAllProductsFromCart().size(); i++) {
            WebElement product = driver.findElements(this.productTitle).get(i);
            if (product.getText().equalsIgnoreCase(productTitle)) {
                Float price = getPriceInNumber(getProductPrice(product));
                currentSubTotal = getSubTotalInNumber(getSubTotal());
                for (int j = 1; j <= getQuantityInNumber(decreaseQuantity); j++) {

                    String quantity = getProductQuantity(product);
                    LOGGER.info("current quantity of product {}", quantity);
                    try {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product.findElement(this.decreaseQuantity));
                        if (isElementPresent(signInPopup, 3)) {
                            click(signInPopup);
                            clickOnCartLink();
                        }
                        getWait().until(ExpectedConditions.elementToBeClickable(product.findElement(this.decreaseQuantity))).click();
                    } catch (StaleElementReferenceException e) {
                        product = driver.findElements(this.productTitle).get(i);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product.findElement(this.decreaseQuantity));
                        getWait().until(ExpectedConditions.elementToBeClickable(product.findElement(this.decreaseQuantity))).click();
                    }
                    pause(3);
                    try {
                        getWait().until(ExpectedConditions.not(ExpectedConditions.attributeToBe(product.findElement(this.productQuantity), "value", quantity)));
                        currentSubTotal = currentSubTotal - price;

                    } catch (StaleElementReferenceException e) {
                        product = driver.findElements(this.productTitle).get(i);
                        getWait().until(ExpectedConditions.not(ExpectedConditions.attributeToBe(product.findElement(this.productQuantity), "value", quantity)));
                        currentSubTotal = currentSubTotal - price;
                    }
                    System.out.println("decreased quantity is: " + (Integer.parseInt(quantity) - 1));
                    System.out.println("Updated sub total: " + currentSubTotal);
                }
                break;
            }
        }
        if (currentSubTotal == getSubTotalInNumber(getSubTotal())) {
            isSubTotalUpdatedAfterDecreaseProduct = true;
        }
        return isSubTotalUpdatedAfterDecreaseProduct;
    }

    public void removeProductFromTheCart(String productName) {
       // List<WebElement> listOfProductsFromCart = waitForAllElementsVisible(this.productTitle);
        for (int i = 0; i < getAllProductsFromCart().size(); i++) {
            WebElement product = driver.findElements(this.productTitle).get(i);
            if (product.getText().equalsIgnoreCase(productName)) {
                String quantity = getProductQuantity(product);
                while (Integer.parseInt(quantity) > 0) {
                    quantity = getProductQuantity(product);

                    LOGGER.info("current quantity of product {}", quantity);
                    try {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product.findElement(this.decreaseQuantity));
                        if (isElementPresent(signInPopup, 3)) {
                            click(signInPopup);
                            clickOnCartLink();
                        }
                        getWait().until(ExpectedConditions.elementToBeClickable(product.findElement(this.decreaseQuantity))).click();
                    } catch (StaleElementReferenceException e) {
                        product = driver.findElements(this.productTitle).get(i);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product.findElement(this.decreaseQuantity));
                        getWait().until(ExpectedConditions.elementToBeClickable(product.findElement(this.decreaseQuantity))).click();
                    }
                    pause(3);
                    if (Integer.parseInt(quantity) - 1 != 0) {
                        try {
                            getWait().until(ExpectedConditions.not(ExpectedConditions.attributeToBe(product.findElement(this.productQuantity), "value", quantity)));
                        } catch (StaleElementReferenceException e) {
                            product = driver.findElements(this.productTitle).get(i);
                            getWait().until(ExpectedConditions.not(ExpectedConditions.attributeToBe(product.findElement(this.productQuantity), "value", quantity)));
                        }
                    } else {
                        System.out.println("The product has been removed");
                    }
                    System.out.println("decreased quantity is: " + (Integer.parseInt(quantity) - 1));
                    quantity = String.valueOf(Integer.parseInt(quantity) - 1);

                }
                break;
            }
        }
    }

    public boolean isSubTotalUpdatedAfterRemovalOfElement(String productName) {
        boolean isSubTotalUpdatedAfterRemoval = false;
        float currentSubTotal = getSubTotalInNumber(getSubTotal());
        float price = 0;
      //  List<WebElement> listOfProductsFromCart = waitForAllElementsVisible(this.productTitle);
        for (int i = 0; i < getAllProductsFromCart().size(); i++) {
            WebElement product = driver.findElements(this.productTitle).get(i);
            if (product.getText().equalsIgnoreCase(productName)) {
                price = getPriceInNumber(getProductPrice(product));
            }
        }
        removeProductFromTheCart(productName);

        float updatedSubTotal = currentSubTotal - price;
        if (updatedSubTotal == getSubTotalInNumber(getSubTotal())) ;
        {
            isSubTotalUpdatedAfterRemoval = true;
        }
        return isSubTotalUpdatedAfterRemoval;
    }

    public void RemovingAllProductsFromTheCart() {
       // List<WebElement> listOfProductsFromCart = waitForAllElementsVisible(this.productTitle);

        int numberOfProduct=getAllProductsFromCart().size();
        while (numberOfProduct!=0) {
            WebElement product = driver.findElements(this.productTitle).get(0);
            System.out.println("current size: "+getAllProductsFromCart().size()+" the product to be removed: "+product.getText());
            removeProductFromTheCart(product.getText());
            numberOfProduct--;
        }
    }

    private void clickOnCartLink() {
        click(cartButton);
    }

    public boolean isCartEmpty(String cartEmptyMessage) {
        return getText(emptyCartMessage).equals(cartEmptyMessage);
    }

    /*public boolean isSubTotalCalculatedCorrect() {
        boolean isCalculateTotalOfProductCorrect = false;
        List<WebElement> listOfProductsFromCart = waitForAllElementsVisible(productTitle);
        LOGGER.info("total products from the Cart {}", listOfProductsFromCart.size());
        int sum = 0;
        for (int i = 0; i < listOfProductsFromCart.size(); i++) {
            String productPriceText = (listOfProductsFromCart.get(i).findElement(productPrice).getText()).replace("$", "").replaceAll("\\..*", "");
            int actualPrice = Integer.parseInt(productPriceText);
            sum = sum + Integer.parseInt(listOfProductsFromCart.get(i).findElement(productQuantity).getAttribute("value"))
                    * actualPrice;

        }
        if (sum == Integer.parseInt(getText(subTotalPrice).replace("$", "").replaceAll("\\..*", ""))) {
            isCalculateTotalOfProductCorrect = true;
            LOGGER.info("Total price of all cart products {} ", sum);
        }

        return isCalculateTotalOfProductCorrect;
    }*/

    public boolean isSubTotalCalculatedCorrect() {
        boolean isCalculateTotalOfProductCorrect = false;

        if (isElementPresent(signInPopup, 3)) {
            click(signInPopup);
            clickOnCartLink();
        }
       // List<WebElement> listOfProductsFromCart = waitForAllElementsVisible(productTitle);
        LOGGER.info("total products from the Cart {}", getAllProductsFromCart().size());
        float sum = 0;
        for (int i = 0; i < getAllProductsFromCart().size(); i++) {
            /*String productPriceText = (getAllProductsFromCart().get(i).findElement(productPrice).getText()).replace("$", "");
            float actualPrice = Float.parseFloat(productPriceText);
            sum = sum + Integer.parseInt(getAllProductsFromCart().get(i).findElement(productQuantity).getAttribute("value"))
                    * actualPrice;*/

            float productPrice = getPriceInNumber((getProductPrice(getAllProductsFromCart().get(i))));
            int productQuantity= getQuantityInNumber(getProductQuantity(getAllProductsFromCart().get(i)));
            sum = sum + productPrice * productQuantity;

        }
        if (sum == getSubTotalInNumber(getSubTotal())) {
            isCalculateTotalOfProductCorrect = true;
            LOGGER.info("Total price of all cart products {} ", sum);
        }

        return isCalculateTotalOfProductCorrect;
    }

    public void removeOneProductFromTheCart(String productTitle) {
      //  List<WebElement> listOfProductsFromCart = waitForAllElementsVisible(this.productTitle);
        LOGGER.info("Total products from the Cart {}", getAllProductsFromCart().size());
        for (int i = 0; i < getAllProductsFromCart().size(); i++) {
            WebElement product = driver.findElements(this.productTitle).get(i);
            if (product.getText().equalsIgnoreCase(productTitle)) {
                int currentQuantity = Integer.parseInt(product.findElement(productQuantity).getAttribute("value"));
                for (int j = currentQuantity; j > 0; j--) {

                    String quantity = product.findElement(productQuantity).getAttribute("value");
                    LOGGER.info("current quantity of product {}", quantity);
                    try {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product.findElement(this.decreaseQuantity));
                        if (isElementPresent(signInPopup, 3)) {
                            click(signInPopup);
                            clickOnCartLink();
                        }
                        getWait().until(ExpectedConditions.elementToBeClickable(product.findElement(this.decreaseQuantity))).click();
                    } catch (StaleElementReferenceException e) {
                        product = driver.findElements(this.productTitle).get(i);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product.findElement(this.decreaseQuantity));
                        getWait().until(ExpectedConditions.elementToBeClickable(product.findElement(this.decreaseQuantity))).click();
                    }
                    pause(3);
                    try {
                        getWait().until(ExpectedConditions.not(ExpectedConditions.attributeToBe(product.findElement(this.productQuantity), "value", quantity)));

                    } catch (StaleElementReferenceException e) {
                        product = driver.findElements(this.productTitle).get(i);
                        getWait().until(ExpectedConditions.not(ExpectedConditions.attributeToBe(product.findElement(this.productQuantity), "value", quantity)));
                    }
                    System.out.println("decreased quantity is: " + (Integer.parseInt(quantity) - 1));
                }
                LOGGER.info("The product is removed {}", product.getText());
                break;
            }
        }
    }
}
