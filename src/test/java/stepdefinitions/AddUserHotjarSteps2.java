package stepdefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.tcs.readers.ExcelReader;
import pages.HotjarLoginPage;
import pages.HotjarOverViewPage;
import pages.HotjarTeamInvitePage;

import java.util.List;
import java.util.Map;

import static org.tcs.webdriver.DriverManager.getDriver;

public class AddUserHotjarSteps2 {
    WebDriver driver = getDriver();
    HotjarLoginPage hotjarLoginPage = new HotjarLoginPage(driver);
    HotjarOverViewPage hotjarOverViewPage = new HotjarOverViewPage(driver);
    HotjarTeamInvitePage hotjarTeamInvitePage=new HotjarTeamInvitePage(driver);

    @Given("I am on Hotjar login portal")
    public void i_am_on_hotjar_login_portal() {
        driver.get("https://insights.hotjar.com/login");
    }

    @When("enter username {string} and password {string}")
    public void enter_username_and_password(String emailAddress, String password) {

        hotjarLoginPage.enterEmailAddress(emailAddress);
        hotjarLoginPage.enterPassword(password);
        System.out.println("Entered user name and password");

    }

    @And("click to Sign In button")
    public void click_to_sign_in_button() {
        hotjarLoginPage.clickLoginButton();
    }

    @And("navigate to invite people page")
    public void navigate_to_invite_people_page() {
        hotjarOverViewPage.clickInviteTeamIcon();
    }

    @And("enter user email from excel using sheet {string} TestCase {string} and select the access level")
    public void enterUserEmailFromExcelUsingSheetColumnTestCaseAndSelectTheAccessLevel(String sheetName, String testCaseName) {
        String filepath="src/test/resources/TestData/UserTestData.xlsx";
        Map<String, String> data = ExcelReader.getTestCaseData(filepath, sheetName, testCaseName);
        hotjarTeamInvitePage.selectionOfOrganization();
        hotjarTeamInvitePage.addUserEmailAddressViaExcel(data);
        //hotjarTeamInvitePage.selectionOfAccessLevelViaExcel(data);
    }

    @And("click on Send invites button")
    public void click_on_send_invites_button() {
        hotjarTeamInvitePage.clickSendInviteButton();
    }

    @Then("sent invitation message is displayed")
    public void sent_invitation_message_is_displayed() {
        boolean isSentInvitationMsgDisplayed= hotjarTeamInvitePage.isSentInvitationMessage();
        System.out.println(isSentInvitationMsgDisplayed);
        Assert.assertTrue("Invitation message was not sent to the user",isSentInvitationMsgDisplayed);
        System.out.println("Invitation was sent to the user");

    }


}
