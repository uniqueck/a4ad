package acceptance.bdd.pageobjects;

import io.cucumber.spring.CucumberTestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class IndexPage extends PageObject {

    public void navigate() {
        navigateTo("/");
    }

}
