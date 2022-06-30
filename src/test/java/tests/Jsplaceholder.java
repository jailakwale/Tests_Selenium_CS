package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pages.JsplaceholderPage;
import pages.LoginPage;
import pages.MyPersonalInformationPage;
import java.io.FileNotFoundException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.HashMap;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.MyPersonalInformationPage;
import utility.TestBase;


public class Jsplaceholder extends TestBase{
	
	JsplaceholderPage jspage;
	SoftAssert softAssertion;
	private static final Logger log = org.apache.log4j.LogManager.getLogger(MyPersonalInformation.class);	
	HashMap<String, String> testData;
	
	
	@BeforeMethod
	public void launchApp() throws FileNotFoundException, IOException {
		log.info("Launching browser");
		driver = getDriver(browserName);
		driver.get(jsplaceholderURL);
		driver.manage().window().maximize();
		BasicConfigurator.configure(); //Configures the logs
	}

	@Test
	public void validateJsplaceholderData() throws InterruptedException, IOException, ParseException {

		jspage = new JsplaceholderPage(driver);		
		softAssertion = new SoftAssert();
		
		String str = jspage.getJsplaceholderData();
		System.out.println("#################################################");
		
		JSONParser parser = new JSONParser();
		//JSONObject json = (JSONObject) parser.parse(str);
		Object obj  = parser.parse(str);
		JSONArray array = new JSONArray();
		System.out.println(obj.getClass());
		System.out.println("#################################################");
		System.out.println(obj.toString());
		
		
		System.out.println("#################################################");
		
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/comments";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		//System.out.println("Response Body is =>  " + response.asString().getClass());
		
		//JSONObject json1 = (JSONObject) parser.parse(response.asString());
		Object obj1  = parser.parse(response.asString());
		JSONArray array1 = new JSONArray();
		System.out.println(obj1.getClass());
		System.out.println(obj1.toString());
	
		softAssertion.assertEquals(obj.toString(), obj1.toString());
	
	
		//Assert all to check the failure points
		softAssertion.assertAll();
	}

	@AfterMethod
	public void teardown() {
		log.info("Test Execution Complete");
		driver.quit();
	}

}
