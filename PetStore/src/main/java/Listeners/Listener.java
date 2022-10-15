package Listeners;

import static Resource.ExtentTestManager.*;
import static Resource.LoggerManager.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;

public class Listener implements ITestListener {



	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		setTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		getTest().log(Status.PASS,"Passed successfully");
		logger.info(result.getName()+" passed successfully");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		getTest().log(Status.FAIL,result.getThrowable());
		logger.fatal(result.getName()+" failed");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}



	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	ext.flush();	
	}

}
