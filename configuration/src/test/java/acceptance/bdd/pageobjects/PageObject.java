package acceptance.bdd.pageobjects;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

public class PageObject {

    @Autowired
    protected WebDriver webdriver;

    @LocalServerPort
    protected int port;

    protected void navigateTo(String path) {
        webdriver.get("http://localhost:" + port + path);
    }



}
