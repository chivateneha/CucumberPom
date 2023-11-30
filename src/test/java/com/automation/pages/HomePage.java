package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	@FindBy(xpath ="/html/body/div/div[1]/div[1]/div/nav/ul/li[1]/a") WebElement HomePageEle;
	@FindBy(id ="userNavLabel") WebElement MenuEle;
	@FindBy(xpath ="//*[@id='userNav-menuItems']/a[5]") WebElement LogoutEle;
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public String getTextFromHomePage() {
		waitForVisibility(HomePageEle, 5,"Salesforce Home Page");
		String data= getTextFromWebElement(HomePageEle, "Salesforce Home Page");
		log.info("text extracted from home page="+data);
		return data;
	}
	
	
	public WebDriver clickLogoutButton() {
		clickElement(LogoutEle,"Logout button");
		return driver;
		
	}

	public void SelectTabNehaABCD() {
		clickElement(MenuEle,"Selecting Neha Chivate Tab");
		return;
		
	}

}
