package acceptance.bdd.steps;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

public abstract class ParentSteps {

    @Autowired
    protected boolean screenshotOnFailure;

    @Autowired
    protected String screenshotDestinationFolder;


}
