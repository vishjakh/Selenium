package Resource;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	static ExtentReports ext;
	static ExtentSparkReporter spark;
	static FormattedDateTime fdt;

	public static synchronized ExtentReports getExtent() {
		ext = new ExtentReports();
		fdt = new FormattedDateTime();
		spark = new ExtentSparkReporter(
				System.getProperty("user.dir") + File.separator + "Reports" +File.separator +fdt.getDateTime()+"_report.html");
		spark.config().setDocumentTitle("Daily Api Automation Report");
		spark.config().setReportName("PetStores");
		spark.config().setTheme(Theme.DARK);
		ext.attachReporter(spark);
		ext.setSystemInfo("Reporter Name", "Vishal");
		ext.setSystemInfo("Platform", "Local Windows");
		return ext;
	}

}
