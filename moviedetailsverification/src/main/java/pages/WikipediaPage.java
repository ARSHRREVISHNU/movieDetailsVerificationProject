package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class WikipediaPage {

	public String wikiActualCountryReleaseDate;
	public String wikiActualcountryRelease;
//Creating object for Web driver.
	WebDriver driver;
	public  WikipediaPage(WebDriver driver) {
		this.driver = driver;
		}
   //Locator to find the search bar
	By wikiSearchBar = By.id("searchInput");
	//Locator to find the search button
	By wikiSearchButton = By.id("searchButton");
	//Locator to find the all the rows in the table
	By totalElementsXpath = By.xpath("//div[@id='bodyContent']//div[5]//div[1]//table[1]//tbody//tr");
	
	//
	public void wikipediaTextSearch(String movie) {
		driver.findElement(wikiSearchBar).sendKeys(movie);
		driver.findElement(wikiSearchButton).click();
		System.out.println(movie);
	}
	
	public void wikipediaElements() {
	//js.executeScript("window.scrollBy(0,350)","");
		List<WebElement> totalElements= driver.findElements(totalElementsXpath);
		String countryReleaseDatexpath = null;
		wikiActualCountryReleaseDate = null;
		String countryReleaseXpath = null;
		wikiActualcountryRelease = null;
		//looping for Release country
		for(int i=3; i<=totalElements.size(); i++ ) {
			String xpath = "//div[@id='bodyContent']//div[5]//div[1]//table[1]//tbody//tr["+i+"]//th";
			String headerCheck = driver.findElement(By.xpath(xpath)).getText();
			if(headerCheck.contentEquals("Country")) {
				countryReleaseXpath = "//div[@id='bodyContent']//div[5]//div[1]//table[1]//tbody//tr["+i+"]//td";
				wikiActualcountryRelease = driver.findElement(By.xpath(countryReleaseXpath)).getText();
				break;
			}
		}
		
		//looping or Release date
		for(int i=3; i<=totalElements.size(); i++ ) {
			String xpath = "//div[@id='bodyContent']//div[5]//div[1]//table[1]//tbody//tr["+i+"]//th";
			String headerCheck = driver.findElement(By.xpath(xpath)).getText();
			if(headerCheck.contentEquals("Release date")) {
				countryReleaseDatexpath = "//div[@id='bodyContent']//div[5]//div[1]//table[1]//tbody//tr["+i+"]//td";
				wikiActualCountryReleaseDate = driver.findElement(By.xpath(countryReleaseDatexpath)).getText();
				break;
			}
		}
	}
}
