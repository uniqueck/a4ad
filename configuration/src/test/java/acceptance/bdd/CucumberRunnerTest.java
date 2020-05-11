package acceptance.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:/acceptance/bdd",
                plugin = {"pretty", "html:target/cucumber"})
public class CucumberRunnerTest {
}
