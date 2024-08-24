package testcases;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.RiskReportPage;
import com.aventstack.extentreports.ExtentTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import utility.BaseClass;
import org.openqa.selenium.support.ui.WebDriverWait;
public class RiskLevel extends BaseClass {

    @Test
    void checkRiskLevel() throws InterruptedException {
        JSONObject jo =  ConfigFileReader.readFile("riskLevel.json");
        String ESCCURL = jo.get("URL").toString();
        String ESCCUsername = jo.get("ESCCUsername").toString();
        String ESCCPassword = jo.get("ESCCPassword").toString();
        String hostname = jo.get("hostname").toString();
        String expectedRiskLevel = jo.get("expectedRiskLevel").toString();
        String elasticUsername = jo.get("elasticUserName").toString();
        String elasticPassword = jo.get("elasticPassword").toString();
        String ENGGID = jo.get("ENGGID").toString();
        ExtentTest test = extent.createTest(ENGGID+": Risk level");

        driver.get(ESCCURL);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //login page
        LoginPage loginPageObj = new LoginPage();
        loginPageObj.userNameField().sendKeys(ESCCUsername);
        loginPageObj.passwordField().sendKeys(ESCCPassword);
        loginPageObj.signInBtn().click();

        //HomePage
        HomePage homePageObj = new HomePage();

        homePageObj.securityModulesPane().click();
        homePageObj.RiskManagementTab().click();
        driver.switchTo().frame(homePageObj.riskReportIframe());
        homePageObj.soEmailField().sendKeys(elasticUsername);
        homePageObj.soPasswordField().sendKeys(elasticPassword);
        homePageObj.soLoginBtn().click();
        homePageObj.elasticUsernameField().sendKeys(elasticUsername);
        homePageObj.elasticPasswordField().sendKeys(elasticPassword);
        homePageObj.elasticLoginBtn().click();

        /*
        //Risk report tab
        RiskReportPage riskReportPage = new RiskReportPage();
        riskReportPage.getriskReportDropDownBtn().click();
        System.out.println(riskReportPage.getRiskReportID().getText());
        riskReportPage.getRiskReportID().click();
        test.fail("check configuration");
        */

        RiskReportPage riskReportPage = new RiskReportPage();
        riskReportPage.getAddFilterLogo().click();
        riskReportPage.getField().sendKeys("Hostname.keyword");
        riskReportPage.getOperator().sendKeys("is");
        riskReportPage.getValue().sendKeys(hostname);
        riskReportPage.getAddFilterBtn().click();

    }
    @AfterTest
    void setClose()
    {

    }
}
