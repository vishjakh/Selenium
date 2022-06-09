package vishal.github;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;

public class BaseTest {

	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	public CapabilityFactory cap = new CapabilityFactory();

	public static ExtentTest Test;

	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) throws MalformedURLException {
		// Set Browser to ThreadLocalMap
		driver.set(new RemoteWebDriver(new URL(
				"https://xyz@ondemand.eu-central-1.saucelabs.com:443/wd/hub"),
				cap.getCapabilities(browser)));

	}

	public WebDriver getDriver() {
		// Get driver from ThreadLocalMap
		return driver.get();
	}

	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}

	@AfterClass
	void terminate() {
		// Remove the ThreadLocalMap element
		driver.remove();
	}

	//Parameters will be coming from ITestListener Class
	public String getScreenshotPath(String methodName, WebDriver driver2) throws IOException {

		// Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot = ((TakesScreenshot) driver2);

		// Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination

		String path = System.getProperty("user.dir") + "\\results\\" + methodName+ ".png";

		File DestFile = new File(path);

		// Copy file at destination

		FileUtils.copyFile(SrcFile, DestFile);

		return path;

	}

	public void setExtentTestObjectInBase(ExtentTest test) {

		Test = test;

	}

	public ExtentTest getExtentTestObject() {

		return Test;

	}

}
