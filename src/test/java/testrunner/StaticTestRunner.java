package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/PetStoreComCopy.feature"},
        glue = {"stepdefinitions"},
        //tags = "@TagArriveFilter or @TagDurationFilter or @TagPriceFilter",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true

)
public class StaticTestRunner {
}
