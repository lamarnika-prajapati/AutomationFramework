package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tcs.webdriver.BasePage;

public class PetStoreLoginPage extends BasePage {
    WebDriver driver;
    By loginIcon = By.xpath("//span/parent::a[@href=\"/account\"]");
    By userName = By.xpath("//input[@id=\"CustomerEmail\"]");
    By password = By.xpath("//input[@id=\"CustomerPassword\"]");
    By SignInButton = By.xpath("//button[@type='submit' and contains(text(),\"Sign In\")]");

    public PetStoreLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnLoginIcon() {
        click(loginIcon);
    }

    public void enterUserName(String username) {
        sendKeys(userName, username);
    }

    public void enterPassword(String password) {
        sendKeys(this.password, password);
    }

    public void clickOnSignInButton() {
        click(SignInButton);
    }



}
