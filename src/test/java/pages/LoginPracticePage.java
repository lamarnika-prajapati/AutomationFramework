package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tcs.webdriver.BasePage;
import org.tcs.webdriver.ElementHelper;

import static org.tcs.webdriver.WaitHelper.getWait;

public class LoginPracticePage extends BasePage {
    private WebDriver driver;
    private By userid=By.id("username");
   // private By password=By.id("password");
    private By password= RelativeLocator.with(By.tagName("input")).below(userid);
    private By submit=By.id("submit");
    private By errorMsg=By.xpath("//div[text()=\"Your username is invalid!\"]");

    public LoginPracticePage(WebDriver driver)
    {
        this.driver=driver;
    }

    public void enterUserName(String userId)
    {
        sendKeys(userid, userId);
    }
    public void enterPassword(String pwd)
    {
        sendKeys(password,pwd);
    }
    public void clickSubmit()
    {
        click(submit);
    }
    public boolean isErrorMessageDisplayed()
    {
        boolean errorMessage= isDisplayed(errorMsg);
        return errorMessage;
    }
}
