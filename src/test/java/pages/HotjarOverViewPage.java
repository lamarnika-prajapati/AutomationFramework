package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static org.tcs.webdriver.WaitHelper.*;

public class HotjarOverViewPage {
    WebDriver driver;
    Wait<WebDriver> wait=getWait();

    By inviteTeamIcon=By.xpath("//a[@href=\"/team/invite/\"]");

    public HotjarOverViewPage(WebDriver driver)
    {
        this.driver=driver;
    }

    public void clickInviteTeamIcon()
    {
        waitForClickable(inviteTeamIcon).click();
    }

}
