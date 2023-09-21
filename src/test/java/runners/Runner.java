package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
        glue = "steps",
        plugin = "json:target/json-report/cucumber-report.json")
//        tags = "@DeletePlace")

public class Runner {
}
