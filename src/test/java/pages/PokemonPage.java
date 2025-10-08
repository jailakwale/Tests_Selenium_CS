/**
 * 
 */
package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



/**
 * Description: Contains all the page webelements and methods
 * @author Jai
 *
 */
public class PokemonPage extends BasePage {
	
	
	@FindBy(xpath = Locators.POKEMON_URLS)
	public WebElement pokemonURLs;
	
	/**
	 * Decription:Assigning driver and initializing webelements of this page 	
	 * @param driver
	 *  
	 */	
	public PokemonPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
	/** 
	 * Description: get the data from the pokemon webelement 	 
	 *   
	 */	
	public String getPokemonData() {
		
		System.out.println("##############################################");
		String text = pokemonURLs.getText();
		System.out.println(text);		
		
		return text;	

	}

}
