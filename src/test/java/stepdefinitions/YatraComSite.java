package stepdefinitions;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.YatraComSearchHomePage;

import java.util.List;
import java.util.Map;

import static org.tcs.webdriver.DriverManager.getDriver;

public class YatraComSite {
   /* WebDriver driver = getDriver();
    YatraComSearchHomePage yatraComSearchHomePage = new YatraComSearchHomePage(driver);

    @Given("user is on the YatraCom site page")
    public void userIsOnTheMakeMyTripSitePage() {
        driver.get("https://www.yatra.com/");
    }

    @When("user enter flight details and search the flight")
    public void userSearchTheFlight(DataTable dataTable) {
        Map<String, String> flightDetails = dataTable.asMap(String.class, String.class);
        yatraComSearchHomePage.closeLoginPopup();
        yatraComSearchHomePage.selectTypeOfTrip(flightDetails.get("Travel type"));
        yatraComSearchHomePage.clickDepartureFrom();
        yatraComSearchHomePage.enterDepartureFromCity(flightDetails.get("From"));
        yatraComSearchHomePage.selectDepartureFromCity(flightDetails.get("From"));

        yatraComSearchHomePage.clickGoingTo();
        yatraComSearchHomePage.enterGoingToCity(flightDetails.get("To"));
        yatraComSearchHomePage.selectGoingToCity(flightDetails.get("To"));

      //  yatraComSearchHomePage.clickGoingTo();
 //       yatraComSearchHomePage.enterGoingToCity(flightDetails.get("To"));
//        yatraComSearchHomePage.selectGoingToCity(flightDetails.get("To"));

    }

    @And("user click to search button")
    public void userClickToSearchButton() {
        System.out.println("click search button");
    }

    @Then("the flights list should be visible if flights are available")
    public void theFlightsListShouldBeVisibleIfFlightsAreAvailable() {
        System.out.println("click search button");

    }*/
}
