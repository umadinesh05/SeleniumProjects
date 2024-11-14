package umadinu.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import umadinu.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener {
	
    ExtentTest test;  // ExtentTest is responsible to create the logs
	ExtentReports extent = ExtentReportNG.getReportObject();
	// when tests run in parallel, there is a chance of concurrency issues due to thread not being assigned uniquely
	//due to that, it will not assign the failure screenshot to the correct testcase name
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();  //Thread safe class object creation
	
	
	@Override
	//everytime the test will start, it will come to this section and we ask this to create the extent report
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		// result argument is having the information of the test running, so we are trying to get the current method name
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);  //unique thread id (Error Validation)
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	//	test.log(Status.PASS, "Test is passed");
		extentTest.get().log(Status.PASS, "Test is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
//		test.fail(result.getThrowable());
		
		extentTest.get().fail(result.getThrowable());
		// the below code is to get the actual driver to take the screenshot of the current webpage
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
/*		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); */
		}
		
		String filepath = null;
		try {
			filepath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	     test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
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
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
