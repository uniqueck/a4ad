package com.github.a4ad.acceptance.bdd.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage extends PageObject {

    @FindBy(xpath="//a[@class=\"navbar-brand\"]")
    private WebElement appTitle;

    public IndexPage(WebDriver driver) {
        super(driver);
    }
}
