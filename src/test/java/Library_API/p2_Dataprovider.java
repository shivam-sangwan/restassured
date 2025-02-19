package Library_API;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.testBase;
import payload.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class p2_Dataprovider {
	//Datadriven testing
	//dynamic data will be passed to body from dataprovider. 
	//..to run test case for multiple sets of data.
	//for each test case, body will have diff. data
	
	@Test(dataProvider = "booksdata")
	public void addBook(String aisle, String isbn)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		//storing post response in a string
		Response res = given().log().all().header("Content-Type","application/json")
		.body(Payload.addBook(aisle,isbn)) //passing dynamic data in body
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response();
		
		//extracting id from response
		String id = testBase.getJsonPath(res, "ID");
		System.out.println(res + " " + id);
		
	}
	
	
	
	@DataProvider(name = "booksdata")
	public Object[][] getData()
	{
		Object[][] obj = {
				{"142","ab"},
				{"343","cd"},
				{"544","me"}
				
		};
		
		return obj;
	}

}
