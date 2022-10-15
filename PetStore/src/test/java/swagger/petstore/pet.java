package swagger.petstore;

import static Resource.LoggerManager.logger;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class pet extends Base {
	int id;
	JSONObject data = new JSONObject();
	Faker f = new Faker();
	
	
	@Test
	public void addPet() {
		JSONObject animalName = new JSONObject();
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		animalName.put("name", f.animal().name());
		data.put("id", f.number().numberBetween(1, 5000));
		data.put("category", animalName);
		data.put("name", f.animal().name());
		String[] url = { "https://google.com", "https://yahoo.com" };
		data.put("photoUrls", url);
		data.put("status", "available");
		given().contentType(ContentType.JSON).body(data.toString()).when().post("/pet").then().assertThat()
				.statusCode(200);
		id = given().contentType(ContentType.JSON).body(data.toString()).when().post("/pet").then().extract()
				.path("id");
	}

	@Test
	public void modifyPet() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		data.put("id", id);
		data.put("name", "modPuppy");

		String name = given().contentType(ContentType.JSON).body(data.toString()).when().put("/pet").then().assertThat()
				.statusCode(200).and().extract().path("name");
		Assert.assertEquals(name, "modPuppy");

	}

	@Test(dependsOnMethods = "addPet")
	public void uploadImage() throws IOException {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		data.put("petId", id);
		Response resp = given().multiPart("file", new File("C:\\Users\\Vishal\\Downloads\\linkedin_back.jpg"))
				.accept(ContentType.JSON).body(data.toString()).when()
				.post("/pet/" + Integer.toString(id) + "/uploadImage").then().assertThat().statusCode(200).and()
				.extract().response();
	}

	@Test
	public void getStatus() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		given().param("status", "available").when().get("/pet/findByStatus").then().assertThat().statusCode(200);
	}

	@Test(dependsOnMethods = "addPet")
	public void findPet() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		given().when().get("/pet/" + Integer.toString(id)).then().assertThat().statusCode(200);
	}

	@Test
	public void updatePet() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");

		data.put("name", f.animal().name());
		data.put("status", "available");
		given().header("accept","application/json").contentType(ContentType.URLENC).body(data.toString()).when().post("/pet/" + Integer.toString(id)).then()
				.assertThat().statusCode(200);

	}
	
	@Test(dependsOnMethods="addPet",priority=10)
	public void deletePet() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		given().header("api_key", "test123").contentType(ContentType.JSON).when().delete("/pet/"+Integer.toString(id)).then().assertThat().statusCode(200);
	}
	
	
	public int getPetId() {
		addPet();
		return id;
	}
	
	

}
