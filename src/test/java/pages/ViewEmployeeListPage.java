package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static org.tcs.webdriver.WaitHelper.getWait;


public class ViewEmployeeListPage {
    private WebDriver driver;
    private Wait<WebDriver> wait=getWait();
    @FindBy(xpath = "//div[@class=\"oxd-form-row\"]/div/div[1]/div/div/div[@class=\"oxd-autocomplete-wrapper\"]/div[@class=\"oxd-autocomplete-text-input oxd-autocomplete-text-input--active\"]/input[1]")
    private WebElement empName;

    @FindBy(xpath = "//button[text()=\" Search \"]")
    private WebElement searchButton;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span\"]")
    private WebElement recordFoundMessage;


    public ViewEmployeeListPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void enterEmployeeName(String firstName)
    {
        empName.sendKeys(firstName);
    }
    public void clickSearchButton()
    {
        searchButton.click();
    }

    public String searchEmployeeInTheList(String firstName)
    {
        enterEmployeeName(firstName);
        clickSearchButton();
        String recordFoundMsg=wait.until(ExpectedConditions.visibilityOf(recordFoundMessage)).getText();
        return recordFoundMsg;
    }
}
