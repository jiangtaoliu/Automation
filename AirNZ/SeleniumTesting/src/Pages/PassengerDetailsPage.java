package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PassengerDetailsPage extends BasePage {
    public PassengerDetailsPage(WebDriver driver, String url) {
		super(driver, url);
		this.driver = driver;
		this.url = url;
	}

	public boolean fillout() {
        try {
        	driver.get(url);
        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            new Select(driver.findElement(By.id("travellers[0].title"))).selectByVisibleText("Mr");
            driver.findElement(By.id("travellers[0].firstName")).clear();
            driver.findElement(By.id("travellers[0].firstName")).sendKeys("testliu");
            
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.findElement(By.id("travellers[0].surname")).clear();
            driver.findElement(By.id("travellers[0].surname")).sendKeys("testjet");
            driver.findElement(By.id("travellers[0].phoneAreaCode")).clear();
            
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.findElement(By.id("travellers[0].phoneAreaCode")).sendKeys("21");
            driver.findElement(By.id("travellers[0].phoneNumber")).clear();
            driver.findElement(By.id("travellers[0].phoneNumber")).sendKeys("1234567");
            driver.findElement(By.id("emailAddress")).clear();
            driver.findElement(By.id("emailAddress")).sendKeys("testjet@gmail.com");
            
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.findElement(By.id("submitBtn")).click();        	
            
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
	public String getAfterUrl()
	{
        String afterUrl = driver.getCurrentUrl();
        return afterUrl;
	}
}
