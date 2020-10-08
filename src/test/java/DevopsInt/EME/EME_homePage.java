package DevopsInt.EME;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.EME_Login;
import pageObjects.EME_forgotPassword;
import pageObjects.EME_landing;


public class EME_homePage extends Base
{
	public static Logger log =LogManager.getLogger(Base.class.getName()); //To create a object for logging the script log
	public WebDriver driver;//to run test parrelly webdriver defined publically here
	EME_landing el; //Declaring class globally hence every test(method can access the class)
	
	@BeforeTest
	public void initialise() throws IOException
	{
		driver = initialzeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		log.info("llog"); 
		log.debug("Debug");
		log.error("Errpr");
	}
	
	@Test(priority=1)
	public void EME_landingPageChecks() throws IOException 
	{
		System.out.println(prop.getProperty("Devuser"));
		el = new EME_landing(driver);
		Assert.assertEquals(el.verifyPageHeader(), "EME");
		Assert.assertEquals(el.verifyWelcomeText().getText(), "Excess Materials Exchange!");
		Assert.assertTrue(el.getLogovalidation().isDisplayed());
		el.Explore().click();
		log.info("Verified EME logo");
	}
	
	@Test(priority=2)
	public void EME_quickScan() throws IOException 
	{
		Assert.assertEquals(el.verifyProductText().getText(), "Explore EME Products");
		//Assert.assertEquals(el.EME_quickscan().getText(), "EME Quick Scan");//Bug: Title should be EME Internal Marketplace
		el.EME_quickscan().click();
		//if(isboolen.(Assert.assertEquals(driver.getTitle(), "EME Quick Scan"))
		{
			
		}
		driver.navigate().back();
	}
	
	@Test(priority=3)
	public void EME_ResourcePassport() throws IOException 
	{
		//Assert.assertEquals(el.EME_quickscan().getText(), "EME Quick Scan");
		el.EME_resourcepassport().click();
		Assert.assertEquals(driver.getTitle(), "EME");//Bug: Title should be EME Resourse Passport.
		driver.navigate().back();
	}
	
	@Test(priority=4)
	public void EME_InternalMrktplce() throws IOException 
	{
		//Assert.assertEquals(el.EME_quickscan().getText(), "EME Quick Scan");
		el.EME_InternalMarketplce().click();
		Assert.assertEquals(driver.getTitle(), "EME");//Bug: Title should be EME Internal Marketplace
		driver.navigate().back();
	
	}
	
		
	@AfterTest
	public void teardown()
	{
		driver.close();
	}

}

