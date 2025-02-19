package payload;

public class Payload {

	//payload class is used to keep all payoad(sample body msg) which is used in our test cases
	//this is done to make test cases look less complex
	public static String addPlace()
	{
		String payload = "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
		return payload;
	}
	
	
	
	public static String coursePrice()
	{
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
	}
	
     public static String addBook(String aisle, String isbn)
     {
    	 String payload =  "{\r\n"
    	 		+ "\r\n"
    	 		+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
    	 		+ "\"isbn\":\""+isbn+"\",\r\n"
    	 		+ "\"aisle\":\""+aisle+"\",\r\n"
    	 		+ "\"author\":\"John foe\"\r\n"
    	 		+ "}";
    	 return payload;
     }
     
     public static String updatePlace(String place_id, String newaddress)
     {
    	 
    	//"+place_id+" > syntex to insert a variable in a string
    	 String payload =  "{\r\n"
 				+ "\"place_id\":\""+place_id+"\",\r\n"
 				+ "\"address\":\""+newaddress+"\",\r\n"
 				+ "\"key\":\"qaclick123\"\r\n"
 				+ "}";
    	 return payload;
     }
     
    

}
