package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EME_Login 
{
	public WebDriver driver;
	public EME_Login(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	By email = By.name("email");
	By pass = By.name("password");
	By Signin = By.xpath("//button[@label='SIGN IN']");
	By forgotpassword = By.cssSelector("[href='/forgot-password']");

	
	public WebElement getUsername()
	{
		return driver.findElement(email);
	}
	
	public WebElement getPassword()
	{
		return driver.findElement(pass);
	}
	
	public WebElement getSignin()
	{
		return driver.findElement(Signin);
	}
	
	public EME_forgotPassword forgotPassword()
	{
		driver.findElement(forgotpassword).click();
		EME_forgotPassword fp = new EME_forgotPassword();
		return fp;
	}

}
