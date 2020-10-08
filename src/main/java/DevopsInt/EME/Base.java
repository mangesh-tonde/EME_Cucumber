package DevopsInt.EME;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;


public class Base 
{
		//select browser to execute
		public WebDriver driver;
		public Properties prop;
		public WebDriver initialzeDriver() throws IOException
		{
			//Chrome
			 prop = new Properties();
	
			 //System.getProperty("user.dir") this will give location of project.
			 FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		
			prop.load(fis);
			//String browserName =System.getProperty("browser"); //this will read the browser provided by Jenkin via maven command (mvn -Dbrowser=chrome) 

			String browserName =prop.getProperty("browser"); // This is to read broswer value defined in properties file
			System.out.println(browserName);
			
			
			
			if(browserName.equals("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\browserDrivers\\ChromeDriver.exe");
				driver = new ChromeDriver();
			}
			else if(browserName.equals("IE"))
			{
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\browserDrivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				
			}
			else if(browserName.equals("Firefox"))
			{
				System.setProperty("webdriver.geko.driver", System.getProperty("user.dir")+"\\browserDrivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			
			//setup timeout for each page.
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
			return driver; 
		}
		
public String getScreenShot(String testcasename, WebDriver driver) throws IOException
{
	//This method caputres screen,palces into the path and return the path.
	System.out.println("you are in capturescreen");
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE); //Capture screen
	String screenShotLocation = System.getProperty("user.dir")+ "\\reports\\"+testcasename +".png";
    FileUtils.copyFile(source,new File(screenShotLocation));// copying screen to the path
    return screenShotLocation; //retrun the path where screen is captured
}
}
