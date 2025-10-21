package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tcs.webdriver.BasePage;

public class DashboardPage extends BasePage {
    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pmiButton;

    By dashboardText=By.xpath("//h6[text()=\"Dashboard\"]");

    public DashboardPage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    public void clickPMIButton()
    {
        pmiButton.click();
    }

    public boolean isDashboardButtonDisplayed()
    {
        return isDisplayed(dashboardText);
    }
}
