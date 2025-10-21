package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.PIMPage;
import pages.ViewEmployeeListPage;

import java.util.List;
import java.util.Map;

import static org.tcs.webdriver.DriverManager.getDriver;
import static org.tcs.webdriver.WaitHelper.getWait;

public class AddEmployeesSteps {
    WebDriver driver = getDriver();
    Wait<WebDriver> wait = getWait();
    DashboardPage dashboardPage = new DashboardPage(driver);
    PIMPage pimPage = new PIMPage(driver);
    AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
    ViewEmployeeListPage viewEmployeeListPage = new ViewEmployeeListPage(driver);

    @When("User Navigate to the PMI Tab")
    public void user_navigate_to_the_pmi_tab() {
        dashboardPage.clickPMIButton();
    }

    @When("User add to the employee")
    public void user_add_to_the_employee(DataTable table) {
        List<List<String>> employeeData = table.asLists();
        pimPage.clickAddEmployeeTab();
        for (int i = 1; i < employeeData.size(); i++) {
            addEmployeePage.fillEmployeeDetails(employeeData.get(i).get(0), employeeData.get(i).get(1), employeeData.get(i).get(2), employeeData.get(i).get(3));

        }


    }

    @Then("Validate the message {string} is displayed")
    public void validate_the_message_is_displayed(String string) {

        boolean isSuccessMessageDisplayed = addEmployeePage.validateAddEmployeeSuccessMessage();
        Assert.assertTrue("Successfully message was not displayed", isSuccessMessageDisplayed);
        System.out.println("successfully message displayed");
        System.out.println(driver.getCurrentUrl());
    }

    @Then("validate the employee is displayed into the employee list")
    public void validate_the_employee_is_displayed_into_the_employee_list(DataTable table) {
        String recordFoundMessage=null;
        Map<String, String> addedEmployee = table.asMap(String.class, String.class);
        pimPage.clickEmployeeList();
        String employeeName=addedEmployee.get("firstName");
        System.out.println("Map row: "+employeeName);
        recordFoundMessage = viewEmployeeListPage.searchEmployeeInTheList(employeeName);
        System.out.println(recordFoundMessage);
        Assert.assertNotEquals("Employee is not added successfully as he/she is not found in the list", "No Records Found", recordFoundMessage);
        System.out.println("employee is added successfully and displayed in the employee list");

    }

}
