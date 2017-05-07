package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	protected WebDriver driver;
	protected String url;
	
	public BasePage(WebDriver driver, String url){
		this.driver = driver;
		this.url = url;
		
		//PageFactory.initElements(driver, this);
	}
	
}