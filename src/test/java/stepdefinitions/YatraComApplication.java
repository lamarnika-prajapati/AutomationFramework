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
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.tcs.webdriver.DriverManager.getDriver;

public class YatraComApplication {
    WebDriver driver = getDriver();
    YatraComSearchHomePage yatraComSearchHomePage = new YatraComSearchHomePage(driver);
    FlightDashboardPage flightDashboardPage = new FlightDashboardPage(driver);

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
        boolean isDashboardPageURLMatched = yatraComSearchHomePage.dashboardAfterSearchingFlight();
        Assert.assertTrue(isDashboardPageURLMatched, "flights are not displayed");
        System.out.println("Flights details are successfully displayed");

        WaitHelper.pause(5);
        List<FlightDetails> allFlightDetails=flightDashboardPage.captureFlightSearchResults();
        for(FlightDetails flightDetails:allFlightDetails)
        {
            System.out.println("Name of flight: "+flightDetails.getAirlineName());
            System.out.println("Code of flight: "+flightDetails.getFlightCode());
            System.out.println("flight's departure from: "+flightDetails.getFlightDepartureFrom());
            System.out.println("flight arrive time: "+flightDetails.getFlightArriveTime());
            System.out.println("flight arrive date: "+flightDetails.getFlightArriveDate());
            System.out.println("flight duration: "+flightDetails.getFlightDuration());
            System.out.println("flight stop or non-stop: "+flightDetails.getStopAndNon_Stop());
            System.out.println("flight's going to: "+flightDetails.getFlightGoingTo());
            System.out.println("flight reach time: "+flightDetails.getFlightReachTime());
            System.out.println("flight reach date: "+flightDetails.getFlightReachDate());
            System.out.println("flight price: "+flightDetails.getFlightPrice());
        }


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
}
