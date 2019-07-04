package Utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

Properties properties;

public void ReadData() throws IOException {
	Properties properties = new Properties();
	File f = new File(System.getProperty("user.dir") + Constants.PATHOBJREPO);
	FileReader obj = new FileReader(f);
	properties.load(obj);
}

public String getData(String data2) {
	String data = properties.getProperty(data2);
	return data;
}
}