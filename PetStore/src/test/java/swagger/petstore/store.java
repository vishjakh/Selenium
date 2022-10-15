package swagger.petstore;
import static Resource.LoggerManager.logger;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;


public class store extends Base {

	
	Integer orderId;
	pet pet = new pet();
	

	@Test
	public void placeOrder() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		Faker f = new Faker();
		JSONObject data = new JSONObject();
		data.put("id", f.number().numberBetween(1, 1000));
		data.put("petId", pet.getPetId());
		data.put("quantity", 10);
		data.put("shipDate", LocalDateTime.now());
		data.put("status", "placed");
		data.put("complete", "true");
		orderId = given().header("accept","application/json").contentType(ContentType.JSON).body(data.toString()).when().post("/store/order").then().assertThat().statusCode(200).and().extract().path("id");
		System.out.println(orderId);
	}
	
	@Test(dependsOnMethods="placeOrder")
	public void checkPurchase() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		String status=given().contentType(ContentType.JSON).when().get("/store/order/"+Integer.toString(orderId)).then().assertThat().statusCode(200).extract().path("status");
		Assert.assertEquals(status, "placed");
	}
	
	@Test(dependsOnMethods= {"placeOrder","checkPurchase"})
	public void deleteOrder() {
		logger.info(new Exception().getStackTrace()[0].getMethodName()+" started executing");
		int code=given().contentType(ContentType.JSON).when().delete("/store/order/"+Integer.toString(orderId)).then().assertThat().statusCode(200).and().extract().path("code");
		Assert.assertEquals(code, 200);
	} 
	
	

}
