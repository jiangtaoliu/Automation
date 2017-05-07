package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SelectFlightPage extends BasePage {
    public SelectFlightPage(WebDriver driver, String url) {
		super(driver, url);
		this.driver = driver;
		this.url = url;
	}

	public boolean fillout() {
        try {
        	driver.get(url);
        	
        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        	driver.findElement(By.xpath("//*[@id=\"viewpoint-DOMESTIC-1\"]/div[3]/fieldset/div[1]/div/div/div[1]/div[1]/div[1]/label/div[1]/div[2]/input")).click();
        	
        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            //driver.findElement(By.xpath("//*[@id=\"viewpoint-DOMESTIC-2\"]/div[3]/fieldset/div[1]/div/div/div[1]/div[1]/div[1]/label/div[1]/div[2]/input")).click();;
        	
        	Actions builder=new Actions(driver);
        	// find the element which we want to Select from auto suggestion
        	WebElement ele=driver.findElement(By.xpath("//*[@id=\"viewpoint-DOMESTIC-2\"]/div[3]/fieldset/div[1]/div/div/div[1]/div[1]/div[1]/label/div[1]/div[2]/input"));
        	// use Mouse hover action for that element
        	builder.moveToElement(ele).build().perform();
        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        	// finally click on that element
        	builder.click(ele).build().perform();
        	
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            
        	Actions builder1=new Actions(driver);
        	// find the element which we want to Select from auto suggestion
        	WebElement ele1=driver.findElement(By.xpath("//*[@id=\"main-container\"]/vbk-selectitinerarypage/div/form/div[3]/div/div/div/div/button"));
        	// use Mouse hover action for that element
        	builder1.moveToElement(ele1).build().perform();
        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        	// finally click on that element
        	builder.click(ele1).build().perform();
        	
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            
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
