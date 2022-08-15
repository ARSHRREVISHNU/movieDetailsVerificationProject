package testcases;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
	
	@BeforeTest
	public void driverMethod(){
		String var = "C:\\Users\\DELL\\eclipse-workspace\\moviedetailsverification\\src\\main\\java\\resources\\chromedriver_win32 (104)\\chromedriver.exe";
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
	
	
	/**
	 * @param movieName
	 */
	@Test(dataProvider="inputProvider")
	public void imdbAndWikiTest(String movieName) {
		//Window handling
		boolean country, releaseDate;
		
		Set<String> tabs = driver.getWindowHandles();
				ArrayList<String> all_tabs = new ArrayList<String>(tabs);
				driver.switchTo().window(all_tabs.get(0));
				//Creating object for IMDBPage
				IMDBPage imdb = new IMDBPage(driver);
		        //WikipediaPage wiki = new WikipediaPage(driver);
				imdb.textSearch(movieName);
				imdb.elementsCheck();
				driver.switchTo().window(all_tabs.get(1));
				//Creating object for Wikipedia Page
				WikipediaPage wiki = new WikipediaPage(driver);
				wiki.wikipediaTextSearch(movieName);
				wiki.wikipediaElements();
				System.out.println("Wiki Class value");
				System.out.println("---"+wiki.wikiActualcountryRelease);
				System.out.println("---"+wiki.wikiActualCountryReleaseDate);
				System.out.println("IMDB Class Value");
				System.out.println("---"+imdb.imdbActualCountryRelease);
				System.out.println("---"+imdb.imdbActualCountryReleaseDate);	
				
				if(wiki.wikiActualcountryRelease.contentEquals(imdb.imdbActualCountryRelease)) {
					country = true;
				}
				else
				{
					country = false;
				}
				if(wiki.wikiActualCountryReleaseDate.contentEquals(imdb.imdbActualCountryReleaseDate)) {
					releaseDate = true;
				}
				else
				{
					releaseDate = false;
				}
				
				
				Assert.assertEquals(country, releaseDate, "Country and Release date both are not same");
	}
	
	@AfterTest
	public void driverClose() {
		//System.out.println("Quit");
		driver.quit();
		}
	
	 
	@DataProvider(name="inputProvider")
	public String[][] inputMethod() throws IOException {		
		//String[][] movieName = {{"Vaalu"},{"Pushpa: The Rise"},{"Ghajini"},{"Legend"}};
		
		String path = "C:\\Users\\DELL\\eclipse-workspace\\moviedetailsverification\\src\\main\\java\\resources\\DataSheet.xlsx";
		XLUtility xlUtil = new XLUtility(path);
		int totalRows = xlUtil.getRowCount("Sheet1");
		int totalCols = xlUtil.getCellCount("Sheet1", totalRows);
		String filmNames[][] = new String[totalRows][totalCols];
		for(int i=1; i<=totalRows; i++ ){
			for(int j=0; j<totalCols; j++ ) {
				filmNames[i-1][j] = xlUtil.getCellData("Sheet1", i, j); 
			}
		}
		
		return filmNames;
	}
}
