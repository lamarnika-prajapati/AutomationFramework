package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.tcs.readers.ConfigReader;
import org.testng.Assert;
import pages.PetCartPage;
import pages.PetProductDetailsPage;
import pages.PetStoreComHomePage;
import pages.PetStoreLoginPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.tcs.webdriver.DriverManager.getDriver;

public class PetStoreComCopySteps {
    WebDriver driver = getDriver();
    PetStoreLoginPage petStoreLoginPage = new PetStoreLoginPage(driver);
    PetStoreComHomePage petStoreComHomePage = new PetStoreComHomePage(driver);
    PetProductDetailsPage petProductDetailsPage = new PetProductDetailsPage(driver);
    PetCartPage petCartPage = new PetCartPage(driver);
    List<Map<String, String>> listOfAllAddedProductsInTheCart = new ArrayList<>();

    @Given("user is on home page of petStore")
    public void userisonhomepageofpetStore() {
        driver.get(ConfigReader.getProperty("application_URL"));


    }

    @When("user search a pet product and add the following product to the cart")
    public void userSearchAPetProductAndAddTheFollowingProductToTheCart(DataTable dataTable) {
        System.out.println("user search a pet product and add the following product to the cart");
        List<Map<String, String>> listOfProductsToBeAddedInTheCart = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> product : listOfProductsToBeAddedInTheCart) {
            petStoreComHomePage.clickOnSearchBarIcon();
            petStoreComHomePage.enterProductInSearchBox(product.get("search product"));
            petStoreComHomePage.clickOnProductToViewProductDetails(product.get("product name"));
            Assert.assertTrue(petProductDetailsPage.isSelectedProductNameMatched(product.get("product name")), "The product details are incorrect for the clicked product");
            System.out.println("The product details are correct and successfully displayed");
            listOfAllAddedProductsInTheCart.add(petProductDetailsPage.getProductDetails());
            petProductDetailsPage.clickOnAddToCartButton();
            petCartPage.clickOnCloseCartButton();
        }

    }

    @Then("all added products should appear in the cart page with correct details")
    public void allAddedProductsShouldAppearInTheCartPageWithCorrectDetails() {
        System.out.println("all added products should appear in the cart page with correct detail");
        petStoreComHomePage.clickOnCartLink();
        Assert.assertTrue(petCartPage.isAllAddedProductToCartDisplayedCorrectlyInCartPage(listOfAllAddedProductsInTheCart), "The added product is not correctly displayed in the cart");
        System.out.println("all added  product is successfully displayed with the correct details in the cart");

    }

//    @When("user increases the quantity of the following product")
//    public void userincreasesthequantityoftheproduct(DataTable dataTable) {
//        List<Map<String, String>> listOfProductsToBeIncreasedInTheCart = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> product : listOfProductsToBeIncreasedInTheCart) {
//            petCartPage.increaseQuantityOfProduct(product.get("product name"), product.get("increase quantity by"));
//        }
//    }

    @And("the total price should equals quantity multiplied by unit price")
    public void thetotalpriceshouldequalsquantitymultipliedbyunitprice() {
        System.out.println("the total price should equals quantity multiplied by unit price");
        Assert.assertTrue(petCartPage.isSubTotalCalculatedCorrect(), "The subtotal is not correctly calculated ");
        System.out.println("The sub total is correctly calculated");
    }

    @And("the sub total should be updated on increasing of the product by user")
    public void theSubTotalShouldBeUpdatedOnIncreasingOfTheProductByUser(DataTable dataTable) {
        System.out.println("the sub total should be updated on increasing of the product by user");
        List<Map<String, String>> listOfProductsToBeIncreasedInTheCart = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> product : listOfProductsToBeIncreasedInTheCart) {
            Assert.assertTrue(petCartPage.updatedSubTotalAfterIncreasingQuantityOfProduct(product.get("product name"), product.get("increase quantity by")), "The sub total is not updated on increasing the product");
        }
        System.out.println("The sub Total is updated as an expected");

    }

    @And("the sub total should be updated on decreasing of the product by user")
    public void theSubTotalShouldBeUpdatedOnDecreasingOfTheProductByUser(DataTable dataTable) {
        System.out.println("the sub total should be updated on decreasing of the product by user");
        List<Map<String, String>> listOfProductsToBeDecreasedInTheCart = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> product : listOfProductsToBeDecreasedInTheCart) {
            Assert.assertTrue(petCartPage.updatedSubTotalAfterDecreasingQuantityOfProduct(product.get("product name"), product.get("decrease quantity by")), "The sub total is not updated on decreasing the product");
        }
        System.out.println("The sub Total is updated as an expected");
    }

    @And("the sub total should be updated on removal of product {string}")
    public void theSubTotalShouldBeUpdatedOnRemovalOfTheFollowingProduct(String productName) {
        System.out.println("the sub total should be updated on removal of product");
        Assert.assertTrue(petCartPage.isSubTotalUpdatedAfterRemovalOfElement(productName), "The product is not successfully removed and the sub total is not correctly updated");
        System.out.println("The product is successfully removed and the sub total is correctly updated");
    }


    @When("user removes all the products from the cart")
    public void userRemovesAllTheProductsFromTheCart() {
        System.out.println("user removes all the products from the cart");
        petCartPage.RemovingAllProductsFromTheCart();
    }


    @Then("the cart should be empty and shows {string}")
    public void theCartShouldBeEmptyAndShows(String cartEmptyMessage) {
        System.out.println("the cart should be empty and shows");
        Assert.assertTrue(petCartPage.isCartEmpty(cartEmptyMessage),"The cart is not empty");
        System.out.println("The cart is empty");
    }
}
