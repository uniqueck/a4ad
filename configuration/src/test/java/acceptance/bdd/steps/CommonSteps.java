package acceptance.bdd.steps;

import acceptance.bdd.pageobjects.IndexPage;
import acceptance.bdd.pageobjects.NavBar;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CommonSteps extends ParentSteps {

    @Autowired
    IndexPage indexPage;

    @Autowired
    NavBar navBar;

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        indexPage.navigate();
    }

    @When("I click on the menu entry {string}")
    public void iClickOnTheMenuEntry(String menuEntry) {
        navBar.clickOnMenuEntry(menuEntry);
    }


}
