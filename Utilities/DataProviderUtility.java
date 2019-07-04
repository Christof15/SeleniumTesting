package Utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.DataProvider;

public class DataProviderUtility {

@DataProvider(name = "SourceData")// Register the DataProvider as SourceData
public String[][] dataProviderMethod() throws IOException {
	int nrows = 9;
	int ncolumns = 2;
	String[][] data = null;
	List<String> row = null;
	List<List<String>> frame = new ArrayList<>();
	ReadExcel re;
	Properties p = new Properties();// This Object Read the .Properties File
	File f = new File(System.getProperty("user.dir") + Constants.PATHOBJREPO);
	FileReader obj = new FileReader(f);
	p.load(obj);
	String Excel = p.getProperty("Excel");
	re = new ReadExcel(Excel);// This Object Read the Excel File
	String value = null;
	for (int i = 1; i < nrows; i++) {//First For is the Number of Row
		row = new ArrayList<>();
		for (int j = 0; j < ncolumns; j++) {//Second For is for the Column
			value = re.getData("Login", i, j);
			if (value != null && !value.isEmpty()) {
				row.add(re.getData("Login", i, j));
			}
		}
		frame.add(row);
	}
	//Cast of the ArrayList to Assign the information to data in order to work the dataProvider
	data = new String[frame.size()][];
	for (int i = 0; i < data.length; i++) {
		data[i] = Arrays.copyOf(frame.get(i).toArray(), frame.get(i).size(), String[].class);
	}
	//Return all the Data Collected from the Excel File
	return data;
}

@DataProvider(name = "BrowserData")// Register the BrowserProvider as SourceData
public String[][] BrowserProviderMethod() throws IOException {
	int nrows = 9;
	int ncolumns = 6;
	String[][] BrowserData = null;
	List<String> row = null;
	List<List<String>> frame = new ArrayList<>();
	ReadExcel re;
	Properties p = new Properties();// This Object Read the .Properties File
	File f = new File(System.getProperty("user.dir") + Constants.PATHOBJREPO);
	FileReader obj = new FileReader(f);
	p.load(obj);
	String Excel = p.getProperty("Excel");
	re = new ReadExcel(Excel);// This Object Read the Excel File
	String value = null;
	for (int i = 1; i < nrows; i++) {//First For is the Number of Row
		row = new ArrayList<>();
		for (int j = 0; j < ncolumns; j++) {//Second For is for the Column
			value = re.getData("Browser", i, j);
			if (value != null && !value.isEmpty()) {
				row.add(re.getData("Browser", i, j));
			}
		}
		frame.add(row);
	}
	//Cast of the ArrayList to Assign the information to data in order to work the dataProvider
	BrowserData = new String[frame.size()][];
	for (int i = 0; i < BrowserData.length; i++) {
		BrowserData[i] = Arrays.copyOf(frame.get(i).toArray(), frame.get(i).size(), String[].class);
	}
	//Return all the Data Collected from the Excel File
	return BrowserData;
}

}
