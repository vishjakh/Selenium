package vishal.FileUpload;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DownloadFile {
	public static void main(String[] args) throws IOException, InterruptedException {
		String filePath = System.getProperty("user.dir") + File.separator + "DownloadedFiles";
		System.setProperty("webdriver.chrome.driver", "D:\\webdrivers\\chromedriver.exe");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("safebrowsing.enabled", "false");
		options.put("profile.default_content_settings.popups", 0);
		options.put("download.default_directory", filePath);
		ChromeOptions copt = new ChromeOptions();
		copt.setExperimentalOption("prefs", options);
		WebDriver driver = new ChromeDriver(copt);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.ilovepdf.com/pdf_to_jpg");
		driver.findElement(By.id("pickfiles")).click();
		Runtime.getRuntime().exec("C:\\Users\\Vishal\\Documents\\Upload2.exe");
		driver.findElement(By.id("processTask")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("pickfiles")));
		driver.findElement(By.id("pickfiles")).click();
		checkFileExistsAndDelete(driver, filePath);

	}

	public static void checkFileExistsAndDelete(WebDriver driver, String filePath) {
		File f = new File(filePath + File.separator + "ilovepdf_pages-to-jpg.zip");
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(25))
				.pollingEvery(Duration.ofMillis(100));
		wait.until(x -> f.exists());

		if (f.exists() && !f.isDirectory()) {
			System.out.println("File Found");
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		System.out.println("Deleting File");
		f.delete();

	}
}
