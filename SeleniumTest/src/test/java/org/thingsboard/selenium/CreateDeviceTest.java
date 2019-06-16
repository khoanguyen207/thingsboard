package org.thingsboard.selenium;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateDeviceTest {

    @Test
    public void createDeviceTest() throws InterruptedException {

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

        WebElement device_name_field = driver.findElement(By.xpath("//input[@id='input_178']"));
        device_name_field.sendKeys("Selenium test");

        WebElement asset_type_field = driver.findElement(By.id("fl-input-179"));
        asset_type_field.sendKeys("thermometer");

//        WebElement device_description_field = driver.findElement(By.xpath("//textarea[@id='input_182']"));
//        device_description_field.sendKeys("Selenium test");
        ///html/body/div[4]/md-dialog/form/md-dialog-actions/button[1]
        WebElement add_button1 = driver.findElement(By.xpath("//html/body/div[4]/md-dialog/form/md-dialog-actions/button[1]"));
        add_button1.click();

         /*
        Check if test goes well
         */
        try{
            WebElement created_device = driver.findElement(By.xpath("//*[@id=\"tb-vertical-container\"]/div/div[2]/div/section[4]/div[2]/md-card/section/md-card-title/md-card-title-text/span"));
            System.out.println(created_device.getText());
            Assert.assertTrue(created_device.getText().contains("Selenium test"));
//            if (created_device.getText().equals("Selenium test")){
//                System.out.println("Test Passed!");
//            } else {
//                System.out.println("Test Failed!");
//            }
            driver.close();

        }catch (Exception e){
            System.out.println(e.getClass().toString());
            driver.close();
        }
    }

}
