/**
 * 
 */
package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Base class to initialize driver, read and extract test data and config properties
 * @author Jai
 *
 */
public class TestBase {
	
	public static final String USERDIR = System.getProperty("user.dir");
	public static final String CONFIGPROPERTIESFILEPATH = USERDIR + "\\src\\test\\resources\\Properties\\config.properties";	
	public static final String TESTDATAFILEPATH = USERDIR + readConfigPropertiesFile("testData.path");	
	public static final int MINWAITTIME = Integer.parseInt(readConfigPropertiesFile("min.waitTime"));
	public static final int DEFAULTWAITTIME = Integer.parseInt(readConfigPropertiesFile("default.waitTime"));
	public static final int MAXWAITTIME = Integer.parseInt(readConfigPropertiesFile("max.waitTime"));
	public static final String browserName = readConfigPropertiesFile("browser.name");
	public static final String URL = readConfigPropertiesFile("url");
	public static final String PokemonURL = readConfigPropertiesFile("pokemonurl");
	public static final String jsplaceholderURL = readConfigPropertiesFile("jsplaceholderurl");
	
	public WebDriver driver = null;
	

	/**
	 * Gets the browser driver
	 * 
	 * @param browserName
	 * @return driver
	 * 
	 */
	public static WebDriver getDriver(String browserName) {
		WebDriver driver = null;
		Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();		

		switch (browserName) {
		case "Firefox":
			driver = drivers.get("Firefox");
			if (driver == null) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				drivers.put("Firefox", driver);
			}
			break;
		case "IE":
			driver = drivers.get("IE");
			if (driver == null) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				drivers.put("IE", driver);
			}
			break;
		case "Chrome":
			driver = drivers.get("Chrome");
			if (driver == null) {				
				 WebDriverManager.chromedriver().setup(); 				
				 driver = new ChromeDriver(); 
				 drivers.put("Chrome", driver);					 
			}
			break;
		}
		return driver;
	}
	
	
	/**
	 * Reads config.properties file
	 * 
	 * @param key
	 * @return value of the key(passed as argument)
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readConfigPropertiesFile(String key) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(CONFIGPROPERTIESFILEPATH));
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			Assert.fail("File Not found as File path = " + CONFIGPROPERTIESFILEPATH, e);
		} catch (IOException e) {		
			e.printStackTrace();
			Assert.fail("IO Exeption as File path = " + CONFIGPROPERTIESFILEPATH, e);
		}
		String value = prop.getProperty(key);
		
		return value;
	}
	
	
	
	/**
	 * Reads test data from testData.JSON file
	 * @param testCaseName Name of the testCase	  
	 * @return test data value as HashMap
	 * @throws IOException
	 * @throws ParseException
	 */
	public static LinkedHashMap<String, String> extractDataFromJson(String testCaseName) throws IOException, ParseException{
		
		LinkedHashMap<String, String>  map = new LinkedHashMap<String,String>();		
		FileInputStream fis= new FileInputStream(TESTDATAFILEPATH);
		InputStreamReader isr= new InputStreamReader(fis);		
		JSONParser jParser = new JSONParser();
		JSONObject jFileObj = (JSONObject) jParser.parse(isr);		
		JSONObject jDataObj = (JSONObject) jFileObj.get(testCaseName);	
		if(testCaseName != null ) {			
			if(null!=jDataObj) {
			   Set<String> keys =  jDataObj.keySet();
				for(String key : keys) {
					map.put(key, jDataObj.get(key).toString());
					
				}				
			}
		}
		return map;
	}
	
	
	/**
	 * Reads test data form testData.JSON file
	 * @param testCaseName Name of the testCase
	 * @param key Key
	 * @return test data value as String
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String readDataFromJson(String testCaseName, String key) throws IOException, ParseException{
		
		FileInputStream fis= new FileInputStream(TESTDATAFILEPATH);
		InputStreamReader isr= new InputStreamReader(fis);		
		JSONParser jParser = new JSONParser();
		JSONObject jFileObj = (JSONObject) jParser.parse(isr);
		JSONObject jDataObj = (JSONObject) jFileObj.get(testCaseName);
		String value = (String) jDataObj.get(key);
		
		return value;	
	}

	

}
