package pages;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
public class WikipediaPage {

	public String wikiActualCountryReleaseDate;
	public String wikiActualcountryRelease;
//Driver object
	WebDriver driver;
//JavascriptExecutor object	
	JavascriptExecutor js;  
	public  WikipediaPage(WebDriver driver) {
		this.driver = driver;
		}
//Web Elements	
	By wikiSearchBar = By.id("searchInput");
	By wikiSearchButton = By.id("searchButton");
	By totalElementsXpath = By.xpath("//div[@id='bodyContent']//div[5]//div[1]//table[1]//tbody//tr");
	By releaseDate = By.xpath("//div[@id='bodyContent']//div[5]//div[1]//table//tbody//tr[12]//td");
	By country = By.xpath("//div[@id='bodyContent']//div[5]//div[1]//table//tbody//tr[14]//td");
	By movieName = By.id("firstHeading");
	
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
		
		
		
		//System.out.println("Wikipedia---Country Release: "+countryRelease+" Release Date: "+countryReleaseDate);
	}
}
