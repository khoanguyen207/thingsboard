package org.thingsboard.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class LoginTest {

    @Test
    public void loginTest() throws IOException{

        String username = "tenant@thingsboard.org";
        String password = "tenant";

        // for windows or linux machines, update the chromedriver into the resources folder
        // https://chromedriver.storage.googleapis.com/index.html?path=74.0.3/
        System.setProperty("webdriver.chrome.driver", "D:\\Khoa\\Documents\\School\\HvA\\SE\\Jaar 3\\ASV\\chromedriver_win32\\chromedriver.exe");


        // Start a new Chrome browser instance and maximize the browser window
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to Thingsboard on localhost
        driver.get("http://192.168.99.100:9090/");

        // Type login info in the login form
        driver.findElement(By.id("username-input")).sendKeys(username);
        driver.findElement(By.id("password-input")).sendKeys(password);

        // Click on button submit
        driver.findElement(By.xpath("//*[@class='md-raised md-button md-tb-dark-theme md-ink-ripple']")).click();

        /*
        Check if test goes well
         */
        try{
            WebElement login_name = driver.findElement(By.xpath("//span[@class='tb-user-authority ng-binding']"));
            String expected = "Tenant administrator";
            System.out.println(login_name.getText());
            if (login_name.getText().equals(expected)){
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed");
            }
            driver.close();

        }catch (Exception e){
            System.out.println(e.getClass().toString());
            driver.close();
        }

    }

}
