package Utilities;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class Screenshot {

public static void ScreenShot(WebDriver d) throws IOException {
	File src = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src, new File(Constants.PATHSCREENSHOT + "Evidence.png"));
		if (src.exists()){
		FileUtils.copyFile(src, new File(Constants.PATHSCREENSHOT + "Evidence"+ System.currentTimeMillis()+".png"));
	}
}
}