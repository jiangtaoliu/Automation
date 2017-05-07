package com.company.restassuredtest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        given().
        when().
            get("http://ergast.com/api/f1/2017/circuits.json").
        then().
            assertThat().
            body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
        
        assertTrue( true );
    }
    public void testApp1()
    {
        given().
        when().
            get("http://ergast.com/api/f1/2017/circuits.json").
        then().
            assertThat().
            body("MRData.CircuitTable.Circuits.circuitId",hasSize(21));
        
        assertTrue( true );
    }
}
