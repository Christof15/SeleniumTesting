package Report;

import Utilities.BrowserChoice;
import Utilities.Constants;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import org.testng.*;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyReport {

public static ExtentSparkReporter spark;
public static ExtentReports extent;
public static ExtentTest test;
WebDriver d;

public String getScreenshot(WebDriver d, String screenshotName) throws Exception {
	String dateName = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date());
	TakesScreenshot ts = (TakesScreenshot) d;
	File source = ts.getScreenshotAs(OutputType.FILE);
	//after execution, you could see a folder "FailedTestsScreenshots" under src folder
	String destination = System.getProperty(Constants.USER_DIR) + "/Reports/Evidence/" + screenshotName + dateName + ".png";
	File finalDestination = new File(destination);
	FileUtils.copyFile(source, finalDestination);
	return destination;
}

@BeforeSuite
public void setUp() throws Exception {
	//Read Properties File for read the report
	Properties p = new Properties();
	File f = new File(System.getProperty("user.dir") + Constants.PATHREPREPO);
	FileReader obj = new FileReader(f);
	p.load(obj);
	//Information in ReportRepository
	String OSTitle = p.getProperty("OSTitle");
	String OSVersion = p.getProperty("OSVersion");
	String HostNameTitle = p.getProperty("HostNameTitle");
	String HostName = p.getProperty("HostName");
	String EnvironmentTitle = p.getProperty("EnvironmentTitle");
	String Environment = p.getProperty("Environment");
	String ReportTitle = p.getProperty("ReportTitle");
	String TimeFormat = p.getProperty("TimeFormat");
	String ReportName = p.getProperty("ReportName");
	spark = new ExtentSparkReporter(System.getProperty("user.dir")+ "\\Reports\\Results.html");
	extent = new ExtentReports();
	extent.attachReporter(spark);
	spark.config().setAutoCreateRelativePathMedia(true);
	spark.config().setCSS("css-string");
	spark.config().setProtocol(Protocol.HTTPS);
	extent.setSystemInfo(OSTitle, OSVersion);
	extent.setSystemInfo(HostNameTitle, HostName);
	extent.setSystemInfo(EnvironmentTitle, Environment);
	spark.config().setTheme(Theme.DARK);
	spark.config().setDocumentTitle(ReportTitle);
	spark.config().enableTimeline(true);
	spark.config().setTimeStampFormat(TimeFormat);
	spark.getAuthorContextInfo();
	spark.getTestList();
	spark.getReportStatusStats();
	spark.getTestRunnerLogs();
	spark.config().setAutoCreateRelativePathMedia(true);
	spark.config().setReportName(ReportName);
}

@AfterMethod
// Report the Result of the Test Case
public void getResult(ITestResult result) throws Exception {
	if (result.getStatus() == ITestResult.FAILURE) {
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test Case FAILED due to below issues:", ExtentColor.RED));
		test.log(Status.FAIL, result.getThrowable());
		//Enable Webdriver to take screenshot
		BrowserChoice b=new BrowserChoice();
		d = b.SelectBrowser("Firefox");
		Screenshot.ScreenShot(d);
		//Add the Screenshot to the report
		test.fail("Details").addScreenCaptureFromPath( Constants.PATHSCREENSHOT+"Evidence.png");
	} else if (result.getStatus() == ITestResult.SUCCESS) {
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
	} else {
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
		test.skip(result.getThrowable());
	}
}

@AfterSuite
public void tearDown() {
	extent.flush();
}
}