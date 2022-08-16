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

	//Method to enter movie name in the search bar and click
	public void wikipediaTextSearch(String movie) {
		driver.findElement(wikiSearchBar).sendKeys(movie);
		driver.findElement(wikiSearchButton).click();
	}
	//Method to find the release date and country
	public void wikipediaElements() {
		//Getting all the elements in the table
		List<WebElement> totalElements= driver.findElements(totalElementsXpath);
		String countryReleaseDatexpath = null;
		wikiActualCountryReleaseDate = null;
		String countryReleaseXpath = null;
		wikiActualcountryRelease = null;
		//iterating the table to find country - As first few rows contains other details we starting to iterate from 3 row  
		for(int i=3; i<=totalElements.size(); i++ ) {
			//Locator to find each row header
			String xpath = "//div[@id='bodyContent']//div[5]//div[1]//table[1]//tbody//tr["+i+"]//th";
			//Getting the header of each row
			String headerCheck = driver.findElement(By.xpath(xpath)).getText();
			//Checking the row header was country or not
			if(headerCheck.contentEquals("Country")) {
				//If the row header was country then getting name of the Country
				countryReleaseXpath = "//div[@id='bodyContent']//div[5]//div[1]//table[1]//tbody//tr["+i+"]//td";
				wikiActualcountryRelease = driver.findElement(By.xpath(countryReleaseXpath)).getText();
				break;
			}
		}

		//iterating the table to find release date - As first few rows contains other details we starting to iterate from 3 row
		for(int i=3; i<=totalElements.size(); i++ ) {
			//Locator to find each row header
			String xpath = "//div[@id='bodyContent']//div[5]//div[1]//table[1]//tbody//tr["+i+"]//th";
			//Getting the header of each row
			String headerCheck = driver.findElement(By.xpath(xpath)).getText();
			//Checking the row header was release date or not
			if(headerCheck.contentEquals("Release date")) {
				//If the row header was release date then getting the date
				countryReleaseDatexpath = "//div[@id='bodyContent']//div[5]//div[1]//table[1]//tbody//tr["+i+"]//td";
				wikiActualCountryReleaseDate = driver.findElement(By.xpath(countryReleaseDatexpath)).getText();
				break;
			}
		}
	}
}
