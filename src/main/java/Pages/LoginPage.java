package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BaseClass;

public class LoginPage extends BaseClass
{
    By username = By.xpath("//label[@for=\"username\"]");
    By password = By.xpath("//label[@for=\"password\"]");
    By signIn = By.xpath("//input[@name=\"login\"]");
    public WebElement userNameField()
    {
        return driver.findElement(username);
    }

    public WebElement passwordField()
    {
        return driver.findElement(password);
    }
    public WebElement signInBtn()
    {
        return driver.findElement(signIn);
    }

}
