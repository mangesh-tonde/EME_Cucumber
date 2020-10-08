package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EME_landing {
	public WebDriver driver;
	
	

	private By explore = By.xpath("//div[@class='eme_click_explore_1']");
	private By Login = By.xpath("//a[@href='/login']");
	private By welcomeTxt = By.xpath("//span[text()='Excess Materials Exchange!']");
	private By logo = By.xpath("//img[@class='logo-large-white ']");
	private By producTxt = By.cssSelector("div[class='eme_explore_section_2'] h1");
	private By emequickscan =By.cssSelector(".eme_quick_scan_content");
	private By resourcespass =By.cssSelector(".eme_scan_image_1");
	private By internalMrktplace =By.cssSelector(".eme_scan_image_2");
	
	public EME_landing(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public WebElement Explore()
	{
		return driver.findElement(explore);		
	}
	
	public EME_Login getLogin()
	{
		 driver.findElement(Login).click();
		 EME_Login elog = new EME_Login(driver);
		 return elog;
		 
	}
	public WebElement verifyWelcomeText()
	{
		return driver.findElement(welcomeTxt);
	}
	
	public WebElement getLogovalidation()
	{
		return driver.findElement(logo);
	}
	
	public String verifyPageHeader()
	{
		return driver.getTitle();
	}
	
	public WebElement verifyProductText()
	{
		return driver.findElement(producTxt);
	}
	
	public WebElement EME_quickscan()
	{
		return driver.findElement(emequickscan);
	}
	public WebElement EME_resourcepassport()
	{
		return driver.findElement(resourcespass);
	}
	public WebElement EME_InternalMarketplce()
	{
		return driver.findElement(internalMrktplace);
	}
	
	//Mehtod to navigate to Quickscan page  
	
	
	
}
