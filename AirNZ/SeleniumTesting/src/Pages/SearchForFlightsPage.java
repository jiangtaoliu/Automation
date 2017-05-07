package Pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchForFlightsPage extends BasePage {
	
    public SearchForFlightsPage(WebDriver driver, String url) {
		super(driver, url);
		this.driver = driver;
		this.url = url;
	}

	public boolean fillout() {
        try {
        	driver.get(url);
        	
        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            WebElement element = driver.findElement(By.id("depart-from"));
            element.sendKeys("Auckland");

            WebElement elementTo = driver.findElement(By.id("depart-to"));
            elementTo.sendKeys("Wellington");
            
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            WebElement elementDateStart = driver.findElement(By.xpath("//*[@id=\"leaveDate\"]"));
            elementDateStart.sendKeys("08/08");
            WebElement elementDateEnd = driver.findElement(By.xpath("//*[@id=\"returnDate\"]"));
            elementDateEnd.sendKeys("10/08");
            elementDateEnd.submit();
            
            //WebElement elementChildren = driver.findElement(By.xpath("//*[@id=\"childCountDisplay\"]"));
            //Select select = new Select(driver.findElement(By.xpath("//*[@id=\"childCountDisplay\"]")));
            //select.selectByIndex(0);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            WebElement elementSearch = driver.findElement(By.xpath("//*[@id=\"search-panel-container\"]"));
            elementSearch.click();
            
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
