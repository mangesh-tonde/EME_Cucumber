package DevopsInt.EME;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import junit.framework.Assert;
import pageObjects.EME_Login;
import pageObjects.EME_forgotPassword;
import pageObjects.EME_landing;
import resources.EME_exelReader;

public class EME_loginFlow extends Base
{
	public static Logger log=  LogManager.getLogger(Base.class.getName()); //To create a object for logging the script log
	public WebDriver driver;//to run test parrelly webdriver defined publically here
	public EME_exelReader exc;
	
	@BeforeTest
		public void initialise() throws IOException
	{
		driver = initialzeDriver();
		driver.get(prop.getProperty("url"));
		log.info("Launched URL");
	}
	
	@Test(priority=1)
	public void EME_Login() throws IOException, InterruptedException 
	{       
		EME_landing el = new EME_landing(driver);
		Assert.assertTrue(el.getLogovalidation().isDisplayed());
		log.info("Verified EME logo");
		EME_Login elog = el.getLogin(); //This method getlogin will click on Login button on landing button
		Assert.assertEquals(driver.getTitle(), "EME");//Bug: Title should be EME Login Internal Marketplace
		elog.getUsername().sendKeys(prop.getProperty("Devuser"));
		elog.getPassword().sendKeys(prop.getProperty("Devpass"));
		elog.getSignin().click();
	} 
	
	/*Initial logic was to check the message and accordingly decide if product is added or removed from
	 the wishlist, as per new logic remove everyhing from whishlist and then add one buy one*/  
	@Test(dependsOnMethods=("EME_Login"))
	public void EME_wishList() throws IOException, InterruptedException 
	{   
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		String emmptywishlist="No Wishlist Items Were Found";
	  
		Thread.sleep(3000);
		Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.cssSelector("span[class=' front_img_userprofile']"))).build().perform();
		Thread.sleep(3000);
        driver.findElement(By.xpath("//div[contains(@class,'ant-menu-submenu')]/ul/li[3]")).click();
        
       List<WebElement> lst = driver.findElements(By.xpath("//div[@class='wl-remove']"));
       int wishlistCount=lst.size();
       System.out.println(wishlistCount);
    //   driver.findElement(By.xpath("//div[@class='wl-btn-container']/div/p")).click();
       
       for(int i=lst.size(); i>0; i--)
       {
    	   System.out.println("in for loop");
           driver.findElement(By.xpath("//div[@class='wl-btn-container']/div/p")).click();
    	   System.out.println("out for loop");
       }
       log.info(driver.findElement(By.xpath("//div[@class='no-items-in-list-message']")).getText());
       driver.findElement(By.cssSelector("i.anticon.anticon-close")).click();

       exc = new EME_exelReader();
	   ArrayList<String> data = exc.getData("Details");
	   for (int i=1; i<3; i++)
		{					
			String temp1= "The product is successfully added to your wishlist"; //Stored sucessful Whishlist check in temp variable
			System.out.println(data.get(i));
			driver.findElement(By.cssSelector("input[id='searchInput']")).sendKeys(data.get(i));
			driver.findElement(By.cssSelector("button[id='search-button']")).click();
	        Thread.sleep(1000);
	        driver.findElement(By.xpath("//div[@class='ant-col ant-col-12']/button")).click();
	        Thread.sleep(1000);
	        //log.info(driver.findElement(By.xpath("//div[@class='ant-message-custom-content ant-message-success']/span")).getText());
			String temp = driver.findElement(By.xpath("//div[@class='ant-message-custom-content ant-message-success']/span")).getText();
			if(!temp.equalsIgnoreCase(temp1)) //to check if wishlist product is alredy checked 
			{
		        driver.findElement(By.xpath("//div[@class='ant-col ant-col-12']/button")).click();
		        System.out.println(driver.findElement(By.xpath("//div[@class='ant-message-custom-content ant-message-success']/span")).getText());
			}
			log.info("Product added to the cart is..." +data.get(i)); //logging product added to the cart
			driver.findElement(By.cssSelector("input[id='searchInput']")).clear();
		}
		
		Thread.sleep(2000);
       // Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.cssSelector("span[class=' front_img_userprofile']"))).build().perform();
		Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@class,'ant-menu-submenu')]/ul/li[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("i.anticon.anticon-close")).click();

   /*     int loopCount=0;
        
 	   List<WebElement> productName = driver.findElements(By.xpath("//div[@class='wl-card-content']/p[1]"));

 	   for (int i=0;i<productName.size();i++)  //check to see if extracted name present in arry list
 	   {
 		   if(data.contains(productName))
 		   { 
  		     loopCount++;
  		     driver.findElements(By.xpath("//div[@class='wl-card-content']/p[1]")).get(i).click();
  		     if(loopCount==productName.size())
  		     {
  		    	 break;
  		     }
 		   }
 	   }*/
                   
	}
	
	@AfterTest
	public void EME_logout() throws InterruptedException
	{
		Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.cssSelector("span[class=' front_img_userprofile']"))).build().perform();
		Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@class,'ant-menu-submenu')]/ul/li[4]")).click();
        Thread.sleep(2000);
        driver.close();
	}
	
	
    
}
