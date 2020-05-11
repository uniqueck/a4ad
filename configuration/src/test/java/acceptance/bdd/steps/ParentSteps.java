package acceptance.bdd.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

public abstract class ParentSteps {

    @Autowired
    protected WebDriver webdriver;

    @Autowired
    protected WebDriverWait wait;

    @LocalServerPort
    protected int port;

    @Autowired
    protected boolean screenshotOnFailure;

    @Autowired
    protected String screenshotDestinationFolder;

}
