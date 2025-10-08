/**
 * 
 */
package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Removed unused import org.apache.log4j.BasicConfigurator
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.TestBase;

/**
 * @author Jai
 *
 */
public class PokemonTest extends TestBase {

	private static final Logger log = LoggerFactory.getLogger(PokemonTest.class);
	SoftAssert softAssertion;

	@DataProvider(name = "pokemonApiEndpoints")
	public Object[][] pokemonApiEndpoints() {
		return new Object[][] {
			{"https://pokeapi.co/api/v2/pokemon?limit=10"},
			{"https://pokeapi.co/api/v2/pokemon?limit=20"},
			{"https://pokeapi.co/api/v2/pokemon?limit=30"}
		};
	}

	@Test(dataProvider = "pokemonApiEndpoints")
	public void testPokemonApiReturnsUrls(String apiEndpoint) throws IOException, ParseException {
		List<String> pokemonurls = new ArrayList<>();
		RestAssured.baseURI = apiEndpoint;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		log.info("Response Body is =>  " + response.asString());

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(response.asString());
		log.info("Parsed the String into JSON object");

		String str = json.get("results").toString();
		log.info("Extracted the array of JSON object 'results' into string");

		JSONArray array = new JSONArray(str);
		for (int i = 0; i < array.length(); i++) {
			org.json.JSONObject object = array.getJSONObject(i);
			pokemonurls.add(object.getString("url"));
			log.info(object.getString("url"));
		}
		// Assert that URLs were found
		softAssertion = new SoftAssert();
		softAssertion.assertTrue(!pokemonurls.isEmpty(), "No Pokemon URLs found!");
		softAssertion.assertAll();
	}

	@AfterMethod
	public void teardown() {
		System.out.println("Test Execution Complete");
		// driver.quit();
	}

}
