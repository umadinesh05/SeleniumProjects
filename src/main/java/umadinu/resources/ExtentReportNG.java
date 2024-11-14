package umadinu.resources;

import java.io.File;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject()  // adding the return type as static so that we dont have to create the new object to call this method
	
	{
		       
		// Providing path for the report to get generated
			//	String path = System.getProperty("user.dir"+"\\reports\\index.html");
				
			
				//create an object for extensparkreport class to configure the test report for look and feel
				ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") +"\\reports\\index.html");
			
				
				// Configuration for the metadata like names and titles of the report
				reporter.config().setReportName("Web Automation Report");
				reporter.config().setDocumentTitle("Test Results");
				
				//Creating object for ExtentReport class which eventually creates the Report
				ExtentReports extent = new ExtentReports();
				extent.attachReporter(reporter);  //pass the extentsparkreporter object as an argument
				extent.setSystemInfo("Tester", "Umadinu");
				//extent.createTest(path);  // its not feasible to write this after every test case considering we will have 100 test cases
	            return extent;	

     }
	}
