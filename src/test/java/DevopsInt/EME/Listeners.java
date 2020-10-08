package DevopsInt.EME;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.EME_ExtentreporterNG;

public class Listeners extends Base implements ITestListener
{
	ExtentTest test;
	ExtentReports extent=EME_ExtentreporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // While running test parellel this Threadlocal helps execution thread safe
	public void onTestStart(ITestResult result) 
	{ 	
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "Test passed");
	}

	
	public void onTestFailure(ITestResult result) {
		
		
		extentTest.get().fail(result.getThrowable());
		//This will help to catch test running in multiple section
		WebDriver driver = null;
		
		String getfailedtmethodname =result.getMethod().getMethodName();
		try {
				driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		try {
				//Logic to attach screenshot in extentrepot
		        extentTest.get().addScreenCaptureFromPath(getScreenShot(getfailedtmethodname,driver), result.getMethod().getMethodName()); 	
				System.out.println("you are in onTestFailure try block");
			
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		 
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}



}
