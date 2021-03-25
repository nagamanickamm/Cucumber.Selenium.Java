package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "rerun:target/failed2.txt"
        },
        features = {"@target/failed.txt"},
        tags = "@Smoke or @NoSmoke",
        glue = {"com.test.stepdefinition"})
public class ReRunner {
}