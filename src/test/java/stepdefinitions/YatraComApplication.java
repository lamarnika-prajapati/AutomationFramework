package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tcs.webdriver.WaitHelper;
import org.testng.Assert;
import pages.FlightDashboardPage;
import pages.FlightDetails;
import pages.YatraComSearchHomePage;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.tcs.webdriver.DriverManager.getDriver;

public class YatraComApplication {
    WebDriver driver = getDriver();
    YatraComSearchHomePage yatraComSearchHomePage = new YatraComSearchHomePage(driver);
    FlightDashboardPage flightDashboardPage = new FlightDashboardPage(driver);
    List<FlightDetails> allFlightList=new ArrayList<>();

    @Given("Given traveller is on the YatraCom site page")
    public void givenTravelIsOnTheYatraComSitePage() {
        driver.get("https://www.yatra.com/");
    }

    @When("traveller enter flight details {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} and search the flight")
    public void userEnterFlightDetailsAndSearchTheFlight(String travelType, String from, String to, String departureDate, String adults, String children, String infant, String travelClass, String specialFare, String nonStop) {
        boolean isNonStopFlight = false;
        yatraComSearchHomePage.closeLoginPopup();
        yatraComSearchHomePage.selectTypeOfTrip(travelType);
        yatraComSearchHomePage.clickDepartureFrom();
        yatraComSearchHomePage.enterDepartureFromCity(from);
        yatraComSearchHomePage.selectDepartureFromCity(from);

        yatraComSearchHomePage.clickGoingTo();
        yatraComSearchHomePage.enterGoingToCity(to);
        yatraComSearchHomePage.selectGoingToCity(to);

        yatraComSearchHomePage.clickOnDepartureDate();
        yatraComSearchHomePage.selectDepartureDate(departureDate);

        yatraComSearchHomePage.clickOnTravelAndClass();
        yatraComSearchHomePage.selectAdults(adults);
        yatraComSearchHomePage.selectChildren(children);
        yatraComSearchHomePage.selectInfants(infant);
        yatraComSearchHomePage.selectTravelClass(travelClass);
        yatraComSearchHomePage.clickOnDoneAfterSelectingTravelAndClass();
        yatraComSearchHomePage.selectSpecialFare(specialFare);
        isNonStopFlight = nonStop.equals("Yes");
        yatraComSearchHomePage.selectNonStopFlights(isNonStopFlight);


    }

    @And("traveller click to search button")
    public void userClickToSearchButton() {
        yatraComSearchHomePage.clickOnSearchButton();
    }

    @Then("the flights list should be visible if flights are available")
    public void theFlightsListShouldBeVisibleIfFlightsAreAvailable() {
        boolean isDashboardPageURLMatched = flightDashboardPage.dashboardAfterSearchingFlight();
        WaitHelper.pause(5);
        allFlightList=flightDashboardPage.captureFlightSearchResults();
        Assert.assertTrue(isDashboardPageURLMatched, "flights details are not displayed");
        System.out.println("Flights details are successfully displayed");

    }


    @When("traveller select adult under travellers details field")
    public void travelSelectUnderTravellersDetailsField(DataTable dataTable) {
        Map<String, String> adultNumber = dataTable.asMap(String.class, String.class);
        yatraComSearchHomePage.closeLoginPopup();
        yatraComSearchHomePage.clickOnTravelAndClass();
        yatraComSearchHomePage.selectAdults(adultNumber.get("Adult"));

    }

