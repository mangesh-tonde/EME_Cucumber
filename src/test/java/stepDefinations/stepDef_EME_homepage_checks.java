package stepDefinations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DevopsInt.EME.Base;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.EME_landing;

public class stepDef_EME_homepage_checks extends Base
{

	public static Logger log =LogManager.getLogger(Base.class.getName()); //To create a object for logging the script log
	public WebDriver driver;//to run test parrelly webdriver defined publically here
	EME_landing el; //Declaring class globally hence every test(method can access the class)

	@Given("^Initialise browser with chrome and launch the EME application\\.$")
	public void initialise_browser_with_chrome_and_launch_the_EME_application() throws Throwable {

		driver = initialzeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}

	@Then("^Verify EME logo and page header on EME home page\\.$")
	public void verify_EME_logo_and_page_header_on_EME_home_page() throws Throwable
	{
		System.out.println(prop.getProperty("Devuser"));
		el = new EME_landing(driver);
		Assert.assertEquals(el.verifyPageHeader(), "Home | EME");
		Assert.assertEquals(el.verifyWelcomeText().getText(), "Excess Materials Exchange!");
		Assert.assertTrue(el.getLogovalidation().isDisplayed());
		el.Explore().click();
		log.info("Verified EME logo");

	}

	@Then("^Verify EME logo and page header on EME Quick scan page\\.$")
	public void verify_EME_logo_and_page_header_on_EME_Quick_scan_page() throws Throwable {
		Assert.assertEquals(el.verifyProductText().getText(), "Explore EME Products");
		//Assert.assertEquals(el.EME_quickscan().getText(), "EME Quick Scan");//Bug: Title should be EME Internal Marketplace
		el.EME_quickscan().click();
		//if(isboolen.(Assert.assertEquals(driver.getTitle(), "EME Quick Scan"))
		{
			
		}
		driver.navigate().back();
	}

	@Then("^Verify EME logo and page header on EME Resource passport page\\.$")
	public void verify_EME_logo_and_page_header_on_EME_Resource_passport_page() throws Throwable {
		//Assert.assertEquals(el.EME_quickscan().getText(), "EME Quick Scan");
				el.EME_resourcepassport().click();
				Assert.assertEquals(driver.getTitle(), "Resources Passport | EME");//Bug: Title should be EME Resourse Passport.
				driver.navigate().back();
	}

	@Then("^Verify EME logo and page header on EME Internal Circular Marketplace page\\.$")
	public void verify_EME_logo_and_page_header_on_EME_Internal_Circular_Marketplace_page() throws Throwable {
		//Assert.assertEquals(el.EME_quickscan().getText(), "EME Quick Scan");
				el.EME_InternalMarketplce().click();
				Assert.assertEquals(driver.getTitle(), "Internal Circular Marketplace | EME");//Bug: Title should be EME Internal Marketplace
				driver.navigate().back();
	}

}
