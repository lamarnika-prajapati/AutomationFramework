package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.tcs.webdriver.WaitHelper.waitForVisibility;


public class FlightDashboardPage {
    Logger LOGGER = LogManager.getLogger(FlightDashboardPage.class);
    WebDriver driver;

    @FindBy(xpath = "//div[@class='tuple']")
    List<WebElement> allSearchedFlightsLocator;

    String airlineNameLocator = "//div[contains(@class,'airline-holder')]/div[contains(@class, 'airline-name')]/span";

    String flightCodeLocator = "//div[contains(@class,'airline-holder')]/div[contains(@class, 'airline-name')]/p/span";

    String flightDepartureFromLocator = "//div[@class='depart-details']/p[contains(@class,'origin')]";
    String flightArriveTimeLocator = "//div[@class='depart-details']/p[contains(@class,'time')]";
    String flightArriveDateLocator = "//div[@class='depart-details']/p[contains(@class,'date')]";
    String flightDurationLocator = "//div[contains(@class,'stops-details')]/p[contains(@class,'duration')]";
    String stopAndNon_StopLocator = "//div[contains(@class,'stops-details')]//span[contains(@class,'duration')]";
    String flightGoingToLocator = "//div[contains(@class,'arrival-details')]/p[contains(@class,'origin')]";
    String flightReachTimeLocator = "//div[@class='tuple']//div[contains(@class,'arrival-details')]/p[contains(@class,'time')]";
    String flightReachDateLocator = "//div[contains(@class,'arrival-details')]/p[contains(@class,'date')]";
    String flightPriceLocatorLocator = "//div[@class='branded-fares-con']/div[contains(@class,'selected')]//input[@type='radio' and @checked]/parent::p/following-sibling::p";
    String flightBookNowButtonLocator = "//button[text()='Book Now']";

    public FlightDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public List<FlightDetails> captureFlightSearchResults() {
        List<FlightDetails> flights = new ArrayList<>();
        System.out.println("Count of flights: " + allSearchedFlightsLocator.size());
        for (WebElement flight : allSearchedFlightsLocator) {
            String airlineName = flight.findElement(By.xpath(airlineNameLocator)).getText();
            LOGGER.info("AirLineName: {}", airlineName);
            String flightCode = flight.findElement(By.xpath(flightCodeLocator)).getText();
            LOGGER.info("FlightCode: {}", flightCode);
            String flightDepartureFrom = flight.findElement(By.xpath(flightDepartureFromLocator)).getText();
            LOGGER.info("DepartureFrom: {}", flightDepartureFrom);
            String flightArriveTime = flight.findElement(By.xpath(flightArriveTimeLocator)).getText();
            LOGGER.info("Arrival time: {}", flightArriveTime);
            String flightArriveDate = flight.findElement(By.xpath(flightArriveDateLocator)).getText();
            LOGGER.info("Arrival Date: {}", flightArriveDate);
            String flightDuration = flight.findElement(By.xpath(flightDurationLocator)).getText();
            LOGGER.info("Duration: {}", flightDuration);
            String stopAndNon_Stop = flight.findElement(By.xpath(stopAndNon_StopLocator)).getText();
            LOGGER.info("Stop or Non-Stop: {}", stopAndNon_Stop);
            String flightGoingTo = flight.findElement(By.xpath(flightGoingToLocator)).getText();
            LOGGER.info("Going to: {}", flightGoingTo);
            String flightReachTime = flight.findElement(By.xpath(flightReachTimeLocator)).getText();
            LOGGER.info("Reach Time: {}", flightReachTime);
            String flightReachDate = flight.findElement(By.xpath(flightReachDateLocator)).getText();
            LOGGER.info("Reach Date: {}", flightReachDate);
            String flightPriceLocator = flight.findElement(By.xpath(flightPriceLocatorLocator)).getText();
            LOGGER.info("Flight Price: {}", flightPriceLocator);

            flightPriceLocator = flightPriceLocator.replaceAll(",", "");
            System.out.println(flightPriceLocator);

            FlightDetails flightDetails = new FlightDetails();
            flightDetails.setAirlineName(airlineName);
            flightDetails.setFlightCode(flightCode);
            flightDetails.setFlightDepartureFrom(flightDepartureFrom);
            flightDetails.setFlightArriveTime(flightArriveTime);
            flightDetails.setFlightArriveDate(flightArriveDate);
            flightDetails.setFlightDuration(flightDuration);
            flightDetails.setStopAndNon_Stop(stopAndNon_Stop);
            flightDetails.setFlightGoingTo(flightGoingTo);
            flightDetails.setFlightReachTime(flightReachTime);
            flightDetails.setFlightReachDate(flightReachDate);
            flightDetails.setFlightPrice(Integer.parseInt(flightPriceLocator));

            flights.add(flightDetails);
        }
        LOGGER.info("Captured details of {} flights.", flights.size());
        return flights;
    }


}
