package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tcs.webdriver.BasePage;

import static org.tcs.webdriver.WaitHelper.waitForVisibility;

public class DashboardPracticeSitePage extends BasePage {
    WebDriver driver;
    By successfullyMessage= By.xpath("//h1[text()=\"Logged In Successfully\"]");

    public DashboardPracticeSitePage(WebDriver driver)
    {
        this.driver=driver;
    }
    public boolean isSuccessfullyMessageDisplayed()
    {
        return waitForVisibility(successfullyMessage).isDisplayed();
    }

}
