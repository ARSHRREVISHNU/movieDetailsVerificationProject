package testcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utils.XLUtility;

public class DataProviders {


	@DataProvider(name="inputProvider")
	//Data provider method
	public String[][] inputMethod() throws IOException {		
		String path = "./src/main/java/resources/DataSheet.xlsx";
		//Loading the sheet
		XLUtility xlUtil = new XLUtility(path);
		//Getting total no of rows in the excel sheet
		int totalRows = xlUtil.getRowCount("Sheet1");
		//Getting total no of columns in the sheet
		int totalCols = xlUtil.getCellCount("Sheet1", totalRows);
		//Creating 2D String object
		String filmNames[][] = new String[totalRows][totalCols];
		//Iterating the movies names from the sheet 
		for(int i=1; i<=totalRows; i++ ){
			for(int j=0; j<totalCols; j++ ) {
				filmNames[i-1][j] = xlUtil.getCellData("Sheet1", i, j); 
			}
		}
		//returning the movie names.
		return filmNames;
	}


}
