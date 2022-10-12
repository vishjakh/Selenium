package vishal.FileUpload;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Assert; 

public class UploadFile {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\webdrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
		driver.get("https://www.ilovepdf.com/pdf_to_jpg");
		driver.findElement(By.id("pickfiles")).click();
//		Thread.sleep(4);
		Runtime.getRuntime().exec("C:\\Users\\Vishal\\Documents\\Upload1.exe");
		Assert.assertTrue(driver.findElements(By.id("processTaskTextBtn")).size()!=0);
		
		
		
		
	}

}
