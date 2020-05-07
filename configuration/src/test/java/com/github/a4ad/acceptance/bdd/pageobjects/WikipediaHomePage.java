package com.github.a4ad.acceptance.bdd.pageobjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Slf4j
public class WikipediaHomePage extends PageObject {

    @FindBy(xpath="//*[@id=\"searchInput\"]")
    private WebElement searchField;

    @FindBy(xpath="/html/body/div[6]/div")
    private WebElement resultSuggestions;

    public WikipediaHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void searchText(String input) {
        searchField.sendKeys(input);
    }

    public void selectTheTechnicalResult() {
        List<WebElement> links = getSuggestionLinks();

        for (WebElement link: links) {
            String linkText = link.getText();
            log.debug("link text: " + linkText);
            if(linkText.contains("software") || linkText.contains("language") || linkText.contains("IT")) {
                // somehow the click() on an embedded link in a div doesn't work with gecko driver (but works with chrome driver).
                Actions act = new Actions(webdriver);
                act.moveToElement(link).click().perform();
//				link.click();
                return;
            }
        }

    }

    public void selectSuggestionAtIndex(int index) {
        List<WebElement> links = getSuggestionLinks();
//		links.get(index - 1).click();

        Actions act = new Actions(webdriver);
        WebElement link = links.get(index - 1);
        act.moveToElement(link).click().perform();
    }

    public List<WebElement> getSuggestionLinks() {
        return resultSuggestions.findElements(By.tagName("a"));
    }
}
