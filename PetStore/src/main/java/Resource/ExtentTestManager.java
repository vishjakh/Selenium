package Resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	static ThreadLocal<ExtentTest> extTestThread = new ThreadLocal<ExtentTest>();
	static ExtentTest test;
	public static ExtentReports ext = ExtentManager.getExtent();

	public static synchronized void setTest(String name) {
		test = ext.createTest(name);
		extTestThread.set(test);
	}

	public static synchronized ExtentTest getTest() {
		return extTestThread.get();
	}

}
