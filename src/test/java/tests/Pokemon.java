/**
 * 
 */
package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
import pages.MyPersonalInformationPage;
import pages.PokemonPage;
import utility.TestBase;

/**
 * @author Jai
 *
 */
public class Pokemon extends TestBase {

	private static final Logger log = LogManager.getLogger(Pokemon.class);
	PokemonPage pokemon;
	MyPersonalInformationPage myPersonalInfo;
	HashMap<String, String> testData;
	SoftAssert softAssertion;
	List<String> pokemonurls;

	/*
	 * @BeforeMethod public void launchApp() throws FileNotFoundException,
	 * IOException { log.info("Launching browser"); driver = getDriver(browserName);
	 * driver.get(PokemonURL); driver.manage().window().maximize();
	 * BasicConfigurator.configure(); // Configures the logs }
	 */

	@Test
	public void accessPokemonAPI() throws IOException, ParseException {

		pokemonurls = new ArrayList<String>();
		// Get the element's data in a string by normal UI way
		// pokemon = new PokemonPage(driver);		  
		// String data = pokemon.getPokemonData();

		RestAssured.baseURI = "https://pokeapi.co/api/v2/pokemon?limit=30";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		System.out.println("Response Body is =>  " + response.asString());

		// Create a JSON Parser object and parse the String
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(response.asString());
		System.out.println(json.get("results").getClass());
		log.info("Parsed the String into JSON object");

		// Get the JSON array from the main JSON object in the String format
		String str = json.get("results").toString();
		System.out.println(str);
		log.info("Extracted the array of JSON object 'results' into string");

		// Create a JSON array of that string
		JSONArray array = new JSONArray(str);

		// Loop over the JSON array to extract the URL from each object
		for (int i = 0; i < array.length(); i++) {
			org.json.JSONObject object = array.getJSONObject(i);
			// Adding the urls to the list
			pokemonurls.add(object.getString("url"));
			System.out.println(object.getString("url"));
			log.info(object.getString("url"));
		}

	}

	@AfterMethod
	public void teardown() {
		System.out.println("Test Execution Complete");
		// driver.quit();
	}

}
