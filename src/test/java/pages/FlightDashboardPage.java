package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.tcs.webdriver.BasePage;
import org.tcs.webdriver.WaitHelper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.tcs.webdriver.WaitHelper.waitForVisibility;


public class FlightDashboardPage extends BasePage {
    Logger LOGGER = LogManager.getLogger(FlightDashboardPage.class);
    WebDriver driver;

  /*  @FindBy(xpath = "//div[@class='tuple']")
    List<WebElement> allSearchedFlightsLocator;*/

    String allSearchedFlightsLocator = "//div[@class='tuple']";
    String airlineNameLocator = ".//div[contains(@class,'airline-holder')]/div[contains(@class, 'airline-name')]/span";

    String flightCodeLocator = ".//div[contains(@class,'airline-holder')]/div[contains(@class, 'airline-name')]/p/span";

    String flightDepartureFromLocator = ".//div[@class='depart-details']/p[contains(@class,'origin')]";
    String flightArriveTimeLocator = ".//div[@class='depart-details']/p[contains(@class,'time')]";
    String flightArriveDateLocator = ".//div[@class='depart-details']/p[contains(@class,'date')]";
    String flightDurationLocator = ".//div[contains(@class,'stops-details')]/p[contains(@class,'duration')]";
    String stopAndNon_StopLocator = ".//div[contains(@class,'stops-details')]//span[contains(@class,'duration')]";
    String flightGoingToLocator = ".//div[contains(@class,'arrival-details')]/p[contains(@class,'origin')]";
    String flightReachTimeLocator = ".//div[contains(@class,'arrival-details')]/p[contains(@class,'time')]";
    String flightReachDateLocator = ".//div[contains(@class,'arrival-details')]/p[contains(@class,'date')]";
    String flightPriceLocatorLocator = ".//div[@class='branded-fares-con']/div[contains(@class,'selected')]//input[@type='radio' and @checked]/parent::p/following-sibling::p";
    String flightBookNowButtonLocator = ".//button[text()='Book Now']";

    String departTimeLocator = "//p[text()='Depart']";
    String departTimeLocatorArrowUp = "//p[text()='Depart']/i[contains(@class,'arrow-up')]";
    String departTimeLocatorArrowDown = "//p[text()='Depart']/i[contains(@class,'arrow-down')]";

    String arriveTimeLocator = "//p[text()='Arrive']/parent::li";
    String durationLocator = "//p[text()='Duration']";
    String pricePerAdultLocator = "//p[text()='Price Per Adult']";
    String nonStopFirstLocator = "//p[text()='Non Stop First']";
    String hideAirportLocator = "//span[text()='Hide Airports']";

    public FlightDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean dashboardAfterSearchingFlight() {
        LOGGER.info("Capturing dashboard page URL");
        String dashboardPageURL = driver.getCurrentUrl();
        return dashboardPageURL.contains("air-search-ui");
    }

