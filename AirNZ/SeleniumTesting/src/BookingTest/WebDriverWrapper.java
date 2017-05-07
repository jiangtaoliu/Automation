package BookingTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverWrapper {
	
    WebDriver driver;
    
	public WebDriverWrapper()
	{
		this.driver = new ChromeDriver();
	}
	
	public void click(WebElement element)
	{
		element.click();
	}
}
