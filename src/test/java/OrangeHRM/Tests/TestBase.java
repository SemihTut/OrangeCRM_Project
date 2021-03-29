package OrangeHRM.Tests;

import OrangeHRM.Utilities.BrowserUtils;
import OrangeHRM.Utilities.ConfigurationReader;
import OrangeHRM.Utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.base.Enums;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

enum Urls {

    URL,URL_DEV;

}

public class TestBase {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected static ExtentReports report;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest extentLogger;
    protected String url;
    @BeforeTest
    public void setUpTest(){
        //initialize the class
        report = new ExtentReports();

        //create a report path
        String projectPath = System.getProperty("user.dir");
        String path = projectPath +"/test-output/report.html";

        //initialize the html reporter with the report path
        htmlReporter = new ExtentHtmlReporter(path);

        //attach the html report to report object
        report.attachReporter(htmlReporter);

        //title in report
        htmlReporter.config().setReportName("Orange HRM Project");

        //set environment information
        report.setSystemInfo("Environment","QA");
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        report.setSystemInfo("OS",System.getProperty("os.name"));

    }

    @BeforeMethod
    @Parameters("env")
    public void setUpMethod(@Optional String env){
      /*  Map<String, String> propertiesMAP = ConfigurationReader.getPropertiesMAP();
        //if env variable is null use default url
        if(env==null){
            Urls urls = Urls.URL; //URL
            url=propertiesMAP.get(urls.name().toLowerCase());
        }else{
            url=ConfigurationReader.get(env+"_url");
        }
        //if it is not null, choose env based on value*/

        Driver.get().manage().window().maximize();
        Driver.get().get(ConfigurationReader.get("url"));

    }

    //ItestResult class describes the result of a test in TestNG
    @AfterMethod
    public void afterMethod(ITestResult result) throws InterruptedException, IOException {
        //if test failed
    /*    try {
            if (result.getStatus()==ITestResult.FAILURE){
                //record the name fo failed test case
                extentLogger.fail(result.getName());

                //take the screenshot and return location of screenshot
                String screenShotPath = BrowserUtils.getScreenshot(result.getName());
                //add your screen shot to your report
                extentLogger.addScreenCaptureFromPath(screenShotPath);

                //capture the exception and put inside the report
                extentLogger.fail(result.getThrowable());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //close driver
        Thread.sleep(2000);
        Driver.closeDriver();

    }

    @AfterTest
    public void tearDownTest(){
        //this is when the report is actually created
        report.flush();

    }

}
