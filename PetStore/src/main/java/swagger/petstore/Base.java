package swagger.petstore;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class Base {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		String apiKey="test123";
	}
	
}
