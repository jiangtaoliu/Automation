package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SelectSeatsPage extends BasePage {

    public SelectSeatsPage(WebDriver driver, String url) {
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
        	List<WebElement> seats = driver.findElements(By.cssSelector(".vui-ss-seat.vui-circle.vui-link-unstyled.vui-ss-available.is-standard"));
        	if (!seats.isEmpty())
        	{
        		// use Mouse hover action for that element
        		builder.moveToElement(seats.get(0)).build().perform();
        		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        		// finally click on that element
        		builder.click(seats.get(0)).build().perform();
        		System.out.printf("available seats number is %d",seats.size());
        		
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            	Actions popup=new Actions(driver);
            	// find the element which we want to Select from auto suggestion
            	List<WebElement> confs = driver.findElements(By.xpath("//*[@id=\"main-container\"]/vbk-seatselectpage/div/form/div[2]/div/div/div/div[1]/div[1]/button"));
            	// use Mouse hover action for that element
            	popup.moveToElement(confs.get(0)).build().perform();
            	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            	// finally click on that element
            	popup.click(confs.get(0)).build().perform();
            	System.out.printf("button number is %d",confs.size());
            	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            	Actions builder2=new Actions(driver);
            	// find the element which we want to Select from auto suggestion
            	List<WebElement> seats2 = driver.findElements(By.cssSelector(".vui-ss-seat.vui-circle.vui-link-unstyled.vui-ss-available.is-standard"));
            	// use Mouse hover action for that element
            	builder2.moveToElement(seats2.get(0)).build().perform();
            	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            	// finally click on that element
            	builder2.click(seats2.get(0)).build().perform();
                System.out.println(seats2.size());
        	}
            
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        	Actions popup2=new Actions(driver);
        	// find the element which we want to Select from auto suggestion
        	List<WebElement> conf2=driver.findElements(By.xpath("//*[@id=\"main-container\"]/vbk-seatselectpage/div/form/div[2]/div/div/div/div[1]/div[1]/button"));
        	// use Mouse hover action for that element
        	popup2.moveToElement(conf2.get(0)).build().perform();
        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        	// finally click on that element
        	popup2.click(conf2.get(0)).build().perform();
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
