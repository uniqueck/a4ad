package acceptance.bdd.steps;

import acceptance.bdd.pageobjects.ServerPage;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class ServerSteps extends ParentSteps {

    @Autowired
    ServerPage serverPage;

    @Then("I should redirected to the servers page")
    public void iShouldRedirectedToTheServersPage() {
        serverPage.checkPageTitle("Server overview");
    }

}
