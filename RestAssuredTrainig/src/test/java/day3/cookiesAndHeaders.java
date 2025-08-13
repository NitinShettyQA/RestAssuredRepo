package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class cookiesAndHeaders {

	@Test(enabled = false)
	public void singleCookie() {

		// validating single cookie

		Response res = when().get("https://www.google.com");

		System.out.println("AEC " + res.getCookie("AEC"));
	}

	@Test(enabled = false)
	public void multipleCookie() {

		// validating single cookie

		Response res = when().get("https://www.google.com");

		// System.out.println("AEC "+res.getCookie("AEC"));
		Map<String, String> allTheCookies = res.getCookies();

		// using for loop to iterate all the cookies

		System.out.println(allTheCookies.keySet());

		for (String co : allTheCookies.keySet()) {
			// Iterating all the key and getting the respective cookie value
			System.out.println(co + " " + allTheCookies.get(co));
		}
	}

	// Get all the header
	@Test(enabled = false)
	public void singgleAndMultipleHeaders() {

		// validating single cookie

		Response res = when().get("https://www.google.com");
		// We can use below method if we know the value of the header and it does not
		// change
		String singleHeader = res.getHeader("Content-Type");
		if (singleHeader == "text/html; charset=ISO-8859-1") {
			System.out.println(true);
		} else {
			System.out.println(false);
		}

		// For printing all the headers
		Headers header = res.getHeaders();

		for (Header hd : header) {
			System.out.println(hd.getName() + " " + hd.getValue());
		}
	}

	@Test(enabled = true)
	public void logAllHeader() {

		// validating single cookie

		when().get("https://www.google.com")
		// We can use below method if we know the value of the header and it does not
		// change
		.then()
		     .log().cookies()
		     .log().all(true)
		     .log().headers();
		   
		//Logging all the headers and cookies in the console

	}

}
