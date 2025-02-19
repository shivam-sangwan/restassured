package Basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import files.testBase;
import payload.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class p3_End_to_End_Testing {

	public static void main(String[] args) {
		
		//update 'address section of body' of the added place
		
	
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//given,when,then
		Response response = given().log().all().queryParam("key", "qaclick123")
	    .header("Content-Type","application/json")
		.body(Payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().extract().response();
		
		String place_id = testBase.getJsonPath(response,"place_id");
		String newaddress = "70 winter walk, USA";
		
		given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		//note: in body "place_id" of above response is used.
		//'newaddress' ref. variable is used to avoid hard coding in body
		.body(Payload.updatePlace(place_id, newaddress)) 
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		
		//get the place using 'get' method and check if updated address is present there
		
		//note: as for get method we are not using any body so we also won't use any header
		//..no body means no header
		//First method of validating updated address
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
	    .when().get("/maps/api/place/get/json")
	    .then().log().all().assertThat().statusCode(200)
	    .body("address", equalTo("70 winter walk, USA"));
		
		//2nd method of validating updated address
		Response response2 = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
	    .when().get("/maps/api/place/get/json")
	    .then().log().all().assertThat().statusCode(200).extract().response();	    
	    
		String actualaddress = testBase.getJsonPath(response2,"address");
	    Assert.assertEquals(newaddress, actualaddress);
	}

}
