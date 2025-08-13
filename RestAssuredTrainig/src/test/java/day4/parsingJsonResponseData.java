package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class parsingJsonResponseData {

	@Test
	public void parsingJsonRessponseData() {

		// validate JSON response without using variable to store the entire response
		/*given()
		     .contentType("application/json")

				.when()
				     .get("https://fakestoreapi.com/products")

				.then()
				     .body("[9].category", equalTo("electronics"))
				.header("Content-Type", "application/json; charset=utf-8");*/
		
		
		//Alternate method to validate JSON response using a variable
		
		Response res=given()
		      .contentType("application/json")
		
		.when()
		     .get("https://fakestoreapi.com/products");
		
		//validate body
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.getContentType(), "application/json; charset=utf-8");
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String categoryVal =res.jsonPath().get("[9].category").toString();
		Assert.assertEquals(categoryVal, "electronics");
	}
}
