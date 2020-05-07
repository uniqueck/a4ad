package com.github.a4ad.acceptance.bdd.steps;

import com.github.a4ad.acceptance.bdd.pageobjects.WikipediaHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StepsSearchOnWikipedia extends ParentSteps {

    private WikipediaHomePage wikiHomePage;

    @Given("^I type a string \"([^\"]*)\"$")
    public void i_type_a_string(String article) throws Throwable {
        wikiHomePage = new WikipediaHomePage(webdriver);
        wikiHomePage.searchText(article);
    }

    @When("^I select the suggestion at the index (\\d+)$")
    public void i_select_the_suggestion_at_the_index(int index) throws Throwable {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("suggestions-results")));
        wikiHomePage.selectSuggestionAtIndex(index);
    }

    @When("^I select the technical suggestion$")
    public void i_select_the_technical_suggestion() throws Throwable {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("suggestions-results")));
        wikiHomePage.selectTheTechnicalResult();
    }

    @Then("^I should redirected to the \"([^\"]*)\" article$")
    public void i_should_redirected_to_the_article(String article) throws Throwable {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"firstHeading\"]")));
        Assert.assertEquals("https://en.wikipedia.org/wiki/" + article, webdriver.getCurrentUrl());
    }

}