    public List<FlightDetails> captureFlightSearchResults() {

        List<WebElement> allSearchedFlights;
        int currentSize = 0;
        int previousSize = 0;

        while (true) {

            allSearchedFlights = driver.findElements(By.xpath(allSearchedFlightsLocator));
            currentSize = allSearchedFlights.size();
            if (currentSize == previousSize) {
                break;
            }
            scrollToBottom();
            previousSize = currentSize;
            WaitHelper.pause(2);
        }
        LOGGER.info("Count of all searched flights: {} ", allSearchedFlights.size());
        List<FlightDetails> flights = new ArrayList<>();

        for (int i = 0; i < allSearchedFlights.size(); i++) {
            String airlineName;
            String flightCode;
            String flightDepartureFrom;
            String flightArriveTime;
            String flightArriveDate;
            String flightDuration;
            String stopAndNon_Stop;
            String flightGoingTo;
            String flightReachTime;
            String flightReachDate;
            String flightPriceLocator;


            WebElement flight = driver.findElements(By.xpath(allSearchedFlightsLocator)).get(i);
            try {
                airlineName = flight.findElement(By.xpath(airlineNameLocator)).getText();
                // LOGGER.info("AirLineName: {}", airlineName);

            } catch (StaleElementReferenceException e) {
                flight = driver.findElements(By.xpath("//div[@class='tuple']")).get(i);
                airlineName = flight.findElement(By.xpath(airlineNameLocator)).getText();
                // LOGGER.info("AirLineName: {}", airlineName);

            }

            try {
                flightCode = flight.findElement(By.xpath(flightCodeLocator)).getText();
                //  LOGGER.info("FlightCode: {}", flightCode);
            } catch (StaleElementReferenceException e) {
                flight = driver.findElements(By.xpath("//div[@class='tuple']")).get(i);
                flightCode = flight.findElement(By.xpath(flightCodeLocator)).getText();
                // LOGGER.info("FlightCode: {}", flightCode);
            }

            try {

                flightDepartureFrom = flight.findElement(By.xpath(flightDepartureFromLocator)).getText();
                //  LOGGER.info("DepartureFrom: {}", flightDepartureFrom);
            } catch (StaleElementReferenceException e) {
                flight = driver.findElements(By.xpath("//div[@class='tuple']")).get(i);
                flightDepartureFrom = flight.findElement(By.xpath(flightDepartureFromLocator)).getText();
                //  LOGGER.info("DepartureFrom: {}", flightDepartureFrom);
            }

            try {
                flightArriveTime = flight.findElement(By.xpath(flightArriveTimeLocator)).getText();
                //   LOGGER.info("Arrival time: {}", flightArriveTime);
            } catch (StaleElementReferenceException e) {
                flight = driver.findElements(By.xpath("//div[@class='tuple']")).get(i);
                flightArriveTime = flight.findElement(By.xpath(flightArriveTimeLocator)).getText();
                // LOGGER.info("Arrival time: {}", flightArriveTime);
            }

            try {
                flightArriveDate = flight.findElement(By.xpath(flightArriveDateLocator)).getText();
                // LOGGER.info("Arrival Date: {}", flightArriveDate);
            } catch (StaleElementReferenceException e) {
                flight = driver.findElements(By.xpath("//div[@class='tuple']")).get(i);
                flightArriveDate = flight.findElement(By.xpath(flightArriveDateLocator)).getText();
                // LOGGER.info("Arrival Date: {}", flightArriveDate);
            }

            try {
                flightDuration = flight.findElement(By.xpath(flightDurationLocator)).getText();
                //LOGGER.info("Duration: {}", flightDuration);
            } catch (StaleElementReferenceException e) {
                flight = driver.findElements(By.xpath("//div[@class='tuple']")).get(i);
                flightDuration = flight.findElement(By.xpath(flightDurationLocator)).getText();
                // LOGGER.info("Duration: {}", flightDuration);
            }

            try {
                stopAndNon_Stop = flight.findElement(By.xpath(stopAndNon_StopLocator)).getText();
                // LOGGER.info("Stop or Non-Stop: {}", stopAndNon_Stop);
            } catch (StaleElementReferenceException e) {
                flight = driver.findElements(By.xpath("//div[@class='tuple']")).get(i);
                stopAndNon_Stop = flight.findElement(By.xpath(stopAndNon_StopLocator)).getText();
                //  LOGGER.info("Stop or Non-Stop: {}", stopAndNon_Stop);
            }

            try {
                flightGoingTo = flight.findElement(By.xpath(flightGoingToLocator)).getText();
                //LOGGER.info("Going to: {}", flightGoingTo);
            } catch (StaleElementReferenceException e) {
                flight = driver.findElements(By.xpath("//div[@class='tuple']")).get(i);
                flightGoingTo = flight.findElement(By.xpath(flightGoingToLocator)).getText();
                // LOGGER.info("Going to: {}", flightGoingTo);
            }


            try {
                flightReachTime = flight.findElement(By.xpath(flightReachTimeLocator)).getText();
                //LOGGER.info("Reach Time: {}", flightReachTime);
            } catch (StaleElementReferenceException e) {
                flight = driver.findElements(By.xpath("//div[@class='tuple']")).get(i);
                flightReachTime = flight.findElement(By.xpath(flightReachTimeLocator)).getText();
                // LOGGER.info("Reach Time: {}", flightReachTime);
            }

            try {
                flightReachDate = flight.findElement(By.xpath(flightReachDateLocator)).getText();
                // LOGGER.info("Reach Date: {}", flightReachDate);
            } catch (StaleElementReferenceException e) {
                flight = driver.findElements(By.xpath("//div[@class='tuple']")).get(i);
                flightReachDate = flight.findElement(By.xpath(flightReachDateLocator)).getText();
                // LOGGER.info("Reach Date: {}", flightReachDate);
            }

            try {
                flightPriceLocator = flight.findElement(By.xpath(flightPriceLocatorLocator)).getText();
                flightPriceLocator = flightPriceLocator.replaceAll(",", "").trim();
                //LOGGER.info("Flight Price: {}", flightPriceLocator);
            } catch (StaleElementReferenceException e) {
                flight = driver.findElements(By.xpath("//div[@class='tuple']")).get(i);
                flightPriceLocator = flight.findElement(By.xpath(flightPriceLocatorLocator)).getText();
                flightPriceLocator = flightPriceLocator.replaceAll(",", "").trim();
                // LOGGER.info("Flight Price: {}", flightPriceLocator);
            }

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
        return flights;
    }

    public void clickOnDepartFilter() {
        //moveToElement(By.xpath(departTimeLocator));
        // keyDown(Keys.PAGE_DOWN);
        scrollToTop();
        pause(1);
        boolean isHideAirportElementDisplayed = isElementPresent(By.xpath(hideAirportLocator),2);
        if (isHideAirportElementDisplayed) {
            click(By.xpath(hideAirportLocator));
            pause(1);
            click(By.xpath(departTimeLocator));
        } else {
            click(By.xpath(departTimeLocator));
        }
    }

    public void clickOnReverseDepartFilter() {
        clickOnDepartFilter();
        pause(1);
        click(By.xpath(departTimeLocator));
    }

    public boolean validateDepartFilterFunctionality(List<FlightDetails> flightDetails, String departureFrom) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        boolean isFlightListFiltered = false;


        List<FlightDetails> otherDepartureFromFlights = new ArrayList<>();
        for (FlightDetails sortedFlightDetails : flightDetails) {
            if (!(sortedFlightDetails.getFlightDepartureFrom().contains(departureFrom))) {
                otherDepartureFromFlights.add(sortedFlightDetails);

            }
        }

        if (!otherDepartureFromFlights.isEmpty()) {
            flightDetails.removeAll(otherDepartureFromFlights);
        }
        LOGGER.info("Filtering actual city flight details based on Depart filter");
        flightDetails.sort(Comparator.comparing((FlightDetails f) -> LocalTime.parse(f.flightArriveTime, timeFormatter))
                .thenComparing(FlightDetails::getFlightPrice)
                .thenComparingInt(f -> f.convertDurationInMinutes(f.flightDuration)));

        if (!otherDepartureFromFlights.isEmpty()) {
            LOGGER.info("Filtering other city flight details based on Depart filter");
            otherDepartureFromFlights.sort(Comparator.comparing((FlightDetails f) -> LocalTime.parse(f.flightArriveTime, timeFormatter))
                    .thenComparing(FlightDetails::getFlightPrice)
                    .thenComparingInt(f -> f.convertDurationInMinutes(f.flightDuration)));
            flightDetails.addAll(otherDepartureFromFlights);
        }

        System.out.println("Before arrive filter");
        for (FlightDetails sortedFlightDetails : flightDetails) {

            System.out.println(sortedFlightDetails.getAirlineName());
            System.out.println(sortedFlightDetails.getFlightCode());
            System.out.println(sortedFlightDetails.getFlightDepartureFrom());
            System.out.println(sortedFlightDetails.getFlightArriveTime());
            System.out.println(sortedFlightDetails.getFlightArriveDate());
            System.out.println(sortedFlightDetails.getFlightDuration());
            System.out.println(sortedFlightDetails.getFlightGoingTo());
            System.out.println(sortedFlightDetails.getFlightReachTime());
            System.out.println(sortedFlightDetails.getFlightReachDate());
            System.out.println(sortedFlightDetails.getFlightPrice());
        }

        List<FlightDetails> filterFlightList = captureFlightSearchResults();
        System.out.println("After arrive filter");

        for (FlightDetails filteredFlightList : filterFlightList) {
            System.out.println(filteredFlightList.getAirlineName());
            System.out.println(filteredFlightList.getFlightCode());
            System.out.println(filteredFlightList.getFlightDepartureFrom());
            System.out.println(filteredFlightList.getFlightArriveTime());
            System.out.println(filteredFlightList.getFlightArriveDate());
            System.out.println(filteredFlightList.getFlightDuration());
            System.out.println(filteredFlightList.getFlightGoingTo());
            System.out.println(filteredFlightList.getFlightReachTime());
            System.out.println(filteredFlightList.getFlightReachDate());
            System.out.println(filteredFlightList.getFlightPrice());

        }


        if (filterFlightList.equals(flightDetails)) {
            isFlightListFiltered = true;

        }
        System.out.println("Is flight lists are equals: " + isFlightListFiltered);

        return isFlightListFiltered;
    }


