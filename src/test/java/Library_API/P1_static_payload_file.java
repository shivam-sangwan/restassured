package Library_API;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.testBase;
import payload.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class P1_static_payload_file {
	//developer may provide payload in the form of 'file' instead of a 'sample body'
	
	
	@Test
	public void addBook() throws IOException
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		//first convert that file payload into string bcoz body() method takes only string
		String payload = new String(Files.readAllBytes(Paths.get("path of json file provided by dev")));
		//get(): method of path class, convert given string into a path
		//readallbytes(); method of files class, convert 'given path data' into bytes
		//finally we converted bytes into string from, using string object
		
		
		//storing post response in a string
		Response res = given().log().all().header("Content-Type","application/json")
		.body(payload) 
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response();
		
		//extracting id from response
		String id = testBase.getJsonPath(res, "ID");
		System.out.println(res + " " + id);
		
	}

}
