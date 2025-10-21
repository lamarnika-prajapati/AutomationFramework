package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static org.tcs.webdriver.WaitHelper.getWait;

public class AddEmployeePage {
    private WebDriver driver;
    private Wait<WebDriver> wait=getWait();
    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement empFirstName;

    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement empMiddleName;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement empLastName;

    @FindBy(xpath = "//div[@class=\"oxd-input-group oxd-input-field-bottom-space\"]/div/input[@class='oxd-input oxd-input--active']")
    private WebElement empID;

    @FindBy(xpath = "//button[text()=\" Save \"]")
    private WebElement saveButton;

    @FindBy(xpath = "//p[text()='Successfully Saved']")
    private WebElement empAddSuccessMessage;

    public AddEmployeePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void enterEmployeeFirstName(String firstName)
    {
        empFirstName.sendKeys(firstName);
    }
    public void enterEmployeeMiddleName(String middleName)
    {
        empMiddleName.sendKeys(middleName);
    }
    public void enterEmployeeLastName(String lastName)
    {
        empLastName.sendKeys(lastName);
    }
    public void enterEmployeeId(String empId)
    {
        empID.clear();
        empID.sendKeys(empId);
    }
    public void clickSaveButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void fillEmployeeDetails(String fname, String mname, String lname, String empid)
    {
        enterEmployeeFirstName(fname);
        enterEmployeeMiddleName(mname);
        enterEmployeeLastName(lname);
        enterEmployeeId(empid);
        clickSaveButton();
    }

    public boolean validateAddEmployeeSuccessMessage(){
        boolean successMessage=false;
        successMessage=wait.until(ExpectedConditions.visibilityOf(empAddSuccessMessage)).isDisplayed();
        return successMessage;
    }




}
