package com.lastminute.FlightSearch.utility;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.List;
import java.util.Map;

import com.lastminute.FlightSearch.domain.Airline;
import com.lastminute.FlightSearch.domain.Airport;
import com.lastminute.FlightSearch.domain.Flight;

import org.junit.Test;
/**
 * Unit test for LoadDataUtility Class.
 */
public class LoadDataUtilityTest {
   
    
	 private final int flightCount = 89;
	
	
	
	
   @Test
   public void testLoadFlights(){
 	   
	    List<Flight> flightList = LoadDataUtility.readFlightsCSV();
	    
    	assertNotNull(flightList);
    	assertSame(flightCount, flightList.size());
    }
    
    /**
     * Test load Airports
     */
    @Test
    public void testLoadAirports(){
		Map<String,Airport> airPortList = LoadDataUtility.loadAirPorts();
		assertNotNull(airPortList);
		assertSame(9, airPortList.size());
		
	}
    
    /**
     * Test load AirLines
     */
    @Test
    public void testLoadAirlines(){
    	Map<String,Airline> airLineList = LoadDataUtility.loadAirlines();
    	assertNotNull(airLineList);
		assertSame(7, airLineList.size());
    }
}
