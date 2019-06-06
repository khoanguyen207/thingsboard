package org.thingsboard.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateDeviceTest {

    @Test
    public void createDeviceTest(){

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


        // Create new device
        String device_xpath = "//md-grid-tile[4]/figure[1]/md-card[@class='ng-scope _md' " +
                "and 1]/md-card-content[1]/md-grid-list[@class='ng-isolate-scope _md' " +
                "and 1]/md-grid-tile[@class='card-tile ng-scope ng-isolate-scope' " +
                "and 1]/figure[1]/a[@class='tb-card-button md-raised md-primary md-button ng-scope md-ink-ripple layout-column' and 1]";

        //the element is an Angular element, so you have to induce WebDriverWait for the element to be visible
        WebElement device_link = new WebDriverWait(driver, 20).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(device_xpath)));
        device_link.click();

        WebElement add_button = driver.findElement(By.xpath("//button[@class='tb-btn-footer md-accent md-hue-2 " +
                "md-fab md-button ng-scope md-ink-ripple']/ng-md-icon[@class='ng-scope' and 1]"));
        add_button.click();

        //Asset info to add
        String device_name = "Selenium test";
        String device_type = "default";

        WebElement device_name_field = driver.findElement(By.xpath("//input[@id='input_178']"));
        device_name_field.sendKeys(device_name);
//        WebElement device_type_field = driver.findElement(By.xpath("//input[@id='fl-input-187']"));
//        device_type_field.sendKeys(device_type);
        Select drop_type = new Select(driver.findElement(By.name("subType")));
        drop_type.selectByVisibleText("default");

        WebElement add_button1 = driver.findElement(By.xpath("//button[@class='md-raised md-primary md-button md-ink-ripple']/" +
                "span[@class='ng-binding ng-scope' and 1]"));
        add_button1.click();
    }

}
