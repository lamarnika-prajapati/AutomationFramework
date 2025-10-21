package testrunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = {"src/test/resources/features/YatraComApplication.feature"},
        glue = {"stepdefinitions"},
        //tags = "@TestCase1",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class ParallelTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)   // 🔑 Enables parallel scenario execution
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
