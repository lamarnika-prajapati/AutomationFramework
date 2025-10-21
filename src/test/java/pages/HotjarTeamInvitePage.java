package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.tcs.webdriver.BasePage;

import java.util.List;
import java.util.Map;

import static org.tcs.webdriver.WaitHelper.getWait;
import static org.tcs.webdriver.WaitHelper.waitForVisibility;

public class HotjarTeamInvitePage extends BasePage {
    WebDriver driver;
    String xpathVariable="//input[@name='email0']";
    Wait<WebDriver> wait=getWait();

    By selectOrganization= By.xpath("//select[@data-testid='team-invite-page-organization-selector']");

    By userEmailAddress=By.xpath("//input[@type=\"email\"]");

    By selectAccessLevel=By.xpath("//select[@aria-label=\"user permission\"]");

    By sendInviteButton=By.xpath("//span[text()=\"Send Invites\"]");

    By sendInvitationMessage=By.xpath("//p[text()=\"Invitations sent.\"]");


    public HotjarTeamInvitePage(WebDriver driver)
    {
        this.driver=driver;
    }

    public void selectionOfOrganization()
    {
        WebElement selectOrg=waitForVisibility(selectOrganization);
        Select option=new Select(selectOrg);
        option.selectByVisibleText("Personal ");

    }
   /* public void addUserEmailAddress(List<String> userEmailAddresses)
    {
        List<WebElement> userMailAdd=driver.findElements(this.userEmailAddress);

        for(int i=1;i<userEmailAddresses.size();i++) {

            userMailAdd.get(i+1).sendKeys(userEmailAddresses.get(i));
        }
    }*/
    public void addUserEmailAddressViaExcel(Map<String,String> userEmailAddresses)
    {
//        int count=0;
//        List<WebElement> userEmailAdd=driver.findElements(this.userEmailAddress);
//        for(Map<String,String> userData: userEmailAddresses)
//        {
//
//            userEmailAdd.get(count).sendKeys(userData.get("Email Address"));
//            System.out.println("row: "+count+ " "+userData.get("Email Address"));
//            count++;
//        }
    }
   /* public void selectionOfAccessLevel(List<String> userEmailAddresses)
    {
        for(int i=1;i<userEmailAddresses.size();i++) {
            WebElement selectAccess = wait.until(ExpectedConditions.visibilityOf(selectAccessLevel.get(i-1)));
            Select option = new Select(selectAccess);
            option.selectByVisibleText("Read & Write");
        }

    }*/
    public void selectionOfAccessLevelViaExcel(List<Map<String,String>> userEmailAddresses)
    {
        int count=0;
        List<WebElement> selectAccessLvl=driver.findElements(selectAccessLevel);
        for(Map<String,String> userData: userEmailAddresses)
        {
            WebElement selectAccess = wait.until(ExpectedConditions.visibilityOf(selectAccessLvl.get(count)));
            Select option = new Select(selectAccess);
            option.selectByVisibleText("Read & Write");
            count++;
        }

    }
    public void clickSendInviteButton()
    {
        click(sendInviteButton);

    }
    public boolean isSentInvitationMessage()
    {
        boolean isDisplayed=false;
        WebElement sentInviteBtn=driver.findElement(sendInvitationMessage);
        try {
            isDisplayed = wait.until(ExpectedConditions.visibilityOf(sentInviteBtn)).isDisplayed();
        }catch (TimeoutException e)
        {
            return isDisplayed;
        }
        return isDisplayed;
    }


}
