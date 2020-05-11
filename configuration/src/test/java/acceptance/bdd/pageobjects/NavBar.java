package acceptance.bdd.pageobjects;

import io.cucumber.spring.CucumberTestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class NavBar extends PageObject {

    public void clickOnMenuEntry(String menuEntry) {
        Optional<WebElement> webElement = webdriver.findElements(By.className("nav-link")).stream().filter(wE -> wE.getText().equals(menuEntry)).findFirst();
        webElement.ifPresent(WebElement::click);
    }

}