    @Then("child and infant select lists should be updated based on the selected adult")
    public void childAndInfantSelectListsShouldBeUpdatedBasedOnTheSelectedAdult(DataTable dataTable) {
        Map<String, String> childAndInfantInputs = dataTable.asMap(String.class, String.class);
        boolean isChildListMatchedWithChildInput = yatraComSearchHomePage.validateChildDependencyOnAdult(childAndInfantInputs.get("Child"));
        Assert.assertTrue(isChildListMatchedWithChildInput, "Child List is not displaying numbers as an expected");
        System.out.println("Child dependencies on Adult is working as an expected");

        boolean isInfantListMatchedWithInfantInput = yatraComSearchHomePage.validateInfantDependencyOnAdult(childAndInfantInputs.get("Infant"));
        Assert.assertTrue(isInfantListMatchedWithInfantInput, "Infant List is not displaying numbers as an expected");
        System.out.println("Infant dependencies on Adult is working as an expected");

    }

    @And("traveller click on Depart filter")
    public void travellerClickOnDepartFilter() {
        flightDashboardPage.clickOnDepartFilter();
    }

    @Then("the flights list should be visible based on depart filter {string}")
    public void theFlightsListShouldBeVisibleBasedOnDepartFilter(String departureFrom) {
        WaitHelper.pause(5);
        boolean isFlightDetailsFiltered=flightDashboardPage.validateDepartFilterFunctionality(allFlightList,departureFrom);
        Assert.assertTrue(isFlightDetailsFiltered,"The flights are not filtered on Depart filter option");
        System.out.println("Flights are successfully filtered");


    }

    @And("traveller click on Arrive filter")
    public void travellerClickOnArriveFilter() {
        flightDashboardPage.clickOnArriveFilter();
    }

    @Then("the flights list should be visible based on Arrive filter {string}")
    public void theFlightsListShouldBeVisibleBasedOnArriveFilter(String departureFrom) {
        WaitHelper.pause(5);
        boolean isFlightDetailsFiltered=flightDashboardPage.validateArriveFilterFunctionality(allFlightList, departureFrom);
        Assert.assertTrue(isFlightDetailsFiltered,"The flights are not filtered on Arrive filter option");
        System.out.println("Flights are successfully filtered based on Arrive filter");

    }

    @And("traveller click on Duration filter")
    public void travellerClickOnDurationFilter() {
       flightDashboardPage.clickOnDurationFilter();
    }

    @Then("the flights list should be visible based on Duration filter {string}")
    public void theFlightsListShouldBeVisibleBasedOnDurationFilter(String departureFrom) {
        WaitHelper.pause(5);
        boolean isFlightDetailsFiltered=flightDashboardPage.validateDurationFilterFunctionality(allFlightList, departureFrom);
        Assert.assertTrue(isFlightDetailsFiltered,"The flights are not filtered on Duration filter option");
        System.out.println("Flights are successfully filtered based on Duration filter");

    }


    @And("traveller click on Price filter")
    public void travellerClickOnPriceFilter() {
        flightDashboardPage.clickOnPricePerAdultFilter();
    }

    @Then("the flights list should be visible based on Price {string}")
    public void theFlightsListShouldBeVisibleBasedOnPrice(String departureFrom) {
        WaitHelper.pause(5);
        boolean isFlightDetailsFiltered=flightDashboardPage.validatePriceFilterFunctionality(allFlightList, departureFrom);
        Assert.assertTrue(isFlightDetailsFiltered,"The flights are not filtered on Price filter option");
        System.out.println("Flights are successfully filtered based on Price filter");

    }

    @And("traveller click on Depart filter to see flights in descending order")
    public void travellerClickOnDepartFilterToSeeFlightsInDescendingOrder() {
        flightDashboardPage.clickOnReverseDepartFilter();
    }

    @Then("the flights list should be visible in descending order based on depart filter {string}")
    public void theFlightsListShouldBeVisibleInDescendingOrderBasedOnDepartFilter(String departureFrom) {
        WaitHelper.pause(5);
        boolean isFlightDetailsFiltered=flightDashboardPage.validateDepartReverseFilterFunctionality(allFlightList,departureFrom);
        Assert.assertTrue(isFlightDetailsFiltered,"The flights are not filtered in descending order on Depart reverse filter option");
        System.out.println("Flights are successfully filtered");
    }


}
