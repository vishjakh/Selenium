package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.Base;
import page.HomePage;
import util.TransFileReader;

public class HomePageTest extends Base {

	private WebDriver driver;
	HomePage home;
	TransFileReader tfr;

	@Parameters({"browser","language"})
	@BeforeTest
	public void setup(String browser,String urlLang) {
		driver = setupBrowser(browser,urlLang);
		home = new HomePage(driver);
	}
	
	@Parameters({"language"})
	@Test
	public void HomePageHeaderTest(String language) {
		home.verifyHeaderElements(language, "header");
		
	}
	
	@Parameters({"language"})
	@Test
	public void HomePageFooterTest(String language) {
		home.verifyFooterElements(language, "footer");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}


}
