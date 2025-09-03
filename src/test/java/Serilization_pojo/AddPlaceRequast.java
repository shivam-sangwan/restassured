package Serilization_pojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import payload.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddPlaceRequast {

	public static void main(String[] args) {
		
		//add place using post, but no payload will be passed in body
		//..object of pojo class will be passed in body to achieve serialization
		
	
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//creating object of pojo class
		AddPlace ap = new AddPlace();
		
		//setting value in pojo class using setter methods
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		
		List<String> types = new ArrayList();
		types.add("shoe park");
		types.add("shop");
		
		//types will take list of string
		ap.setTypes(types);
		
		//setting location
		Location lc = new Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		ap.setLocation(lc);
		
		
		//given,when,then
		Response response = given().log().all().queryParam("key", "qaclick123")
	    .header("Content-Type","application/json")
		.body(ap)  //restAssured will automaticaly convert this object into request payload(serialization)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		System.out.println(response.asString());
		
		
	}

}
