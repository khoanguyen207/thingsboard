package org.thingsboard.selenium;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCustomerTest {

    @Test
    public void createCustomerTest(){

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

        //Create new customer
        String customer_xpath = "//*[2]/figure[1]/md-card[@class='ng-scope _md' and " +
                "1]/md-card-content[1]/md-grid-list[@class='ng-isolate-scope _md' and " +
                "1]/md-grid-tile[@class='card-tile ng-scope ng-isolate-scope' and " +
                "1]/figure[1]/a[@class='tb-card-button md-raised md-primary md-button ng-scope md-ink-ripple layout-column' and 1]";

        //the element is an Angular element, so you have to induce WebDriverWait for the element to be visible
        WebElement customer_link = new WebDriverWait(driver, 20).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(customer_xpath)));
        customer_link.click();

        WebElement add_button = driver.findElement(By.xpath("//*[@class='tb-btn-footer md-accent md-hue-2 md-fab md-button ng-scope md-ink-ripple']/ng-md-icon[@class='ng-scope' and 1]"));
        add_button.click();

        //Customer info to add
        String customer_name = "Selenium test customer";
        String customer_description = "selenium_test";

        driver.findElement(By.xpath("//input[@id='input_416']")).sendKeys(customer_name);
        WebElement customer_desc_field = driver.findElement(By.xpath("//textarea[@id='input_417']"));
        customer_desc_field.sendKeys(customer_description);
        Select customer_country = new Select(driver.findElement(By.xpath("//*[@id=\"select_419\"]")));
        customer_country.selectByVisibleText("Netherlands");

        WebElement customer_city = driver.findElement(By.xpath("//input[@id='input_421']"));
        customer_city.sendKeys("Amsterdam");
        WebElement customer_address = driver.findElement(By.xpath("//input[@id='input_424']"));
        customer_address.sendKeys("Test 123");
        WebElement customer_phone = driver.findElement(By.xpath("//input[@id='input_426']"));
        customer_phone.sendKeys("0612345678");
        WebElement customer_email = driver.findElement(By.xpath("//input[@id='input_427']"));
        customer_email.sendKeys("testemail@gmail.com");

//        WebElement add_button1 = driver.findElement(By.xpath("//input[@type='submit']"));
        WebElement add_button1 = new WebDriverWait(driver, 20).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='md-raised md-primary md-button md-ink-ripple']/span[@class='ng-binding ng-scope' and 1]")));
        add_button1.click();

         /*
        Check if test goes well
         */
        try{
            WebElement created_customer = driver.findElement(By.xpath("//*[@id=\"tb-vertical-container\"]/div/div[2]/div/section[1]/div[1]/md-card/section/md-card-title"));
            if (created_customer.getText().equals("Selenium test customer")){
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
