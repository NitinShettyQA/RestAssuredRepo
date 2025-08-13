package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class queryAndPathParam {

	@Test
	public void queryAndPathParameter() {
		given()
		      .urlEncodingEnabled(false)
//If we are sending multiple path in pathParam method like "api/users" then we will have to set URL encoding as false or else we will have to send each parameter separately	     
				.pathParam("path", "api/users").queryParam("page", "2").log().all()

				.when()
				// We need not mention query parameter in the get method separately like path
				// parameter
				.get("https://reqres.in/{path}")

				.then().statusCode(200).log().all();
	}
}
