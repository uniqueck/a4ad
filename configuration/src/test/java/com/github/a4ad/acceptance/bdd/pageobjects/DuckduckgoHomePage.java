package com.github.a4ad.acceptance.bdd.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DuckduckgoHomePage extends PageObject {

	@FindBy(xpath="//*[@id=\"search_form_input_homepage\"]")
	private WebElement searchField;
	
	@FindBy(xpath="//*[@id=\"search_button_homepage\"]")
	private WebElement searchButton;
	
	public DuckduckgoHomePage(WebDriver driver) {
		super(driver);
	}
	
	public void inputText(String textToSearch) {
		searchField.sendKeys(textToSearch);
	}
	
	public DuckduckgoResultsPage clickOnSearchButton() {
		searchButton.click();
		return new DuckduckgoResultsPage(webdriver);
	}
}