    public boolean validateDepartReverseFilterFunctionality(List<FlightDetails> flightDetails, String departureFrom) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        boolean isFlightListFiltered = false;
        List<FlightDetails> otherDepartureFromFlights = new ArrayList<>();
        for (FlightDetails sortedFlightDetails : flightDetails) {
            if (!(sortedFlightDetails.getFlightDepartureFrom().contains(departureFrom))) {
                otherDepartureFromFlights.add(sortedFlightDetails);

            }
        }

        if (!otherDepartureFromFlights.isEmpty()) {
            System.out.println("otherDepartureFrom: "+otherDepartureFromFlights.size());
            flightDetails.removeAll(otherDepartureFromFlights);
        }
        LOGGER.info("Filtering flight details based on Depart reverse filter");
        flightDetails.sort(Comparator.comparing((FlightDetails f) -> LocalTime.parse(f.flightArriveTime, timeFormatter),Comparator.reverseOrder())
                .thenComparing(FlightDetails::getFlightPrice,Comparator.reverseOrder())
                .thenComparing((FlightDetails f)  -> f.convertDurationInMinutes(f.flightDuration),Comparator.reverseOrder()));

        if (!otherDepartureFromFlights.isEmpty()) {
            LOGGER.info("Filtering other city flight details based on Depart reverse filter");
            otherDepartureFromFlights.sort(Comparator.comparing((FlightDetails f) -> LocalTime.parse(f.flightArriveTime, timeFormatter),Comparator.reverseOrder())
                    .thenComparing(FlightDetails::getFlightPrice,Comparator.reverseOrder())
                    .thenComparing((FlightDetails f)  -> f.convertDurationInMinutes(f.flightDuration),Comparator.reverseOrder()));

            flightDetails.addAll(otherDepartureFromFlights);
        }
        System.out.println("Before arrive filter");

