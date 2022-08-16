package testcases;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.IMDBPage;
import pages.WikipediaPage;
import utils.XLUtility;

public class TestCase_1 {

	WebDriver driver;

	//drivermethod executed before imdbAndWikiTest
	@BeforeTest
	public void driverMethod(){
		String var = "./src/main/java/resources/chromedriver_win32 (104)//chromedriver.exe";
		//Setting the system property
		System.setProperty("webdriver.chrome.driver", var);
		driver = new ChromeDriver();
		//Loading IMDB Website
		driver.get("https://www.imdb.com/");
		//Opening new tab
		driver.switchTo().newWindow(WindowType.TAB);
		//Loading Wikipedia website
		driver.get("https://en.wikipedia.org/");
		//Maximizing the window
		driver.manage().window().maximize();

	}

	@Test(dataProvider="inputProvider", dataProviderClass=DataProviders.class)
	public void imdbAndWikiTest(String movieName) {
		boolean country, releaseDate;
		//Getting total tabs opened
		Set<String> tabs = driver.getWindowHandles();
		ArrayList<String> all_tabs = new ArrayList<String>(tabs);
		//Switching to imdb website tab
		driver.switchTo().window(all_tabs.get(0));
		//Creating object for IMDBPage class
		IMDBPage imdb = new IMDBPage(driver);
		//Calling the methods present in IMDBPage class
		imdb.textSearch(movieName);
		imdb.elementsCheck();
		//Switching to wikipedia website tab
		driver.switchTo().window(all_tabs.get(1));
		//Creating object for WikipediaPage class
		WikipediaPage wiki = new WikipediaPage(driver);
		//Calling the methods present in IMDBPage class
		wiki.wikipediaTextSearch(movieName);
		wiki.wikipediaElements();
		//Checking if the country name in the wikipedia and imdb was same or not
		if(wiki.wikiActualcountryRelease.contentEquals(imdb.imdbActualCountryRelease)) {
			country = true;
		}
		else
		{
			country = false;
		}
		//Checking if the release date in the wikipedia and imdb was same or not
		if(wiki.wikiActualCountryReleaseDate.contentEquals(imdb.imdbActualCountryReleaseDate)) {
			releaseDate = true;
		}
		else
		{
			releaseDate = false;
		}

		//Checking the country in imdb and wikipedia was same and release date in imdb and wikipedia was same
		Assert.assertEquals(country, releaseDate, "Country and Release date both are not same");
	}

	@AfterTest
	//Method to close the driver
	public void driverClose() {

		driver.quit();
	}



}
