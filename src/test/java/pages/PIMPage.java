package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PIMPage {
    private WebDriver driver;
    @FindBy(linkText = "Add Employee")
    private WebElement addEmployeeButton;

    @FindBy(linkText = "Employee List")
    private WebElement employeeListButton;

    @FindBy(xpath = "//button[text()=\" Add \"]")
    private WebElement addButton;

    public PIMPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void clickAddEmployeeTab()
    {
        addEmployeeButton.click();
    }
    public void clickEmployeeList()
    {
        System.out.println(driver.getCurrentUrl());
        employeeListButton.click();
    }
    public void clickAdd()
    {
        System.out.println("inside add Button");
        System.out.println("get currnt page url: "+driver.getCurrentUrl());
        addButton.click();
    }

}
