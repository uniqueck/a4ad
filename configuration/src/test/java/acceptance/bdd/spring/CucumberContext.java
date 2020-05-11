package acceptance.bdd.spring;

import acceptance.bdd.beans.ChromeWebDriver;
import acceptance.bdd.beans.FirefoxWebDriver;
import acceptance.bdd.beans.OperaWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@ContextConfiguration(classes = {CucumberProperties.class})
public class CucumberContext {

    @Autowired
    String webbrowser;

    @Bean(name = "webdriver", destroyMethod = "close")
    public WebDriver getWebDriver() {
        WebDriver webDriver = null;
        switch (webbrowser) {
            case "firefox":
                webDriver= new FirefoxWebDriver();
                break;
            case "chrome":
                webDriver = new ChromeWebDriver();
                break;
            case "opera":
                webDriver = new OperaWebDriver();
                break;
        }
        return webDriver;
    }

    @Bean("wait")
    public WebDriverWait getWebDriverWait() {
        return new WebDriverWait(getWebDriver(), 5);
    }
}
