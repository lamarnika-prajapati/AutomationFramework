package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tcs.webdriver.BasePage;
import org.tcs.webdriver.WaitHelper;

public class LoginPage extends BasePage {
    WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(LoginPage.class);

    By userNameLocator= By.xpath("//input[@name='username']");
    By passwordLocator=By.xpath("//input[@name='password']");
    By loginButtonLocator=By.xpath("//button[text()=' Login ']");
    By forgotPasswordLocator=By.xpath("//p[text()='Forgot your password? ']");

    public LoginPage(WebDriver driver) {
        LOGGER.info("Initializing login Page: {}", driver);
        this.driver = driver;
    }

    public void enterUserName(String uname) {
        sendKeys(userNameLocator, uname);
    }

    public void enterPassword(String password) {
        sendKeys(passwordLocator, password);
    }

    public void clickLoginButton() {
        click(loginButtonLocator);
    }

    public void clickForgotPasswordButton() {
        LOGGER.info("WebDriverWaits.getWait(): {}", WaitHelper.getWait());
        click(forgotPasswordLocator);
    }

}
