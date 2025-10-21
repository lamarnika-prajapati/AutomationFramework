package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tcs.webdriver.BasePage;
import org.tcs.webdriver.ElementHelper;
import org.tcs.webdriver.WaitHelper;

public class HotjarLoginPage extends BasePage {
    WebDriver driver;
    WaitHelper waits;

    By emailAddress= By.xpath("//input[@id='email']");

    By password= By.xpath("//input[@id='password']");

    By signIn=By.xpath("//button[@id='submit']");

    public HotjarLoginPage(WebDriver driver)
    {
        this.driver=driver;
    }

    public void enterEmailAddress(String emailAddress)
    {
        sendKeys(this.emailAddress,emailAddress);
    }
    public void enterPassword(String password)
    {
        sendKeys(this.password, password);
    }
    public void clickLoginButton()
    {
        click(signIn);
    }

}
