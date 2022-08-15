package pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class IMDBPage {

	public String imdbActualCountryRelease;
	public String imdbActualCountryReleaseDate;
	WebDriver driver;	
	//Constructor method that will be invoked automatically as soon as the object of the class is created
	public IMDBPage(WebDriver driver) {
		this.driver = driver;
	}	
	//Locator to find Search bar
	By searchBar = By.id("suggestion-search");
	//Locator to Search button
    By searchButton = By.id("suggestion-search-button");
    //Locator to find the 1st entry of the movie link based on search
    By titleLink = By.xpath("//table[@class='findList']//tbody//tr//td[2]//a");
    //Locator to find Country of Origin field
    By countriesOfOrigin =  By.xpath("//span[contains(text(),'Countries of origin')]/following-sibling::div");
    By countryOfOrigin = By.xpath("//span[contains(text(),'Country of origin')]/following-sibling::div");
    //Locator to find Release Date link
    By releaseDateLink = By.xpath("//a[contains(text(),'Release date')]");
    //Locator to find releases field in Release date page
    By releasesElement = By.id("releases");
    //Locator to find release date field
    By releaseCountries = By.xpath("//div[@id='releaseinfo_content']//table[1]//tbody//tr");
    
    
	//Method to enter the text in the search bar and click
	public void textSearch(String movie) {
		driver.findElement(searchBar).sendKeys(movie);
        driver.findElement(searchButton).click();
        }
	//Method to find the release date and country of origin
	public void elementsCheck() {
		imdbActualCountryRelease = null;
		try {
		//Clicking the expected movie title from the list
	driver.findElement(titleLink).click();
	//Getting the countries of origin
	imdbActualCountryRelease = driver.findElement(countriesOfOrigin).getText();
		}
		//Execute catch block if the countries of origin field was not found.
		catch(Exception e) {
			//Getting the country of origin
			imdbActualCountryRelease = driver.findElement(countryOfOrigin).getText();
			
			
		}
    //Clicking the release date link
	driver.findElement(releaseDateLink).click();
	//Waiting until the element releases was loaded(Using Fluent wait checking for every 2 seconds) 
	FluentWait wait = new FluentWait(driver);
	wait.withTimeout(Duration.ofSeconds(10));
	wait.pollingEvery(Duration.ofSeconds(2));
	wait.ignoring(NoSuchElementException.class);
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(releasesElement)));
	//Getting the list of countries present in the release date page
	List<WebElement> actualIMDBReleaseDate = driver.findElements(releaseCountries);
	imdbActualCountryReleaseDate = null;
	String countryReleasexpath = null;
	//iterating the countries list to find India
	for(int i=1; i<=actualIMDBReleaseDate.size(); i++ ) {
		String xpath = "//div[@id='releaseinfo_content']//table[1]//tbody//tr["+i+"]//td[1]";
	    //Getting the country name 
		String countryNameCheck = driver.findElement(By.xpath(xpath)).getText();
		//Checking the country was India or not
		if(countryNameCheck.contentEquals("India")) {
			//If the country was India getting the release date
			countryReleasexpath = "//div[@id='releaseinfo_content']//table[1]//tbody//tr["+i+"]//td[2]";
			imdbActualCountryReleaseDate = driver.findElement(By.xpath(countryReleasexpath)).getText();
					break;
		}
		
	}
	
}
}