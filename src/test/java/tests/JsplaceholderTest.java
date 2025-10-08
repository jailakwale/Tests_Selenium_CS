package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pages.JsplaceholderPage;
import utility.TestBase;

public class JsplaceholderTest extends TestBase {
	
	JsplaceholderPage jspage;
	SoftAssert softAssertion;
	private static final Logger log = LoggerFactory.getLogger(JsplaceholderTest.class);	
		
	@BeforeMethod
	public void launchApp() throws FileNotFoundException, IOException {
		log.info("Launching browser");
		driver = getDriver(BROWSER_NAME);
		driver.get(JSPLACEHOLDER_URL);
		driver.manage().window().maximize();
	}

	@Test
	public void validateJsplaceholderData() throws IOException, ParseException {

		jspage = new JsplaceholderPage(driver);		
		softAssertion = new SoftAssert();
		
		String str = jspage.getJsplaceholderData();
		log.info("Parsing JS placeholder data");

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(str);
		log.info("Parsed object class: {}", obj.getClass());
		
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/comments"; 
		
		RequestSpecification httpRequest = RestAssured.given(); 
		
		Response response = httpRequest.get("");	 

		Object obj1 = parser.parse(response.asString());			
	
		softAssertion.assertEquals(obj.toString(), obj1.toString());
	
		softAssertion.assertAll();
	}

	@AfterMethod
	public void teardown() {
		log.info("Test Execution Complete");
		driver.quit();
	}

}
