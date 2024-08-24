package utility;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import testcases.ConfigFileReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseClass {

    public static ExtentReports extent;
    public static SoftAssert softAssert;
    public static ChromeDriver driver;
    public static String filePath;


@BeforeSuite
   public void setup() throws IOException, ParseException {


        // typecasting obj to JSONObject
        JSONObject jo = ConfigFileReader.readFile("commonConf.json");
   // System.out.println("hiii" + BaseClass.filePath);
        //initialization of data
        String testEnv = jo.get("testEnv").toString();
        String user = jo.get("user").toString();
        String build = jo.get("build").toString();

        // Date and extent report configuration
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(currentDate);
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("Reports/"+"Sanity Testing for Build #" +build+" "+formattedDate+".html");
        extent.attachReporter(spark);
        spark.config().setDocumentTitle("Sanity testing Report");
        spark.config().setReportName("Sanity Testing");
        spark.config().setTheme(Theme.STANDARD);
        extent.setSystemInfo("Environment", testEnv);
        extent.setSystemInfo("User Name", user);
        extent.setSystemInfo("ERM Build",build);
        softAssert = new SoftAssert();


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();


    }


    @AfterSuite
    public void closeSetup()
    {
            try {
                softAssert.assertAll();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            finally {
                extent.flush();
                driver.close();
            }


    }

}