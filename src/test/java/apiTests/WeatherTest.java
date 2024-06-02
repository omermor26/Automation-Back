package apiTests;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherTest {

	private static final String URL = "http://api.openweathermap.org/data/2.5/weather";
	private static final String key = "a6919dd2e20c5ca67bcf1c727a0b36bf";
	
	public static void main(String[] args) {
		RestAssured.baseURI = URL;
		String city = "London,uk";
		Response response;
		
		if (city.contains("Tel-Aviv")) {
			response = given().queryParam("q", "Tel-Aviv,il").queryParam("appid", key).queryParam("units", "metric").get().thenReturn(); //Getting response in c.
		}
		else {
			response = given().queryParam("q", city).queryParam("appid", key).get().thenReturn(); //Getting response in f.
		}
		
		System.out.println("Response data: " + response.asPrettyString());
		System.out.println("Response code: " + response.getStatusCode());
		
		//Verify that response code is 200
		if (response.getStatusCode() == 200) {
			System.out.println("status code is ok : 200");
		}
		else {
			System.out.println("status code is: " + response.getStatusCode());
		}
		
		String country = response.path("sys.country"); //Getting country code
		
		System.out.println("Temp in " + response.path("name") + " is: " + response.path("main.temp"));
		
		//Checking if city is Tel-Aviv country code is IL
		if ("IL".equals(country) && response.path("name").equals("Tel-Aviv")) {
			System.out.println("country code for tel aviv is correct and " + country);
		}
		else {
			System.out.println("country code is: " + country);
		}
	}
}
