package acceptance.bdd.steps;

import acceptance.bdd.spring.CucumberContext;
import acceptance.bdd.spring.CucumberProperties;
import io.cucumber.java.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {CucumberProperties.class, CucumberContext.class})
@EnableAutoConfiguration
@Ignore
@ComponentScan(basePackages = "acceptance.bdd")
public class CucumberContextConfiguration {

    @Before
    public void setup_cucumber_spring_context(){
        // Dummy method so cucumber will recognize this class as glue
        // and use its context configuration.
    }
}
