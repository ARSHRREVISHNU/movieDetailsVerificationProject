package testcases;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;
import org.testng.Reporter;
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
		Reporter.log("IMDB website was loaded.");
		//Opening new tab
		driver.switchTo().newWindow(WindowType.TAB);
		//Loading Wikipedia website
		driver.get("https://en.wikipedia.org/");
		Reporter.log("Wikipedia website was loaded.");
		//Maximizing the window
		driver.manage().window().maximize();
		Reporter.log("Maximized the window.");

	}

	@Test(dataProvider="inputProvider", dataProviderClass=DataProviders.class)
	public void imdbAndWikiTest(String movieName) {
		SoftAssert softAssert = new SoftAssert();
		boolean country, releaseDate, countryAndReleaseDate=false;
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
			Reporter.log("The Country in IMDB and Wikipedia was correctly matched. For the movie: "+movieName+"The country in the IMDB Website was: "+imdb.imdbActualCountryRelease+"The country in the Wikipedia Website was: "+wiki.wikiActualcountryRelease);
		}
		else
		{
			country = false;
			Reporter.log("The Country in the IMDB and the Wikipedia was not matched. For the movie: "+movieName+"The country in the IMDB Website was: "+imdb.imdbActualCountryRelease+"The country in the Wikipedia Website was: "+wiki.wikiActualcountryRelease);
		}
		//Checking if the release date in the wikipedia and imdb was same or not
		if(wiki.wikiActualCountryReleaseDate.contentEquals(imdb.imdbActualCountryReleaseDate)) {
			releaseDate = true;
			Reporter.log("The Release Date in the IMDB and the Wikipedia was correctly matched. For the movie: "+movieName+"The Release Date in the IMDB Website was: "+imdb.imdbActualCountryReleaseDate+"The Release Date in the Wikipedia Website was: "+wiki.wikiActualCountryReleaseDate);
		}
		else
		{
			releaseDate = false;
			Reporter.log("The Release Date in the IMDB and the Wikipedia was not matched. For the movie: "+movieName+"The Release Date in the IMDB Website was: "+imdb.imdbActualCountryReleaseDate+"The Release Date in the Wikipedia Website was: "+wiki.wikiActualCountryReleaseDate);
		}

		//Checking the country in imdb and wikipedia was true and release date in imdb and wikipedia was true
		if(country == true && releaseDate == true) {
			countryAndReleaseDate = true;
		}
		softAssert.assertTrue(countryAndReleaseDate);
		softAssert.assertAll();
	}

	@AfterTest
	//Method to close the driver
	public void driverClose() {

		driver.quit();
		Reporter.log("Driver closed.");
	}



}
