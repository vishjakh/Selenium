package page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import util.ElementUtil;
import util.TransFileReader;

public class HomePage {

	ElementUtil eleUtil;
	List<String> elementHeaderText = new ArrayList<String>();
	List<String> elementFooterText = new ArrayList<String>();
	TransFileReader reader;

	public HomePage(WebDriver driver) {
		eleUtil = new ElementUtil(driver);
	}

	// ----------------------------Header ------------------------------------

	public List<String> getHeaderElements() {

		// -------------------Header Elements on left ---------------------------
		String headerElementsXpathLeft = "//nav/ul/li //a";
		List<WebElement> liElements = eleUtil.getElements("xpath", headerElementsXpathLeft);

		for (WebElement ele : liElements) {
			elementHeaderText.add(ele.getText());
		}

		// ------------------Header Elements on Right-------------------------------
		String headerElementRight1 = "//a[contains(@href,'/account')]";
		String headerElementRight2 = "(//div[contains(@class,'CountryFlag_flag__6ZRas')]//following-sibling::span)[1]";

		elementHeaderText.add(eleUtil.getElement("xpath", headerElementRight1).getText());
		elementHeaderText.add(eleUtil.getElement("xpath", headerElementRight2).getText());

		return elementHeaderText;
	}

	// ------------------------------------------Footer------------------------------------

	public List<String> getFooterElements() {

		// ------------------------------Footer Column1----------------------------

		String company = "//*[@id='layout']/div[1]/div/div/div[2]/div/div[1]/div";
		elementFooterText.add(eleUtil.getElement("xpath", company).getText());
		String column1Xpath = "//*[@id='layout']/div[1]/div/div/div[2]/div/div[1]/ul/li/a";
		List<WebElement> column1Elements = eleUtil.getElements("xpath", column1Xpath);

		for (WebElement ele : column1Elements) {

			// ----------Adding by textContent rather than getText() bcz <br> in text is
			// breaking code---------

			elementFooterText.add(ele.getAttribute("textContent"));

		}
		String single = "//*[@id='layout']/div[1]/div/div/div[2]/div/div[1]/ul/span";
		elementFooterText.add(eleUtil.getElement("xpath", single).getText());

		// -----------------------------Footer Column---------------------------------

		String cc = "//*[@id='layout']/div[1]/div/div/div[2]/div/div[2]/div";
		elementFooterText.add(eleUtil.getElement("xpath", cc).getText());

		String column2Xpath = "//*[@id='layout']/div[1]/div/div/div[2]/div/div[2]/ul/li/a";
		List<WebElement> column2Elements = eleUtil.getElements("xpath", column2Xpath);

		for (WebElement ele : column2Elements) {
			elementFooterText.add(ele.getText());
		}

		// -----------------------------Footer Column 3--------------------------

		String shop = "//*[@id='layout']/div[1]/div/div/div[2]/div/div[3]/div";
		elementFooterText.add(eleUtil.getElement("xpath", shop).getText());

		String column3Xpath = "//*[@id='layout']/div[1]/div/div/div[2]/div/div[3]/ul/li";
		List<WebElement> column3Elements = eleUtil.getElements("xpath", column3Xpath);

		for (WebElement ele : column3Elements) {
			elementFooterText.add(ele.getText());
		}

		return elementFooterText;
	}

	// ------------------------Verification----------------------------------------

	public void verifyHeaderElements(String language, String section) {

		reader = new TransFileReader();

		List<String> fileData = (reader.getHeaderDataFromFile(language, section)).stream().map(ele -> ele.toLowerCase())
				.collect(Collectors.toList());
		List<String> webData = (getHeaderElements()).stream().map(ele -> ele.toLowerCase())
				.collect(Collectors.toList());
//		System.out.println("File value: " + fileData);
//		System.out.println("Real Headers: " + webData);

		Assert.assertTrue(fileData.equals(webData));

	}

	public void verifyFooterElements(String language, String section) {

		reader = new TransFileReader();

		List<String> fileData = (reader.getFooterDataFromFile(language, section)).stream().map(ele -> ele.toLowerCase())
				.collect(Collectors.toList());
		List<String> webData = (getFooterElements()).stream().map(ele -> ele.toLowerCase())
				.collect(Collectors.toList());
//		System.out.println("File value: " + fileData);
//		System.out.println("Real Footers: " + webData);

		Assert.assertTrue(CollectionUtils.isEqualCollection(fileData, webData));
	}

}
