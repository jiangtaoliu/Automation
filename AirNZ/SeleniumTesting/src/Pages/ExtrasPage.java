package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ExtrasPage extends BasePage {
	
    public ExtrasPage(WebDriver driver, String url) {
		super(driver, url);
		this.driver = driver;
		this.url = url;
	}

	public boolean fillout() {
        try {
        	driver.get(url);
        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            
        	Actions builder=new Actions(driver);
        	// find the element which we want to Select from auto suggestion
        	WebElement passager=driver.findElement(By.xpath("//*[@id=\"main-container\"]/vbk-extraspage/div/form/div[4]/div/button"));
        	// use Mouse hover action for that element
        	builder.moveToElement(passager).build().perform();
        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        	// finally click on that element
        	builder.click(passager).build().perform();
            
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
