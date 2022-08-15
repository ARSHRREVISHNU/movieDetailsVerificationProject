package pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class IMDBPage {

	public String imdbActualCountryRelease;
	public String imdbActualCountryReleaseDate;
	WebDriver driver;
	
	//Constructor method that will be invoked automatically as soon as the object of the class is created
	public IMDBPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Locator for search bar
	By searchBar = By.id("suggestion-search");
    By searchButton = By.id("suggestion-search-button");
    By titleLink = By.xpath("//table[@class='findList']//tbody//tr//td[2]//a");
    By countryOfOrigin =  By.xpath("//span[contains(text(),'Countries of origin')]/following-sibling::div");
    By releaseDateLink = By.xpath("//a[contains(text(),'Release date')]");
    By releasesElement = By.id("releases");
    By releaseCountries = By.xpath("//div[@id='releaseinfo_content']//table[1]//tbody//tr");
    By countriesOfOrigin = By.xpath("//span[contains(text(),'Country of origin')]/following-sibling::div");
    
	//Method to enter the text in the search bar
	public void textSearch(String movie) {
		driver.findElement(searchBar).sendKeys(movie);
        driver.findElement(searchButton).click();
        //System.out.println(movie);
        }
	public void elementsCheck() {
		imdbActualCountryRelease = null;
		try {
		//Clicking the expected movie title from the list
	driver.findElement(titleLink).click();
	//Getting the country of origin
	imdbActualCountryRelease = driver.findElement(countryOfOrigin).getText();
	//System.out.println(actualIMDBCountry);
		}
		catch(Exception e) {
			
			//System.out.println("Country of origin can't find so checking with countres of origin.");
			imdbActualCountryRelease = driver.findElement(countriesOfOrigin).getText();
			//System.out.println(actualIMDBCountry);
			
		}
	//Waiting until the element releases was loaded(Using Fluent wait checking for every 2 seconds) 
	driver.findElement(releaseDateLink).click();
	FluentWait wait = new FluentWait(driver);
	wait.withTimeout(Duration.ofSeconds(10));
	wait.pollingEvery(Duration.ofSeconds(2));
	wait.ignoring(NoSuchElementException.class);
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(releasesElement)));
	//Getting the Release date
	List<WebElement> actualIMDBReleaseDate = driver.findElements(releaseCountries);
	imdbActualCountryReleaseDate = null;
	String countryReleasexpath = null;
	for(int i=1; i<=actualIMDBReleaseDate.size(); i++ ) {
		String xpath = "//div[@id='releaseinfo_content']//table[1]//tbody//tr["+i+"]//td[1]";
		String countryNameCheck = driver.findElement(By.xpath(xpath)).getText();
		if(countryNameCheck.contentEquals("India")) {
			countryReleasexpath = "//div[@id='releaseinfo_content']//table[1]//tbody//tr["+i+"]//td[2]";
			imdbActualCountryReleaseDate = driver.findElement(By.xpath(countryReleasexpath)).getText();
					break;
		}
		
	}
//System.out.println("IMDB: "+countryReleaseDate);
	
}
}