package com.automation.Stepdefination;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.ForgotPasswordPage;
import com.automation.tests.utilities.Log4JUtility;
import com.automation.tests.utilities.PropertiesUtility;


import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class StepDef {
			protected static Logger log;
		public static WebDriver driver;
		protected static Log4JUtility logObject=Log4JUtility.getInstance();
		LoginPage login;
		HomePage home;
		ForgotPasswordPage forgotpw;
		
		
		public  void launchBrowser(String browserName) {
			switch(browserName) {
			case "firefox":
				
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				
				break;
			case "chrome":
							driver = new ChromeDriver();
				driver.manage().window().maximize();
				
				break;
			}
			System.out.println(browserName+" browser opened");
		}
		
		public  void goToUrl(String url) {
			
			driver.get(url);
			log.info(url+ "is entered");
		}

		public  void closeBrowser() {
			driver.close();
			log.info("current browser closed");
		}
		
		@BeforeAll
		public static void setUpBeforeAllScenarios() {
			log=logObject.getLogger();
		}
		@Before
		public void setUpEachScenario() {
			
			launchBrowser("chrome");
			
		}
		@After
		public void tearDown() {
			closeBrowser();
		}
		@AfterStep
		public void after_each_step(Scenario sc) {
			
		}
		
		@Given("User open salesforce application {string}")
		
		public void user_open_salesforce_application(String string) {
			PropertiesUtility pro=new PropertiesUtility();
			Properties p=pro.createPropertyObject();
			pro.loadFile("applicationDataProperties",p);
			goToUrl(string);
			
		}

		@When("user on {string}")
		public void user_on(String page) {
			 if(page.equalsIgnoreCase("loginpage")) {
			    	login=new LoginPage(driver);
			 System.out.println("login page object has been created is ="+login);}
			    else if(page.equalsIgnoreCase("homepage")) {
			    	home=new HomePage(driver);
			    }
			    	
			 
			    else if(page.equalsIgnoreCase("ForgotpasswordPage")) {
			    	 forgotpw= new ForgotPasswordPage(driver);
			    	 System.out.println("login page object has been created is ="+forgotpw);
			    }
			       
			    
		}
		

		@When("User enters value into text box username as {string}")
		public void user_enters_value_into_text_box_username_as(String userId) throws InterruptedException {
			//Thread.sleep(5000);
			//login = new LoginPage(driver); 
			login.enterUserName(userId);
		}

		@When("User enters value into text box password as {string}")
		public void user_enters_value_into_text_box_password_as(String password) {
		    login.enterPassword(password);
		}

		@When("Click on Login button")
		public void click_on_login_button() {
			driver= login.clickLoginButton();
		}
		
		@Then("verify we can see {string} error message")
		public void verify_we_can_see_error_message(String expected) {
			
				String actual=login.getTextFromEmptyPWLoginerrormessage();
				Assert.assertEquals(actual, expected,"Pass: testcase passed");
		}

		

		@Then("verify we can see {string} page")
		public void verify_we_can_see_page(String expected) {
			String actual=home.getTextFromHomePage();
			Assert.assertEquals(actual, expected, "Pass: testcase passed");
		}
		
		@When("User selects remember me check box")
		public void user_selects_remember_me_check_box() {
			login.CheckRememberMeCheckbox();
		}
		
		@When("User clicks on NehaABCD")
		public void user_clicks_on_Neha_ABCD_tab() {
			home.SelectTabNehaABCD();
		}

		@When("User clicks Logout button")
		public void user_clicks_logout_button() {
			driver = home.clickLogoutButton();    
		}

		@Then("verify we can see {string} populated with the username")
		public void verify_we_can_see_tab(String expected2) {
			String actual2=login.getLoginPage();
			Assert.assertEquals(actual2, expected2, "Pass: testcase passed");
		}

		
		@When("Click on forgot password link")
		public void click_on_forgot_password_link() {

			driver = login.ClickForgotPasswordLink();
			ForgotPasswordPage forgotpw= new ForgotPasswordPage(driver);
		
		}
		@Then("verify that we can see {string} on forgot password page")
		public void verify_that_we_can_see_on_forgot_password_page(String expected) {
		    // Write code here that turns the phrase above into concrete actions
			String actual=forgotpw.getTextFromForgotPasswordPageElement();
			Assert.assertEquals(actual, expected, "Pass: testcase passed");
		}
		
		@Then("User enters value into text box username as {string} on forgot password page")
		public void user_enters_value_into_text_box_username_as_on_forgot_password_page(String string) {
			forgotpw.enterUserName(string);  
		}

		@When("User clicks the continue button")
		public void user_clicks_the_continue_button() {
			forgotpw.clickContinueButton();
		}
		
		@Then("verify we can see Error message2 {string}")
		public void verify_we_can_see_error_message2(String expected) {
			String actual= login.getTextFrominvalidPWandUNLoginErrormessage();
			Assert.assertEquals(actual, expected,"Pass: testcase passed");
		}
}
