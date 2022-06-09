package vishal.github;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridBase {
	RemoteWebDriver driver;
	Properties prop;
	DesiredCapabilities cap;

	public WebDriver initialize() throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + File.separator + "Resources" + File.separator + "main.properties");
		prop = new Properties();
		prop.load(fis);
		String browser = prop.getProperty("browser");
		String node = prop.getProperty("node");

		cap = new DesiredCapabilities();

		if (browser.equalsIgnoreCase("chrome")) {
			cap.setPlatform(Platform.WIN10);
			cap.setBrowserName("chrome");
			driver = new RemoteWebDriver(new URL(node), cap);
			return driver;
		
		} else if (browser.equalsIgnoreCase("firefox")) {
			cap.setPlatform(Platform.WIN10);
			cap.setBrowserName("firefox");
			driver = new RemoteWebDriver(new URL(node), cap);
			return driver;

		}

		else if (browser.equalsIgnoreCase("ie")) {
			cap.setPlatform(Platform.WIN10);
			cap.setBrowserName("internet explorer");
			driver = new RemoteWebDriver(new URL(node), cap);
			return driver;
		}

		else if (browser.equalsIgnoreCase("safari")) {
			cap.setPlatform(Platform.WIN10);
			cap.setBrowserName("safari");
			driver = new RemoteWebDriver(new URL(node), cap);
			return driver;

		} else {
			System.out.println("Setting defalut as chrome");
			cap.setPlatform(Platform.WIN10);
			cap.setBrowserName("chrome");
			driver = new RemoteWebDriver(new URL(node), cap);
			return driver;
		}

	}

}
