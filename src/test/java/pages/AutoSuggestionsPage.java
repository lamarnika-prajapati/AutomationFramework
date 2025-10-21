package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tcs.webdriver.BasePage;

import java.util.List;

import static org.tcs.webdriver.WaitHelper.*;

public class AutoSuggestionsPage extends BasePage {
    WebDriver driver;
    By searchBox=By.xpath("//textarea[@aria-label='Search']");
    By suggestionOptions=By.xpath("//div[@role='presentation']/ul[@role='listbox']/li[@data-view-type='1']");

    public AutoSuggestionsPage(WebDriver driver)
    {
        this.driver=driver;
    }

    public void enterTextToSearchBox(String text)
    {
        sendKeys(searchBox,text);
    }
    public void clickTextFromSuggestedList(String text)
    {
        List<WebElement> listOfSuggestedOptions= waitForAllElementsVisible(suggestionOptions);
        for(WebElement option: listOfSuggestedOptions)
        {
            if(option.getText().equalsIgnoreCase(text))
            {
               option.click();
               break;
            }
        }
    }


}
