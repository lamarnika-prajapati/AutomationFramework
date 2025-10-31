/*
package stepdefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.tcs.readers.ConfigReader;
import org.testng.Assert;
import pages.PetCartPage;
import pages.PetProductDetailsPage;
import pages.PetStoreComHomePage;
import pages.PetStoreLoginPage;

import static org.tcs.webdriver.DriverManager.getDriver;

public class PetStoreComSteps {
    WebDriver driver = getDriver();
    PetStoreLoginPage petStoreLoginPage = new PetStoreLoginPage(driver);
    PetStoreComHomePage petStoreComHomePage = new PetStoreComHomePage(driver);
    PetProductDetailsPage petProductDetailsPage = new PetProductDetailsPage(driver);
    PetCartPage petCartPage = new PetCartPage(driver);


    @Given("user is on home page of petStore")
    public void userisonhomepageofpetStore() {
        driver.get(ConfigReader.getProperty("application_URL"));


    }

    @When("user enter {string}, {string} and click on login button")
    public void userenterusrnameandpasswordandclickonloginbutton(String username, String password) {
        petStoreLoginPage.clickOnLoginIcon();
        petStoreLoginPage.enterUserName(username);
        petStoreLoginPage.enterPassword(password);
        petStoreLoginPage.clickOnSignInButton();
    }

    @Then("user is logged in successfully and navigated to Home page")
    public void userisloggedinsucessfullyandnavigatedtoHomepage() {
        Assert.assertTrue(petStoreComHomePage.isLogoutButtonDisplayed(), "User is not logged in successfully");
        System.out.println("User is logged in successfully");
    }


    @When("user clicks on search bar and search a pet product {string}")
    public void userclicksonsearchbarandsearchapetproduct(String searchProduct) {
        petStoreComHomePage.clickOnSearchBarIcon();
        petStoreComHomePage.enterProductInSearchBox(searchProduct);
    }

    @And("choose a specific pet product {string} from the search results")
    public void chooseaspecificpetproductfromthesearchresults(String productTitle) {
        petStoreComHomePage.clickOnProductToViewProductDetails(productTitle);

    }

    @Then("product details page should display product name {string}, {string} and add to cart button")
    public void productdetailspageshoulddispalyproductnamepriceandaddtocartbutton(String productTitle, String price) {
        Assert.assertTrue(petProductDetailsPage.isSelectedProductDetailsDisplayed(productTitle, price), "The details are not displayed for the selected product");
        System.out.println("the product details are successfully displayed");
    }

    @When("click on add to cart button")
    public void clickonaddtocartbutton() {
        petProductDetailsPage.clickOnAddToCartButton();

    }

    @And("navigate to the cart page")
    public void navigatetothecartpage() {


    }

    @Then("the selected product should appear in the cart page with correct name {string} , price {string} and quantity {string}")
    public void theselectedproductshouldappearinthecartpagewithcorrectnamepriceandquantity(String productTitle, String prize, String defaultQuantity) {
        Assert.assertTrue(petCartPage.isAddedProductToCartDisplayedInCartPage(productTitle, prize, defaultQuantity), "The newly added product is not displayed in the cart");
        System.out.println("The newly added  product is successfully displayed with the correct details in the cart");
    }

    @When("user increases the quantity {string}, {string} of the product")
    public void userincreasesthequantityoftheproduct(String productTitle, String increaseQuantity) {
                petCartPage.increaseQuantityOfProduct(productTitle, increaseQuantity);
    }

    @And("the total price should equals quantity multiplied by unit price")
    public void thetotalpriceshouldequalsquantitymultipliedbyunitprice() {
        if(!petCartPage.isCartEmpty())
        {
            Assert.assertTrue(petCartPage.isSubTotalCalculatedCorrect(),"The subtotal is not correctly calculated ");
            System.out.println("The sub total is correctly calculated");
        }
        else
        {
            System.out.println("Cart is empty");
        }

    }

    @When("user removes one product {string} from the cart")
    public void userRemovesOneProductFromTheCart(String productTitle) {
        petCartPage.removeOneProductFromTheCart(productTitle);

    }

    @Then("user validates the final price and quantity of products 2 in the cart")
    public void uservalidatesthefinalpriceandquantityofproducts2inthecart() {

    }

    @Then("user validates the final price and quantity of products 1 in the cart")
    public void uservalidatesthefinalpriceandquantityofproducts1inthecart() {

    }

    @When("user removes product from the cart")
    public void userremovesproductfromthecart() {

    }

    @Then("the cart should be empty and shows {string}")
    public void thecartshouldbeemptyandshows() {

    }


    @And("close the cart")
    public void closeTheCart() {
        petCartPage.clickOnCloseCartButton();
    }


}
*/
