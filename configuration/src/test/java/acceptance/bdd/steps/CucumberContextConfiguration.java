package acceptance.bdd.steps;

import acceptance.bdd.spring.CucumberContext;
import acceptance.bdd.spring.CucumberProperties;
import com.github.a4ad.A4adApplication;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.output.OutputFrame;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy;
import org.testcontainers.lifecycle.TestDescription;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={A4adApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {CucumberProperties.class, CucumberContext.class})
@EnableAutoConfiguration
@Ignore
@ComponentScan(basePackages = "acceptance.bdd")
public class CucumberContextConfiguration {

    @Autowired
    BrowserWebDriverContainer container;

    @Before
    public void setup_cucumber_spring_context(){
        container.start();
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.embed(container.getWebDriver().getScreenshotAs(OutputType.BYTES), scenario.getId());
        }


        container.afterTest(new TestDescription() {
            @Override
            public String getTestId() {
                return scenario.getId();
            }

            @Override
            public String getFilesystemFriendlyName() {
                return scenario.getName();
            }
        }, Optional.of(scenario).filter(Scenario::isFailed).map(__ -> new RuntimeException()));
    }



}
