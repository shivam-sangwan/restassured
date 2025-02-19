package Basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import payload.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class p2_Basics2 {

	public static void main(String[] args) {
		
		//printing 'add place API' i.e. post method response
		
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//given,when,then
		String response = given().log().all().queryParam("key", "qaclick123")
	    .header("Content-Type","application/json")
		.body(Payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		
		
		//extracting a parameter(ex: placeId) from response using jsonpath..
		//...this will be used in get/put/delete requests
		
		
		JsonPath js = new JsonPath(response); 
		//jsonpath: takes response in form of string and convert to json
		//alternative of xpath for JSON
		
		String place_id = js.get("place_id");  
		//get(): get object stored at given path("place_id") in response.
		//as place_id has no parent(in json response) so only "place_id" is given as path
	
		System.out.println(place_id);
		
	}

}
