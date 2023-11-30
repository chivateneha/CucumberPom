package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.automation.pages.BasePage;

public class LoginPage extends BasePage{
		@FindBy(xpath="//*[@id=\"username\"]") WebElement userNameElement;
		@FindBy(id="password") WebElement passwordElement;
		@FindBy(id="Login") WebElement loginButtonElement;
		@FindBy(id="rememberUn") WebElement RememberMeElement;
		@FindBy(id="forgot_password_link") WebElement ForgotPasswordElement;
		@FindBy(xpath="//*[@id=\"error\"]") WebElement EmptyPWLoginErrorElement;
		@FindBy(xpath="//*[@id=\"usernamegroup\"]/label") WebElement LoginPageElement; 
		@FindBy(xpath="//*[@id=\"error\"]") WebElement invalidPWandUNLoginErrorElement;
		
		
		public LoginPage(WebDriver driver) {
			super(driver);
		}
		
		public void enterUserName(String data) {
			enterText(userNameElement, data, "Username textbox");
		}
		public void enterPassword(String data) {
			enterText(passwordElement, data, "password field");
		}
		
		public WebDriver clickLoginButton() {
			clickElement(loginButtonElement,"login button");
			return driver;
			
		}
		public WebDriver CheckRememberMeCheckbox() {
			clickElement(RememberMeElement,"Remember Me Checkbox");
			return driver;
			
		}
		public WebDriver ClickForgotPasswordLink() {
			clickElement(ForgotPasswordElement,"Forgot your Password link");
			return driver;
			
		}
		public String getTitleOfThePAge() {
			return getPageTitle();
		}
		public String getTextFromEmptyPWLoginerrormessage() {
			waitForVisibility(EmptyPWLoginErrorElement, 5,"Empty password login error message");
			String data= getTextFromWebElement(EmptyPWLoginErrorElement, "Empty password login error message");
			log.info("text extracted from registartion page="+data);
			return data;
		}
		
		public String getTextFrominvalidPWandUNLoginErrormessage() {
			waitForVisibility(invalidPWandUNLoginErrorElement, 5,"invalid Password and username Login Error message");
			String data= getTextFromWebElement(invalidPWandUNLoginErrorElement, "invalid Password and username Login Error message");
			log.info("text extracted from registartion page="+data);
			return data;
		}
		
		public String getLoginPage() {
			waitForVisibility(LoginPageElement, 5,"Login Page");
			String data= getTextFromWebElement(LoginPageElement, "Login Page");
			log.info("text extracted from registartion page="+data);
			return data;
		}
		
		
}
