package org.thingsboard.selenium;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetRelationTest {

    @Test
    public void setRelationTest(){
        // for windows or linux machines, update the chromedriver into the resources folder
        // https://chromedriver.storage.googleapis.com/index.html?path=74.0.3/
        System.setProperty("webdriver.chrome.driver", "D:\\Khoa\\Documents\\School\\HvA\\SE\\Jaar 3\\ASV\\chromedriver_win32\\chromedriver.exe");

        // Create Chrome driver instance and login as a tenant
        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Navigate to Thingsboard on localhost
        driver.get("http://192.168.99.100:9090/");

        /*
         * Login in Thingsboard
         */
        // Type login info tenant in the login form
        String username = "tenant@thingsboard.org";
        String password = "tenant";

        driver.findElement(By.id("username-input")).sendKeys(username);
        driver.findElement(By.id("password-input")).sendKeys(password);

        // Click on button submit
        driver.findElement(By.xpath("//*[@class='md-raised md-button md-tb-dark-theme md-ink-ripple']")).click();

        /*
        Navigate to asset panel
         */
        String asset_xpath = "//li[4]/tb-menu-link[@class='ng-isolate-scope' " +
                "and 1]/a[@class='md-button ng-scope md-ink-ripple' and 1]/span[@class='ng-binding ng-scope' and 1]";

        //the element is an Angular element, so you have to induce WebDriverWait for the element to be visible
        WebElement asset_link = new WebDriverWait(driver, 20).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(asset_xpath)));
        asset_link.click();

        //span[@class='md-headline ng-binding']
        driver.findElement(By.xpath("//span[@class='md-headline ng-binding']")).click();
        // To relation tab
        driver.findElement(By.xpath("//md-tab-item[6 and @class='md-tab ' and @id='tab-item-365']")).click();

        driver.findElement(By.xpath("//*[@id=\"tab-content-16\"]/div/tb-relation-table/md-content/div/md-toolbar[1]/div/button[1]/md-icon")).click();

        //Relation info form
        driver.findElement(By.id("fl-input-155")).sendKeys("Contains");
        driver.findElement(By.xpath("//*[@id=\"select_158\"]")).sendKeys("Device");
        driver.findElement(By.xpath("//*[@id=\"fl-input-166\"]")).sendKeys("Selenium t");
        driver.findElement(By.xpath("/html/body/div[4]/md-dialog/form/md-dialog-actions/button[1]")).click();

        /*
        Check if test goes well
         */

        try{
            WebElement created_customer = driver.findElement(By.xpath("//*[@id=\"tab-content-831\"]/div/tb-relation-table/md-content/div/md-table-container/table/tbody/tr/td[4]"));
            if (created_customer.getText().equals("Selenium t")){
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed!");
            }
            driver.close();

        }catch (Exception e){
            System.out.println(e.getClass().toString());
            driver.close();
        }

    }

}