        for (FlightDetails sortedFlightDetails : flightDetails) {
            System.out.println(sortedFlightDetails.getAirlineName());
            System.out.println(sortedFlightDetails.getFlightCode());
            System.out.println(sortedFlightDetails.getFlightDepartureFrom());
            System.out.println(sortedFlightDetails.getFlightArriveTime());
            System.out.println(sortedFlightDetails.getFlightArriveDate());
            System.out.println(sortedFlightDetails.getFlightDuration());
            System.out.println(sortedFlightDetails.getFlightGoingTo());
            System.out.println(sortedFlightDetails.getFlightReachTime());
            System.out.println(sortedFlightDetails.getFlightReachDate());
            System.out.println(sortedFlightDetails.getFlightPrice());
            //System.out.println(sortedFlightDetails.getAirlineName() + " " + sortedFlightDetails.getFlightPrice());
        }

        List<FlightDetails> filterFlightList = captureFlightSearchResults();
        System.out.println("After arrive filter");

        for (FlightDetails filteredFlightList : filterFlightList) {
            System.out.println(filteredFlightList.getAirlineName());
            System.out.println(filteredFlightList.getFlightCode());
            System.out.println(filteredFlightList.getFlightDepartureFrom());
            System.out.println(filteredFlightList.getFlightArriveTime());
            System.out.println(filteredFlightList.getFlightArriveDate());
            System.out.println(filteredFlightList.getFlightDuration());
            System.out.println(filteredFlightList.getFlightGoingTo());
            System.out.println(filteredFlightList.getFlightReachTime());
            System.out.println(filteredFlightList.getFlightReachDate());
            System.out.println(filteredFlightList.getFlightPrice());

        }


        if (filterFlightList.equals(flightDetails)) {
            isFlightListFiltered = true;

        }
        System.out.println("Is flight lists are equals: " + isFlightListFiltered);

