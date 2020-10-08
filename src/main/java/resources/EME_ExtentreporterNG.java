package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class EME_ExtentreporterNG {
	static ExtentReports extent;
	public static ExtentReports getReportObject()
	{
		//ExtentSparkReporter - Created a report
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("EME Automation Suite");
		reporter.config().setDocumentTitle("Test Result");
		
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "MT");
		return extent; 
	}

}
