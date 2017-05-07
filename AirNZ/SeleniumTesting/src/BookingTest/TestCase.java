package BookingTest;

import java.util.concurrent.TimeUnit;

import Pages.ExtrasPage;
import Pages.PassengerDetailsPage;
import Pages.SearchForFlightsPage;
import Pages.SelectFlightPage;
import Pages.SelectSeatsPage;

public class TestCase {
	WebDriverWrapper wdw;
	String url = "https://flightbookings.airnewzealand.co.nz/";
	
	public TestCase()
	{
		this.wdw = new WebDriverWrapper();
		
	}
	
	public static void setUp(){

	}
	
	public void cleanUp(){
		//driver.manage().deleteAllCookies();
	}
	
	public static void tearDown(){
		//driver.close();
	}	
	
	public boolean run() throws InterruptedException
	{
		boolean result;
		SearchForFlightsPage sffp = new SearchForFlightsPage(this.wdw.driver, url);
		result = sffp.fillout();
		
		Thread.sleep(5000);
		String sfpUrl = sffp.getAfterUrl();
		SelectFlightPage sfp = new SelectFlightPage(this.wdw.driver, sfpUrl);
		result = sfp.fillout();

		Thread.sleep(5000);
		String pdpUrl = sfp.getAfterUrl();
		PassengerDetailsPage pdp = new PassengerDetailsPage(this.wdw.driver, pdpUrl);
		result = pdp.fillout();

		Thread.sleep(5000);
		String epUrl = pdp.getAfterUrl();
		ExtrasPage ep = new ExtrasPage(this.wdw.driver, epUrl);
		result = ep.fillout();

		Thread.sleep(5000);
		String sspUrl = ep.getAfterUrl();
		SelectSeatsPage ssp = new SelectSeatsPage(this.wdw.driver, sspUrl);
		result = ssp.fillout();
		
		return result;
	}

}
