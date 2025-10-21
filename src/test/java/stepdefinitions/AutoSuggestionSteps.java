package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.record.pivottable.StreamIDRecord;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tcs.webdriver.AssertHelper;
import org.tcs.webdriver.DriverManager;
import pages.AutoSuggestionsPage;


import static org.tcs.webdriver.DriverManager.getDriver;

public class AutoSuggestionSteps {

    Logger LOGGER= LogManager.getLogger(AutoSuggestionsPage.class);
    WebDriver driver=getDriver();
    AutoSuggestionsPage autoSuggestionsPage=new AutoSuggestionsPage(driver);

    @Given("user is on google page")
    public void userIsOnGooglePage()
    {
        driver.get("https://www.google.com/");
    }

    @When("user type the text {string} into the search box")
    public void userTypeTheTextIntoTheSearchBox(String text)
    {
        autoSuggestionsPage.enterTextToSearchBox(text);
    }

    @Then("user click to the {string} from the list")
    public void userClickToTheTextFromTheList(String requiredText)
    {
        autoSuggestionsPage.clickTextFromSuggestedList(requiredText);
        String pageTitle=driver.getTitle();
        LOGGER.info("Page title is {}",pageTitle);
        AssertHelper.assertTrue(pageTitle.contains("Google Search"),"The option was not clicked");
        System.out.println("Option clicked successfully");
    }



}
