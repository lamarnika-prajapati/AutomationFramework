package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import pages.DashboardPage;
import pages.LoginPage;

import static org.tcs.webdriver.DriverManager.getDriver;

public class LoginSiteSteps {
    private static final Logger logger = LogManager.getLogger(LoginSiteSteps.class);

    LoginPage loginPage = new LoginPage(getDriver());
    DashboardPage dashboardPage=new DashboardPage(getDriver());

    @Given("User is on Login Page")
    public void user_is_on_login_page() {
        logger.info("Navigating to login page");

        getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @When("User enter Username {string} and Password {string}")
    public void user_enter_username_and_password(String username, String password) {
        System.out.println("Driver is: "+getDriver()+" "+Thread.currentThread().getId());
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
    }
    @And("User click to Login page")
    public void user_click_to_login_page() {
        logger.info("clicking to login button");

        loginPage.clickLoginButton();
    }
    @Then("User successfully is navigated to Dashboard page")
    public void user_successfully_is_navigated_to_dashboard_page() {
        boolean isDashboardVisible= dashboardPage.isDashboardButtonDisplayed();
        Assert.assertTrue("User is not successfully logged in",isDashboardVisible);
        System.out.println("User logged in successfully");
    }


    @Given("User click to forgot password")
    public void userClickToForgotPassword() {
        System.out.println("Driver is: "+getDriver()+" "+Thread.currentThread().getId());
        loginPage.clickForgotPasswordButton();
        logger.info("clicked to forgot password button");
    }


    @Then("user is navigated to reset password page")
    public void userIsNavigatedToResetPasswordPage() {
        String currentPageURL=getDriver().getCurrentUrl();
        System.out.println(currentPageURL);
    }

}
