package stepdefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.tcs.readers.ExcelReader;
import org.tcs.webdriver.AssertHelper;
import org.tcs.webdriver.BasePage;
import pages.DashboardPracticeSitePage;
import pages.LoginPracticePage;

import java.util.List;
import java.util.Map;

import static org.tcs.webdriver.DriverManager.getDriver;

public class LoginPracticeSiteSteps extends BasePage {
    WebDriver driver = getDriver();
    LoginPracticePage loginPracticePage = new LoginPracticePage(driver);
    DashboardPracticeSitePage dashboardPracticeSitePage = new DashboardPracticeSitePage(driver);
    String loginTestDataExcel = "src/test/resources/TestData/UserCred.xlsx";

    @Given("User is on Login Page of Practice site")
    public void user_is_on_login_page_of_practice_site() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @When("User enter the username and password using {string} data")
    public void userEnterTheUserNameAndPassword(String testCaseName) {
        String sheetName = "UserEmails";
        Map<String, String> dataFromExcel = ExcelReader.getTestCaseData(loginTestDataExcel, sheetName, testCaseName);
        System.out.println("Print data from excel: "+dataFromExcel);
        loginPracticePage.enterUserName(dataFromExcel.get("UserName"));
            loginPracticePage.enterPassword(dataFromExcel.get("Password"));
    }

    @When("User click to Login button")
    public void user_click_to_login_button() {
        loginPracticePage.clickSubmit();
    }

    @Then("User successfully is navigated to the Dashboard page")
    public void user_successfully_is_navigated_to_the_dashboard_page() {
        boolean successMessage = dashboardPracticeSitePage.isSuccessfullyMessageDisplayed();
        AssertHelper.assertTrue(successMessage, "Logged in not successfully");
        System.out.println("Logged in successfully");
    }


    @Then("User successfully is navigated to the error message")
    public void userSuccessfullyIsNavigatedToTheErrorMessage() {
        boolean errorMessageDisplayed = loginPracticePage.isErrorMessageDisplayed();
        AssertHelper.assertTrue(errorMessageDisplayed, "Error message was not displayed");
        System.out.println("Error message was successfully displayed");
    }
}
