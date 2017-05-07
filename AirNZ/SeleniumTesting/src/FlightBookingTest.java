import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class FlightBookingTest{

    private static WebDriver driver;
    private static String baseUrl;

    public static void main(String[] args){
    	
    	//System.setProperty("webdriver.gecko.driver", "/Users/jetlau/myself/github/selenium/geckodriver");
        //driver = new FirefoxDriver();
    	
    	driver = new ChromeDriver();
        baseUrl = "https://flightbookings.airnewzealand.co.nz/";
        
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        boolean result;
        try {
            result = firstPage();
            
            if (result)
            {
            	result = secondPage();
            }
            if (result)
            {
            	result = thirdPage();
            }
            if (result)
            {
            	result = fourthPage();
            }
            if (result)
            {
            	result = fifthPage();
            }
            
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch(Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            //driver.close();
        }

        System.out.println("Test " + (result? "passed." : "failed."));
        
        if (!result) {
            //System.exit(1);
        }
    }
    
    private static boolean firstPage() {
        try {
        	driver.get(baseUrl);
        	
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
    
    private static boolean secondPage() {
        try {
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
            driver.findElement(By.xpath("//*[@id=\"main-container\"]/vbk-selectitinerarypage/div/form/div[3]/div/div/div/div/button")).click();
            
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    
    private static boolean thirdPage() {
        try {
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
    
    private static boolean fourthPage() {
        try {
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
    
    private static boolean fifthPage() {
        try {
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
}