package vishal.github;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Listeners extends BaseTest implements ITestListener{
	ExtentReports extent = ExtentReportManager.getExtentObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	WebDriver driverObj;
	
	@Override  
	public void onTestStart(ITestResult result) {  
	// TODO Auto-generated method stub  
		
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		setExtentTestObjectInBase(extentTest.get());
		
	}  
	  
	@Override  
	public void onTestSuccess(ITestResult result) {  
	// TODO Auto-generated method stub  
		extentTest.get().log(Status.PASS, "Successfull");
	}  
	  
	@Override  
	public synchronized void onTestFailure(ITestResult result) {  
	// TODO Auto-generated method stub  
		extentTest.get().fail(result.getThrowable());
		
		
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driverObj),result.getMethod().getMethodName() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}  
	  
	@Override  
	public void onTestSkipped(ITestResult result) {  
	// TODO Auto-generated method stub  
	System.out.println("Skip of test cases and its details are : "+result.getName());  
	}  
	  
	@Override  
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
	// TODO Auto-generated method stub  
	System.out.println("Failure of test cases and its details are : "+result.getName());  
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
