package com.github.a4ad.acceptance.bdd.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DuckduckgoResultsPage extends PageObject {
	
	@FindBy(xpath="//*[@id=\"links\"]")
	private WebElement links;
	
	public DuckduckgoResultsPage(WebDriver driver) {
		super(driver);
	}
	
	public List<WebElement> getAllResults() {
		return links.findElements(By.className("result"));
	}
	
}