        return isFlightListFiltered;
    }


    public void clickOnArriveFilter() {
        moveToElement(By.xpath(arriveTimeLocator));
        // keyDown(Keys.PAGE_DOWN);
        scrollToTop();
        pause(1);
        click(By.xpath(arriveTimeLocator));

    }

    public boolean validateArriveFilterFunctionality(List<FlightDetails> flightDetails, String departureFrom) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH);

        boolean isFlightListFiltered = false;
        List<FlightDetails> otherDepartureFlights = new ArrayList<>();
        for (FlightDetails sortedFlightDetails : flightDetails) {
            if (!(sortedFlightDetails.getFlightDepartureFrom().contains(departureFrom))) {
                otherDepartureFlights.add(sortedFlightDetails);

            }
        }

        if (!otherDepartureFlights.isEmpty()) {
            flightDetails.removeAll(otherDepartureFlights);
        }
        LOGGER.info("Filtering flight details based on Arrive filter");

        flightDetails.sort(Comparator.comparing((FlightDetails f) -> MonthDay.parse(f.flightReachDate.trim(), dateFormatter)).thenComparing((FlightDetails f) -> LocalTime.parse(f.flightReachTime, timeFormatter))
                .thenComparing(FlightDetails::getFlightPrice)
                .thenComparingInt(f -> f.convertDurationInMinutes(f.flightDuration)));

        if (!otherDepartureFlights.isEmpty()) {
            LOGGER.info("Filtering other city flight details based on Arrive filter");
            otherDepartureFlights.sort(Comparator.comparing((FlightDetails f) -> MonthDay.parse(f.flightReachDate.trim(), dateFormatter)).thenComparing((FlightDetails f) -> LocalTime.parse(f.flightReachTime, timeFormatter))
                    .thenComparing(FlightDetails::getFlightPrice)
                    .thenComparingInt(f -> f.convertDurationInMinutes(f.flightDuration)));
            flightDetails.addAll(otherDepartureFlights);
        }

        System.out.println("Before arrive filter");

        for (FlightDetails sortedFlightDetails : flightDetails) {
            System.out.println(sortedFlightDetails.getAirlineName());
            System.out.println(sortedFlightDetails.getFlightCode());
            System.out.println(sortedFlightDetails.getFlightDepartureFrom());
            System.out.println(sortedFlightDetails.getFlightArriveTime());
            System.out.println(sortedFlightDetails.getFlightArriveDate());
            System.out.println(sortedFlightDetails.getFlightDuration());
            System.out.println(sortedFlightDetails.getFlightGoingTo());
            System.out.println(sortedFlightDetails.getFlightReachTime());
            System.out.println(sortedFlightDetails.getFlightReachDate());
            System.out.println(sortedFlightDetails.getFlightPrice());
        }

        List<FlightDetails> filterFlightList = captureFlightSearchResults();
        System.out.println("After arrive filter");

        for (FlightDetails filteredFlightList : filterFlightList) {
            System.out.println(filteredFlightList.getAirlineName());
            System.out.println(filteredFlightList.getFlightCode());
            System.out.println(filteredFlightList.getFlightDepartureFrom());
            System.out.println(filteredFlightList.getFlightArriveTime());
            System.out.println(filteredFlightList.getFlightArriveDate());
            System.out.println(filteredFlightList.getFlightDuration());
            System.out.println(filteredFlightList.getFlightGoingTo());
            System.out.println(filteredFlightList.getFlightReachTime());
            System.out.println(filteredFlightList.getFlightReachDate());
            System.out.println(filteredFlightList.getFlightPrice());

        }

        if (filterFlightList.equals(flightDetails)) {
            isFlightListFiltered = true;

        }
        System.out.println("Is flight lists are equals: " + isFlightListFiltered);

        return isFlightListFiltered;
    }

    public void clickOnDurationFilter() {
        moveToElement(By.xpath(durationLocator));
        // keyDown(Keys.PAGE_DOWN);
        scrollToTop();
        pause(1);
        click(By.xpath(durationLocator));

    }


    public boolean validateDurationFilterFunctionality(List<FlightDetails> flightDetails, String departureFrom) {
        boolean isFlightListFiltered = false;
        List<FlightDetails> otherDepartureFlights = new ArrayList<>();
        for (FlightDetails sortedFlightDetails : flightDetails) {
            if (!(sortedFlightDetails.getFlightDepartureFrom().contains(departureFrom))) {
                otherDepartureFlights.add(sortedFlightDetails);

            }
        }

        if (!otherDepartureFlights.isEmpty()) {
            flightDetails.removeAll(otherDepartureFlights);
        }
        LOGGER.info("Filtering flight details based on Duration filter");

        flightDetails.sort(Comparator.comparing((FlightDetails f) -> f.convertDurationInMinutes(f.flightDuration)).thenComparing(FlightDetails::getFlightPrice));

        if (!otherDepartureFlights.isEmpty()) {
            LOGGER.info("Filtering other city flight details based on Duration filter");
            otherDepartureFlights.sort(Comparator.comparing((FlightDetails f) -> f.convertDurationInMinutes(f.flightDuration))
                    .thenComparing(FlightDetails::getFlightPrice));
            flightDetails.addAll(otherDepartureFlights);
        }

        List<FlightDetails> filterFlightList = captureFlightSearchResults();

        if (filterFlightList.equals(flightDetails)) {
            isFlightListFiltered = true;

        }
        System.out.println("Is flight lists are equals: " + isFlightListFiltered);
        return isFlightListFiltered;
    }

    public void clickOnPricePerAdultFilter() {
        moveToElement(By.xpath(pricePerAdultLocator));
        // keyDown(Keys.PAGE_DOWN);
        scrollToTop();
        pause(1);
        click(By.xpath(pricePerAdultLocator));

    }

    public boolean validatePriceFilterFunctionality(List<FlightDetails> flightDetails, String departureFrom) {
        boolean isFlightListFiltered = false;
        List<FlightDetails> otherDepartureFlights = new ArrayList<>();
        for (FlightDetails sortedFlightDetails : flightDetails) {
            if (!(sortedFlightDetails.getFlightDepartureFrom().contains(departureFrom))) {
                otherDepartureFlights.add(sortedFlightDetails);

            }
        }

        if (!otherDepartureFlights.isEmpty()) {
            flightDetails.removeAll(otherDepartureFlights);
        }
        LOGGER.info("Filtering flight details based on Price filter");
        flightDetails.sort(Comparator.comparing(FlightDetails::getFlightPrice).thenComparing(f -> f.convertDurationInMinutes(f.flightDuration)));

        if (!otherDepartureFlights.isEmpty()) {
            LOGGER.info("Filtering other city flight details based on Price filter");
            otherDepartureFlights.sort(Comparator.comparing(FlightDetails::getFlightPrice).thenComparing(f -> f.convertDurationInMinutes(f.flightDuration)));
            flightDetails.addAll(otherDepartureFlights);
        }

        System.out.println("Before price filter");
        for (FlightDetails sortedFlightDetails : flightDetails) {
            System.out.println(sortedFlightDetails.getAirlineName());
            System.out.println(sortedFlightDetails.getFlightCode());
            System.out.println(sortedFlightDetails.getFlightDepartureFrom());
            System.out.println(sortedFlightDetails.getFlightArriveTime());
            System.out.println(sortedFlightDetails.getFlightArriveDate());
            System.out.println(sortedFlightDetails.getFlightDuration());
            System.out.println(sortedFlightDetails.getFlightGoingTo());
            System.out.println(sortedFlightDetails.getFlightReachTime());
            System.out.println(sortedFlightDetails.getFlightReachDate());
            System.out.println(sortedFlightDetails.getFlightPrice());
            //System.out.println(sortedFlightDetails.getAirlineName() + " " + sortedFlightDetails.getFlightPrice());
        }
        List<FlightDetails> filterFlightList = captureFlightSearchResults();
        System.out.println("After price filter");

        for (FlightDetails filteredFlightList : filterFlightList) {
            System.out.println(filteredFlightList.getAirlineName());
            System.out.println(filteredFlightList.getFlightCode());
            System.out.println(filteredFlightList.getFlightDepartureFrom());
            System.out.println(filteredFlightList.getFlightArriveTime());
            System.out.println(filteredFlightList.getFlightArriveDate());
            System.out.println(filteredFlightList.getFlightDuration());
            System.out.println(filteredFlightList.getFlightGoingTo());
            System.out.println(filteredFlightList.getFlightReachTime());
            System.out.println(filteredFlightList.getFlightReachDate());
            System.out.println(filteredFlightList.getFlightPrice());

        }

        if (filterFlightList.equals(flightDetails)) {
            isFlightListFiltered = true;

        }

        System.out.println("Is flight lists are equals: " + isFlightListFiltered);
        return isFlightListFiltered;
    }


    public void clickOnNonStopFirstFilter() {
        click(By.xpath(nonStopFirstLocator));
    }


}
