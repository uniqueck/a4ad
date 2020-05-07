package com.github.a4ad.acceptance.bdd.steps;

import com.github.a4ad.acceptance.bdd.pageobjects.DuckduckgoHomePage;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StepsSearchEngine extends ParentSteps {

    private DuckduckgoHomePage homePage;

    @Given("^I am on the search engine home page \"([^\"]*)\"$")
    public void i_am_on_the_search_engine_home_page(String url) throws Throwable {
        webdriver.get(url);
        homePage = new DuckduckgoHomePage(webdriver);
    }

}
