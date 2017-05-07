package BookingTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Runner {
	
	TestCase tc;
	List<TestCase> tcl;
	
	public void runTest()
	{
		tcl = new ArrayList();
		tc = new TestCase();
		tcl.add(tc);
		
		this.run();
	}
	public void run()
	{
		boolean result = false;
		for(int i = 0; i < tcl.size(); i++)
		{
	        try {
	        	
	        	result = tcl.get(i).run();

	        } catch(Exception e) {
	            e.printStackTrace();
	        } finally {
	            //driver.close();
	        }

	        System.out.println("Test " + (result? "passed." : "failed."));
		}

	}

	public void sendResult()
	{
		//to-do
	}

}
