package com.github.a4ad.acceptance.bdd.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ParentSteps {

    @Autowired
    protected WebDriver webdriver;

    @Autowired
    protected WebDriverWait wait;

    @Autowired
    protected boolean screenshotOnFailure;

    @Autowired
    protected String screenshotDestinationFolder;

}
