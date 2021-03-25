package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "rerun:target/failed.txt"
        },
        features = {"classpath:features/PlaytechWorkflows.feature",
                "classpath:features/PlaytechWorkflows2.feature"},
        tags = "@Smoke or @NoSmoke",
        glue = {"com.test.stepdefinition"})

public class Runner extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios(){
                return super.scenarios();
        }
}
