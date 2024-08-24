package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BaseClass;

public class RiskReportPage extends BaseClass {
    WebDriverWait wait = new WebDriverWait(driver,20);

    By riskReportDropDownBtn = By.xpath("//button[@class=\"euiFormControlLayoutCustomIcon euiFormControlLayoutCustomIcon--clickable\"]");
    By riskReportID = By.xpath("//div[contains(text(),'ReportId')][0]");
    By addFilterLogo = By.xpath("//button[@data-test-subj=\"addFilter\"]");
    By field = By.xpath("//div[@data-test-subj='filterFieldSuggestionList']//div//div//div[@data-test-subj='comboBoxInput']");
    By operator = By.xpath("//div[@data-test-subj='filterOperatorList']//div//div//div[@data-test-subj='comboBoxInput']");
    By value = By.xpath("//input[@placeholder='Enter a value']");
    By addFilterBtn = By.xpath("////span[contains(text(), 'Add filter')]");

    public WebElement getriskReportDropDownBtn()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(riskReportDropDownBtn));
    }

    public WebElement getRiskReportID()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(riskReportID));
    }

    public WebElement getAddFilterLogo()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addFilterLogo));
    }

    public WebElement getField()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(field));
    }

    public WebElement getOperator()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(operator));
    }

    public WebElement getValue()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(value));
    }

    public WebElement getAddFilterBtn()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addFilterBtn));
    }

}
