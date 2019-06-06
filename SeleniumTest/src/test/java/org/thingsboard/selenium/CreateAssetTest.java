package org.thingsboard.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class CreateAssetTest {

    @Test
    public void createAssetTest() throws IOException {

        // for windows or linux machines, update the chromedriver into the resources folder
        // https://chromedriver.storage.googleapis.com/index.html?path=74.0.3/
        System.setProperty("webdriver.chrome.driver", "D:\\Khoa\\Documents\\School\\HvA\\SE\\Jaar 3\\ASV\\chromedriver_win32\\chromedriver.exe");

        // Create Chrome driver instance and login as a tenant
        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Navigate to Thingsboard on localhost
        driver.get("http://192.168.99.100:9090/");

        // Type login info tenant in the login form
        String username = "tenant@thingsboard.org";
        String password = "tenant";

        driver.findElement(By.id("username-input")).sendKeys(username);
        driver.findElement(By.id("password-input")).sendKeys(password);

        // Click on button submit
        driver.findElement(By.xpath("//*[@class='md-raised md-button md-tb-dark-theme md-ink-ripple']")).click();

        //Create new asset
        String asset_xpath = "//li[4]/tb-menu-link[@class='ng-isolate-scope' " +
                "and 1]/a[@class='md-button ng-scope md-ink-ripple' and 1]/span[@class='ng-binding ng-scope' and 1]";

        //the element is an Angular element, so you have to induce WebDriverWait for the element to be visible
        WebElement asset_link = new WebDriverWait(driver, 20).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(asset_xpath)));
        asset_link.click();

        WebElement add_button = driver.findElement(By.xpath("//*[@class='tb-btn-footer md-accent md-hue-2 md-fab md-button " +
                "ng-scope md-ink-ripple']/*[@class='ng-scope']"));
        add_button.click();

        //Asset info to add
        String asset_name = "Selenium test";
        String asset_type = "selenium_test";

        WebElement asset_name_field = driver.findElement(By.xpath("//input[@id='input_150']"));
        asset_name_field.sendKeys(asset_name);
        WebElement asset_type_field = driver.findElement(By.xpath("//input[@id='fl-input-151']"));
        asset_type_field.sendKeys(asset_type);

        WebElement add_button1 = driver.findElement(By.xpath("//button[@class='md-raised md-primary " +
                "md-button md-ink-ripple']/span[@class='ng-binding ng-scope' and 1]"));
        add_button1.click();

    }

}
