package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/PlaytechWorkflows.feature"},
        tags = "@Smoke or @NoSmoke",
        glue = {"com.test.stepdefinition"})
public class Runner {
}