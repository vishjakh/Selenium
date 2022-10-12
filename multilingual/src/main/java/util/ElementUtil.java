package util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtil {

	WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public WebElement getElement(String locator, String value) {
		return driver.findElement(getBy(locator, value));
	}

	public List<WebElement> getElements(String locator, String value) {
		return driver.findElements(getBy(locator, value));
	}

	public By getBy(String locatorType, String locatorValue) {

		By locator = null;

		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "cssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "className":
			locator = By.className(locatorValue);
			break;
		case "linkText":
			locator = By.linkText(locatorValue);
			break;
		case "partialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "tagName":
			locator = By.tagName(locatorValue);
			break;
		default:
			break;
		}
		return locator;

	}
}
