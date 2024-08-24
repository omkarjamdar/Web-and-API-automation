package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BaseClass;

public class HomePage extends BaseClass {
    WebDriverWait wait = new WebDriverWait(driver,20);
    By securityModules = By.xpath("//span[@id=\"side-menu-securityModules\"]");
    By riskManagement = By.xpath("//*[text()='Risk Management']");
    By soEmail = By.xpath("//input[@name=\"identifier\"]");
    By soPassword = By.xpath("//input[@name=\"password\"]");
    By soLoginbtn = By.xpath("//button[@type=\"submit\"]");
    By riskReportFrame = By.xpath("//iframe[@title=\"Risk Report\"]");
    By elasticUsername = By.xpath("//input[@name=\"username\"]");
    By elasticPassword = By.xpath("//input[@name=\"password\"]");
    By elasticLogin = By.xpath("//button[@type=\"submit\"]");
    public WebElement securityModulesPane()
    {
       // return driver.findElement(securityModules);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(securityModules));
    }
     public WebElement RiskManagementTab()
     {
         return wait.until(ExpectedConditions.visibilityOfElementLocated(riskManagement));

     }

    public WebElement soEmailField()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(soEmail));
      //  return driver.findElement(soEmail);
    }
    public WebElement soPasswordField()
    {
        return driver.findElement(soPassword);
    }
    public WebElement soLoginBtn()
    {
        return driver.findElement(soLoginbtn);
    }

    public WebElement riskReportIframe()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(riskReportFrame));

    }
    public WebElement elasticUsernameField()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elasticUsername));

    }
    public WebElement elasticPasswordField()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elasticPassword));
    }

    public WebElement elasticLoginBtn()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elasticLogin));
    }

}
