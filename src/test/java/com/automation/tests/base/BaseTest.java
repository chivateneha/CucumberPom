package com.automation.tests.base;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.automation.tests.utilities.ExtentReportsUtility;
import com.automation.tests.utilities.Log4JUtility;
import com.automation.tests.utilities.PropertiesUtility;
import com.google.common.io.Files;

public class BaseTest {
	protected static WebDriver driver = null;
	protected static WebDriverWait wait = null;
	protected Log4JUtility logObject=Log4JUtility.getInstance();
	protected static Logger myLog;
	protected ExtentReportsUtility report=ExtentReportsUtility.getInstance();
	
	@BeforeTest
	public void setupBeforeTest() {
		myLog=logObject.getLogger();
	}
	@BeforeMethod
	@Parameters("browsername")
	public void setUpBeforeTestMethod(@Optional("chrome") String browser_name) {
		myLog.info("******Salesforce script Started***********");
		PropertiesUtility pro1 = new PropertiesUtility();
		Properties p = pro1.createPropertyObject();
		pro1.loadFile("applicationData", p);
		String url = pro1.getPropertyValue("url", p);
		launchBrowser(browser_name);
		maximiseBrowser();
		goToUrl(url);
		
	}

	@AfterMethod
	public void tearDownAfterTestMethod() {
		driver.close();
		myLog.info("******Salesforce script ended***********");
	}
	
	
	public WebDriver getDriverInstance() {
		return driver;
	}

	public void launchBrowser(String browserName) {
		switch (browserName) {
		case "firefox":
			driver = new FirefoxDriver();
			myLog.info("firefox browser launched");
			//report.logTestInfo("firefox browser launched");
			break;
		case "chrome":
			driver = new ChromeDriver();
			myLog.info("chrome browser launched");
			//report.logTestInfo("chrome browser launched");
			break;
		default:
			myLog.error("you have not entrered the correct browser");
			//report.logTestFailed("you have not entrered the correct browser");

		}
	}

	public void goToUrl(String url) {
		driver.get(url);
		myLog.info(url + "is entered");
		//report.logTestInfo(url + "is entered");
	}

	public void maximiseBrowser() {
		driver.manage().window().maximize();
		//myLog.info("browser window has maximized");
	}

	
	
	public String takescreenshot(String filepath) {
		 TakesScreenshot screenCapture=(TakesScreenshot)driver;
		 File src=screenCapture.getScreenshotAs(OutputType.FILE);
		 File destination=new File(filepath);
		 try {
			Files.copy(src, destination);
			myLog.info("captured the screen");
			report.logTestInfo("captured the screen");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			myLog.error("captured the screen");
			report.logTestFailedWithException(e);
		}
		return filepath;
	}	 
//		 takescreenshots(WebElement element,String filepath1) {
//			 File src1=element.getScreenshotAs(OutputType.FILE);
//			 File destination1=new File(filepath1);
//			 try {
//				Files.copy(src1, destination1);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				myLog.error("captured the screen");
//				report.logTestFailedWithException(e);
//			}
//		}
		
		

		public void quitBrowser(){
			driver.quit();
			
			//driver=null;
			
		}
		
		//*******************Handling alerts reusable methods***********************
		
			public static Alert switchToAlert() {
				
				Alert alert= driver.switchTo().alert();
				System.out.println("switched to alert");
				return alert;
				
			}
			
		
			String getAlertText(Alert alert, String ObjectName) {
				
				System.out.println("Extract text in the "+ ObjectName);
				String text=alert.getText();
				System.out.println("Text extracted from alert box is : "+ text);
				return text;
			}

			
			public static void dismisAlert() {
				
				Alert alert1 = switchToAlert();
				alert1.dismiss();
				System.out.println("Alert dismissed");
			}
				
			
			
			
			//****************Select class reusable method starts**********************
			
			public static void SelectfromDropDown (WebElement i, int string) {
				  Select select = new Select (i);
				  select.selectByIndex(string);
		}

			
			//****************Select class reusable method ends**********************
			
			
			public static void waitForVisibility(WebElement ele,int time, int pollingtime, String ObjectName) {
				
				FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
				wait.withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(ElementNotInteractableException.class);
					
			}
			
		

}
