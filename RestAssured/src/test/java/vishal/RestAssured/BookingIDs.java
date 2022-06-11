package vishal.RestAssured;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookingIDs extends Base {
	
	
	static Logger logObj = LogManager.getLogger("BookingIDs");
	
	@BeforeClass
	public void start() {
		logObj.info("BookingIds Log Started");
	}
		
	
	@Test
	public void getBookingIDs() {
		
		RequestSpecification httpRequest = getHttpRequest();
		Response response = httpRequest.request(Method.GET,"/booking");
		System.out.println(response.getBody().asString());
		JsonPath jp = response.jsonPath();
		List bookingIds = jp.getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty());
	}
	
	@Test
	public void getBookingIDsByName() {
		RequestSpecification httpRequest = getHttpRequest();
		Response response = httpRequest.request(Method.GET,"/booking?firstname=Jim&lastname=Brown");
		System.out.println(response.getBody().asString());
		JsonPath jp = response.jsonPath();
		List bookingIds = jp.getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty());
	
	}
	
	@Test
	public void getBookingIDsByDate() {
		RequestSpecification httpRequest = getHttpRequest();
		Response response = httpRequest.request(Method.GET,"/booking?checkin=2014-03-13&checkout=2022-05-21");
		System.out.println(response.getBody().asString());
		JsonPath jp = response.jsonPath();
		List bookingIds = jp.getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty());
	
	}
	
	@Test
	public void getBooking() {
		RequestSpecification httpRequest = getHttpRequest();
		Response response = httpRequest.request(Method.GET,"/booking/307");
		System.out.println(response.getBody().asString());
	}
	
	
	@AfterClass
	public void end() {
		logObj.info("BookingIds Log Ended");
	}
	
	

}
