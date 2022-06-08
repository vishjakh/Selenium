package vishal.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Base {
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver getDriver() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+File.separator +"Resources"+File.separator+"main.properties");
		prop = new Properties();
		prop.load(fis);
		String browser = prop.getProperty("browser");
		
		if (browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "d://webdrivers//chromedriver.exe");
			driver = new ChromeDriver();
			return driver;
		}
		else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", "d://webdrivers//geckoriver.exe");
			driver = new FirefoxDriver();
			return driver;
			
		}
		
		else if(browser.equalsIgnoreCase("Internet Explorer")) {
			System.setProperty("webdriver.ie.driver", "d://webdrivers//IEDriverServer.exe");  
			driver=new InternetExplorerDriver();  
			return driver;
		}
		
		else if(browser.equalsIgnoreCase("Safari")) {
			driver= new SafariDriver();	
			return driver;
			
		}
		else {
			System.out.println("Setting defalut as chrome");
			System.setProperty("webdriver.chrome.driver", "d://webdrivers//chromedriver.exe");
			driver = new ChromeDriver();
			return driver;
		}
	}
}
