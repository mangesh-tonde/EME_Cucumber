package DevopsInt.EME;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import junit.framework.Assert;
import pageObjects.EME_Login;
import pageObjects.EME_forgotPassword;
import pageObjects.EME_landing;

public class EME_loginPage extends Base
{
	public static Logger log=  LogManager.getLogger(Base.class.getName()); //To create a object for logging the script log
	public WebDriver driver;//to run test parrelly webdriver defined publically here
	@BeforeMethod
		public void initialise() throws IOException
	{
		driver = initialzeDriver();
		driver.get(prop.getProperty("url"));
		log.info("Launched URL");
	}
	
	@Test(priority=1)
	public void invalidUsername() throws IOException, InterruptedException 
	{       
		EME_landing el = new EME_landing(driver);
		Assert.assertTrue(el.getLogovalidation().isDisplayed());
		log.info("Verified EME logo");
		EME_Login elog = el.getLogin(); //This method getlogin will click on Login button on landing button
		Assert.assertEquals(driver.getTitle(), "EME");//Bug: Title should be EME Login Internal Marketplace
		elog.getUsername().sendKeys(prop.getProperty("Devuser1"));
		elog.getSignin().click();
		Thread.sleep(1000);
		log.info(driver.findElement(By.xpath("//div[@class='ant-message-custom-content ant-message-error']/span")).getText());
		Thread.sleep(2000);
	} 
	
	@Test
	public void invalidPassword() throws IOException, InterruptedException 
	{       
		EME_landing el = new EME_landing(driver);
		Assert.assertTrue(el.getLogovalidation().isDisplayed());
		log.info("Verified EME logo");
		EME_Login elog = el.getLogin(); //This method getlogin will click on Login button on landing button
		Assert.assertEquals(driver.getTitle(), "EME");//Bug: Title should be EME Login Internal Marketplace
		elog.getUsername().sendKeys(prop.getProperty("Devuser"));
		elog.getPassword().sendKeys(prop.getProperty("Devpass1"));
		//assertion to verify error message
		elog.getSignin().click();
		Thread.sleep(1000);
		log.info(driver.findElement(By.xpath("//div[@class='ant-message-custom-content ant-message-error']/span")).getText());
		Thread.sleep(1000);
	} 
	
	@AfterMethod
	public void teardown()
	{
		driver.close();
	}
}
