package acceptance.bdd.spring;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.BrowserWebDriverContainer;

@Configuration
@ContextConfiguration(classes = {CucumberProperties.class})
public class CucumberContext {

    @Autowired
    String webbrowser;

    @Bean
    public WebDriver getWebDriver(BrowserWebDriverContainer container) {
        return container.getWebDriver();
    }

    @Bean
    public BrowserWebDriverContainer browserWebDriverContainer() {
        BrowserWebDriverContainer browserWebDriverContainer = new BrowserWebDriverContainer();
        switch (webbrowser) {
            case "firefox":
                browserWebDriverContainer.withCapabilities(new FirefoxOptions());
                break;
            case "chrome":
                browserWebDriverContainer.withCapabilities(new ChromeOptions());
                break;
            case "opera":
                browserWebDriverContainer.withCapabilities(new OperaOptions());
                break;
        }
        return browserWebDriverContainer;
    }
}
