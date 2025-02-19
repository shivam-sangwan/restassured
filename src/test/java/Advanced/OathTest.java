package Advanced;

import org.testng.annotations.Test;

import files.testBase;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OathTest {
	//testing Oath2.0...refer 'Oath details' text file for api contract
	
	
	@Test
	public void Oath()
	{
		//accessing "AS" to get access token
		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		
		Response res = given().log().all()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		//we provided entire baseURI in post, as 'resource' was not available in Api contract
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().assertThat().statusCode(200).extract().response();
		
		System.out.println(res.asString());
		String token = testBase.getJsonPath(res, "access_token");
	
		
		//accessing 'api GetCourseDetails' using token
		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/getCourseDetails";
		
		String response = given().log().all().header("Authorization", token)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then().log().all().assertThat().statusCode(401)
		.extract().response().asString();
		
		
	
	}

}
