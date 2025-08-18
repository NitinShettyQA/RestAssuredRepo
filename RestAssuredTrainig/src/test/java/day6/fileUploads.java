package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class fileUploads {
	
	@Test
	public void singleFileUpload()
	{
		File singleFile = new File("Mention file name");
		
		given()
		      .multiPart("file",singleFile)
		      .contentType("multipart/form-data")
		
		.when()
		      .post("Mention the endpoint to upload the file")
		
		.then()
		      .statusCode(200)
		      .body("file", equalTo("file name")); //Comparing file name from the response and the expected value
	}
	
	
	@Test
	public void multiFileUpload()
	{
		File file1 = new File("Mention file1 location");
		File file2 = new File("Mention file2 location");
		
		
		//If there are huge number of files to be uploaded then we can use array
		// File fileArr[] = {"mention file location", "mention file location", };
		//.multiPart("files", "fileArr") >>> wont work on all types of API depends on API implementation
		
		given()
		      .multiPart("files",file1)
		      .multiPart("files",file1)
		      .contentType("multipart/form-data")
		
		.when()
		      .post("Mention the endpoint to upload the file")
		
		.then()
		      .statusCode(200)
		      .body("file", equalTo("file name")); //Comparing file name from the response and the expected value
	}
	
	
	//file download
	@Test
	public void downLoadFile()
	{
		given()
		
		.when()
		     .get("URL to download the file")
		
		.then()
		      .statusCode(200)
		      .body("i", equalTo("")); // Perform validation required
	}
}
