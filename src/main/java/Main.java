import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String[] args)
    {
        String postmanLoginURL = "https://identity.getpostman.com/login";

        String login = PropertiesManager.getProperty("login");
        String password = PropertiesManager.getProperty("password");

        String loginElementID = "username";
        String passwordElementID = "password";
        String buttonID = "sign-in-btn";

        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        driver.get(postmanLoginURL);

        try
        {
            WebElement loginElement = driver.findElement(By.id(loginElementID));
            WebElement passwordElement = driver.findElement(By.id(passwordElementID));

            loginElement.sendKeys(login);
            passwordElement.sendKeys(password);
            passwordElement.submit();
        }
        catch(Exception exc)
        {
            System.out.println("I can't find one or more elements!");
            driver.quit();
            return;
        }

        try
        {
            WebElement avatarElement = driver.findElement(By.className("avatar"));
            System.out.println("Authorization is successful!");
        }
        catch (Exception exc)
        {
            System.out.println("Authorization is failed!");
        }
        driver.quit();
    }
}
