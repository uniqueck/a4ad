package acceptance.bdd.pageobjects;

import io.cucumber.spring.CucumberTestContext;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class ServerPage extends PageObject {

    public void checkPageTitle(String title) {
        WebElement webElement = webdriver.findElement(By.xpath("//div[@class=\"container-fluid\"]/h1"));
        Assert.assertEquals(title, webElement.getText());
    }

}
