package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EME_forgotPassword 
{
		public WebDriver driver;
		
		public void EME_forgotPassword(WebDriver driver)
		{
			this.driver = driver;
		}
		
		By forgotpassword = By.cssSelector("[href='/forgot-password']");
		
}
