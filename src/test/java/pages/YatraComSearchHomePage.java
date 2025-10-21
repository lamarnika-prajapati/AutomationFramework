package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.tcs.webdriver.BasePage;
import org.tcs.webdriver.ElementHelper;
import org.tcs.webdriver.WaitHelper;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class YatraComSearchHomePage extends BasePage {
    Logger LOGGER = LogManager.getLogger(YatraComSearchHomePage.class);

    private WebDriver driver;
    private Wait<WebDriver> wait = getWait();
    String tripType = "//h4[text()='|TripType|']/parent::span/preceding-sibling::span/input";

    By crossLoginPopup = By.xpath("//span[@class='style_cross__q1ZoV']/img[@alt=\"cross\"]");
    By departureFormLocator = By.xpath("//p[text()='Departure From']");
    By fromCityLocator = By.xpath("//input[@id='input-with-icon-adornment']");
    By listOfCityFromSearchedCity = By.xpath("//input[@id='input-with-icon-adornment']/ancestor::div[contains(@class,'MuiStack')]/following-sibling::div//li//span");
    By goingToLocator = By.xpath("//p[text()='Going To']");
    By toCityLocator = By.xpath("//input[@id='input-with-icon-adornment']");

    By departureDate = By.xpath("//div[@class='css-w7k25o']");
    By datePickerMonths = By.xpath("//span[@class='react-datepicker__current-month']");
    By goToNextMonth = By.xpath("//button[@aria-label=\"Next Month\" and not(@style='visibility: hidden;')]");
    String allDaysByMonthPath = "//span[@class='react-datepicker__current-month' and contains(text(),'|Month Year|')]/ancestor::div[@class='react-datepicker__month-container']/div[@class='react-datepicker__month']/div/div[not(contains(@class,'day--outside')) and not(contains(@class,'day--disabled'))]";

    By travelerLocator = By.xpath("//div[@name='traveler']/parent::div");
    By adultsLocator = By.xpath("//div/p[text()='Adult']//following-sibling::div/ul/li");
    // By childrenLocator = By.xpath("//div/p[text()='Child']//following-sibling::div/ul/li");
    By childrenLocator = By.xpath("//div/p[text()='Child']//following-sibling::div/ul/li[@aria-disabled='false']");
    // By infantsLocator = By.xpath("//div/p[text()='Infant']//following-sibling::div/ul/li");
    By infantsLocator = By.xpath("//div/p[text()='Infant']//following-sibling::div/ul/li[@aria-disabled='false']");
    String travelAndClassLocator = "//h4[text()='|TravelClass|']/parent::span/preceding-sibling::span/input";
    By doneButton = By.xpath("//button[text()='Done']");

    String spacialFareLoactor = "//div[text()='|SpecialFare|']/parent::div/preceding-sibling::span/input";

    By nonStopCheckBoxLocator = By.xpath("//span[text()='Non-Stop Flights']/ancestor::label//input[@type='checkbox']");
    By searchButtonLocator = By.xpath("//button[text()='Search']");

    By dashboardDestinationLocator = By.xpath("//input[@value=\"Select destination\"]");
    By dashboardOriginLocator = By.xpath("//input[@value=\"Select Origin\"]");
    By dashboardMessageWithOriginAndDestination = By.xpath("//span[text()='We found more airports near ']");

    By iframeLocator = By.xpath("//iframe[@id=\"webklipper-publisher-widget-container-notification-frame\"]");
    By iframeCloseButton = By.xpath("//button[@name=\"close\"]");
    List<WebElement> listOfAdults;

    public YatraComSearchHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeLoginPopup() {
        LOGGER.info("Closing Login Popup");
        click(crossLoginPopup);
    }

    public void selectTypeOfTrip(String tripType) {
        LOGGER.info("selecting trip type {}", tripType);
        driver.findElement(By.xpath(this.tripType.replace("|TripType|", tripType))).click();
    }

    /* public void clickDepartureFrom() {
         List<WebElement> iframes = driver.findElements(iframeLocator);
         if (!iframes.isEmpty()) {
             driver.switchTo().frame(iframes.getFirst());
             LOGGER.info("Popup appeared and closing it");
             click(iframeCloseButton);
             driver.switchTo().defaultContent();
         } else {
             LOGGER.info("No iframe found");
         }
         click(departureFormLocator);
     }*/
    public void clickDepartureFrom() {
        LOGGER.info("waiting for iframe popup");
        boolean isPopupElementPresent = isElementPresent(iframeLocator, 1);
        LOGGER.info("is iframe popup present {}", isPopupElementPresent);
        if (isPopupElementPresent) {
            WebElement popupElement = driver.findElement(iframeLocator);
            driver.switchTo().frame(popupElement);
            LOGGER.info("Popup appeared and closing it");
            click(iframeCloseButton);
            driver.switchTo().defaultContent();
        } else {
            LOGGER.info("No popup element is present");
        }
        click(departureFormLocator);
    }

    public void clickGoingTo() {
        click(goingToLocator);
    }

    public void enterDepartureFromCity(String departureFrom) {
        LOGGER.info("Entering departure city {}", departureFrom);
        sendKeys(fromCityLocator, departureFrom);
    }

    public void enterGoingToCity(String goingTo) {
        LOGGER.info("Entering going to city {}", goingTo);
        sendKeys(toCityLocator, goingTo);
    }

    public void selectCity(String city, String departureOrTo) {

        try {
            Thread.sleep(2000); // sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();  // prints the stack trace (for debugging)
            Thread.currentThread().interrupt(); // good practice to restore the interrupt state
        }
        List<WebElement> listOfDepartureCity = driver.findElements(listOfCityFromSearchedCity);
        LOGGER.info("printing all suggested city from Departure from");
        for (WebElement departureFromCity : listOfDepartureCity) {
            System.out.println("city: " + departureFromCity.getText());
        }
        if (!(listOfDepartureCity.isEmpty())) {
            System.out.println("Count of Suggestion options: " + getElementCount(listOfCityFromSearchedCity));
            for (WebElement departureCitySuggestion : listOfDepartureCity) {
                if ((departureCitySuggestion.getText().contains(city))) {
                    LOGGER.info("clicking the city from the suggestion list '{}'", departureCitySuggestion.getText());
                    click(departureCitySuggestion);
                    break;
                }
            }
        } else {
            LOGGER.error("No '{}' city displayed", departureOrTo);
            throw new RuntimeException("No '" + departureOrTo + "' city displayed ");
        }
    }

    public void selectDepartureFromCity(String departureFromCity) {
        selectCity(departureFromCity, "Departure");
    }

    public void selectGoingToCity(String goingToCity) {
        selectCity(goingToCity, "To");
    }

    public void clickOnDepartureDate() {
        click(departureDate);
    }

    public void selectDepartureDate(String date) {
        WebElement datePickerMonth = driver.findElement(datePickerMonths);
        System.out.println(datePickerMonth.getText());
        String trimmedDate = date.substring(3);
        System.out.println("Trimmed date: " + trimmedDate);
        do {
            if (trimmedDate.contains(datePickerMonth.getText())) {
                System.out.println("Searched Month: " + datePickerMonth.getText());
                List<WebElement> allDaysOfMonth = driver.findElements(By.xpath(allDaysByMonthPath.replace("|Month Year|", datePickerMonth.getText())));
                LOGGER.info("printing size of days list '{}'", allDaysOfMonth.size());
                for (WebElement day : allDaysOfMonth) {
                    String getDayOnly = day.getText().split("\\n")[0].trim();
                    String getDayFromDate = date.substring(0, 2);
                    if (getDayFromDate.equals(getDayOnly)) {
                        LOGGER.info("Click the departure day '{}'", getDayOnly);
                        click(day);
                        break;
                    }
                }
                break;
            } else {
                click(goToNextMonth);
            }
        } while (!datePickerMonth.getText().contains(date));


    }

    public void clickOnTravelAndClass() {
        click(travelerLocator);
    }

    public void selectAdults(String adultsNumber) {
        listOfAdults = driver.findElements(adultsLocator);
        for (WebElement adult : listOfAdults) {
            if (adult.getText().equals(adultsNumber)) {
                click(adult);
                break;
            }
        }
    }

    public void selectChildren(String childrenNumber) {
        List<WebElement> listOfAdults = driver.findElements(childrenLocator);
        for (WebElement child : listOfAdults) {
            if (child.getText().equals(childrenNumber)) {
                click(child);
                break;
            }
        }

    }

    public void selectInfants(String infantsNumber) {
        List<WebElement> listOfAdults = driver.findElements(infantsLocator);
        for (WebElement infant : listOfAdults) {
            if (infant.getText().equals(infantsNumber)) {
                click(infant);
                break;
            }
        }
    }

    public void selectTravelClass(String travelClass) {
        driver.findElement(By.xpath(travelAndClassLocator.replace("|TravelClass|", travelClass))).click();
    }

    public void clickOnDoneAfterSelectingTravelAndClass() {
        click(doneButton);
    }

    public void selectSpecialFare(String specialFare) {
        driver.findElement(By.xpath(spacialFareLoactor.replace("|SpecialFare|", specialFare))).click();
    }

    public void selectNonStopFlights(boolean isNonStop) {
        boolean isSelectedCheckBox = driver.findElement(nonStopCheckBoxLocator).isSelected();
        if (isNonStop && !isSelectedCheckBox) {
            driver.findElement(nonStopCheckBoxLocator).click();
        }
    }

    public void clickOnSearchButton() {
        click(searchButtonLocator);
        try {
            Thread.sleep(5000); // sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();  // prints the stack trace (for debugging)
            Thread.currentThread().interrupt(); // good practice to restore the interrupt state
        }
    }

    public boolean dashboardAfterSearchingFlight() {
        LOGGER.info("Capturing dashboard page URL");
        String dashboardPageURL = driver.getCurrentUrl();
        return dashboardPageURL.contains("air-search-ui");
    }

    public boolean validateChildDependencyOnAdult(String child) {
        List<WebElement> listOfChild = driver.findElements(childrenLocator);
        LOGGER.info("Validating child dependency on adult");
        return (listOfChild.size() - 1) == Integer.parseInt(child);

    }

    public boolean validateInfantDependencyOnAdult(String infant) {
        List<WebElement> listOfInfants = driver.findElements(infantsLocator);
        LOGGER.info("Validating infant dependency on adult");
        return (listOfInfants.size() - 1) == Integer.parseInt(infant);

    }


}
