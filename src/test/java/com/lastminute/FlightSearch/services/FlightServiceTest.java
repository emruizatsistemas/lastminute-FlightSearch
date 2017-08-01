package com.lastminute.FlightSearch.services;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lastminute.CsvFiles;
import com.lastminute.FlightSearch.domain.Airline;
import com.lastminute.FlightSearch.domain.Airport;
import com.lastminute.FlightSearch.domain.Flight;
import com.lastminute.FlightSearch.dto.FlightSearchDto;
import com.lastminute.FlightSearch.dto.ResultFlightSearchDto;
import com.lastminute.FlightSearch.services.FlightServiceImpl;
import com.lastminute.FlightSearch.utility.LoadDataUtility;



public class FlightServiceTest
{
    
	
	private static Map<String,Airport> airPortList = null;
	
	private static Map<String,Airline> airLineList= null;
    
    private static List<Flight> flights= null;

    private static final Logger log =
	        Logger.getLogger(FlightServiceTest.class.getName());
	
    private IFlightService flightService = new FlightServiceImpl();
    
    private final int flightCount = 89;
    
    private final int airPortCount = 9;
    
    private final int airLineCount = 7;
       
    public static final Path RESOURCEDIRECTORY = Paths.get("src/test/resources");
	 
	public static final String CSV="Flights.csv";
	
	private Map<String, Float> flightMap;
	
	private static final String AMS="AMS";
	
	private static final String FRA="FRA";
	
	private static final String LHR="LHR";
	
	private static final String IST="IST";
	
	private static final String BCN="BCN";
	
	private static final String MAD="MAD";
	
	private static final String CDG="CDG";
	
	
    
    
    @BeforeClass
    public static void onceExecutedBeforeAll() {
        log.finer("@BeforeClass: onceExecutedBeforeAll");
        List<List<String>> prices = CsvFiles.readAllRecords(RESOURCEDIRECTORY.toAbsolutePath()+"/"+CSV);	    
        flights = LoadDataUtility.loadFlights(prices);	
    	airPortList = LoadDataUtility.loadAirPorts();
    	airLineList = LoadDataUtility.loadAirlines();
    }
 
    @Before
    public void executedBeforeEach() {
    	log.finer("@Before: executedBeforeEach");
        
    }
    

    
    /**
     * Verify Load Data 
     */
    @Test
    public void testLoadData(){
    	
    	log.finer("@Test: testLoadData");

    	assertNotNull(flights);
		assertSame(flightCount, flights.size());

    	assertNotNull(airPortList);
		assertSame(airPortCount, airPortList.size());
		
		assertNotNull(airLineList);
		assertSame(airLineCount, airLineList.size());
		
    }
    
    
    /**
     * Test Search Flight with result data
     */
    @Test
    public void testResultSearchFlight1(){
    	
    	log.finer("@Test: testResultSearchFlight1");
    	
      	//Verify SearchFlight
		FlightSearchDto flightSearchDto = new FlightSearchDto();
		flightSearchDto.setDepartureDate(addSubtractDaysToDate(new Date(),31));
		flightSearchDto.setOriginAirPort(AMS);
		assertNotNull(AMS,airPortList.get(AMS));
		flightSearchDto.setDestinationAirPort(FRA);
		assertNotNull(FRA,airPortList.get(FRA));
		flightSearchDto.setNumAdult(1);
		flightSearchDto.setNumChild(0);
		flightSearchDto.setNumInfant(0);
			
		log.info("* 1 adult, 31 days to the departure date, flying AMS -> FRA");
		List<ResultFlightSearchDto> result;
		try {
			result = flightService.searchFlight(flightSearchDto);
			assertNotNull(result);
			assertSame(3, result.size());
			flightMap = printVuelos(result);
			assertNotNull(flightMap.get("TK2372"));
			assertEquals(new Float(157.6),flightMap.get("TK2372"));
			assertNotNull(flightMap.get("TK2659"));
			assertEquals(new Float(198.4),flightMap.get("TK2659"));
			assertNotNull(flightMap.get("LH5909"));
			assertEquals(new Float(90.4),flightMap.get("LH5909"));
			
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
    }
    
    
    /**
     * Test Search Flight with result data
     */
    @Test
    public void testResultSearchFlight2(){
    	
    	log.finer("@Test: testResultSearchFlight2");
    	
      	//Verify SearchFlight
		FlightSearchDto flightSearchDto = new FlightSearchDto();
		flightSearchDto.setDepartureDate(addSubtractDaysToDate(new Date(),15));
		flightSearchDto.setOriginAirPort(LHR);
		assertNotNull(LHR,airPortList.get(LHR));
		flightSearchDto.setDestinationAirPort(IST);
		assertNotNull(IST,airPortList.get(IST));
		flightSearchDto.setNumAdult(2);
		flightSearchDto.setNumChild(1);
		flightSearchDto.setNumInfant(1);
			
		log.info("* 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST");
		List<ResultFlightSearchDto> result;
		try {
			result = flightService.searchFlight(flightSearchDto);
			assertNotNull(result);
			assertSame(2, result.size());
			flightMap = printVuelos(result);
			assertNotNull(flightMap.get("TK8891"));
			assertEquals(new Float(806.0),flightMap.get("TK8891"));
			assertNotNull(flightMap.get("LH1085"));
			assertEquals(new Float(481.19),flightMap.get("LH1085"));
			
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
    }
    
    
    /**
     * Test Search Flight with result data
     */
    @Test
    public void testResultSearchFlight3(){
    	
    	log.finer("@Test: testResultSearchFlight3");
    	
      	//Verify SearchFlight
		FlightSearchDto flightSearchDto = new FlightSearchDto();
		flightSearchDto.setDepartureDate(addSubtractDaysToDate(new Date(),2));
		flightSearchDto.setOriginAirPort(BCN);
		assertNotNull(BCN,airPortList.get(BCN));
		flightSearchDto.setDestinationAirPort(MAD);
		assertNotNull(MAD,airPortList.get(MAD));
		flightSearchDto.setNumAdult(1);
		flightSearchDto.setNumChild(2);
		flightSearchDto.setNumInfant(0);
			
		log.info("* 1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD");
		List<ResultFlightSearchDto> result;
		try {
			result = flightService.searchFlight(flightSearchDto);
			assertNotNull(result);
			assertSame(2, result.size());
			flightMap = printVuelos(result);
			assertNotNull(flightMap.get("IB2171"));
			assertEquals(new Float(909.09),flightMap.get("IB2171"));
			assertNotNull(flightMap.get("LH5496"));
			assertEquals(new Float(1028.43),flightMap.get("LH5496"));
			
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
    }
    
    
    /**
     * Test Search Flight no result data
     */
    @Test
    public void testNoResultSearchFlight(){
    	log.finer("@Test: testNoResultSearchFlight");
    	
      	//Verify SearchFlight
		FlightSearchDto flightSearchDto = new FlightSearchDto();
		flightSearchDto.setDepartureDate(new Date());
		flightSearchDto.setOriginAirPort(CDG);
		assertNotNull(CDG,airPortList.get(CDG));
		flightSearchDto.setDestinationAirPort(FRA);
		assertNotNull(FRA,airPortList.get(FRA));
		flightSearchDto.setNumAdult(1);
		flightSearchDto.setNumChild(0);
		flightSearchDto.setNumInfant(0);
			
		log.info("* CDG -> FRA");
		List<ResultFlightSearchDto> result;
		try {
			result = flightService.searchFlight(flightSearchDto);
			assertFalse(!result.isEmpty());
			log.info("********************************");
			log.info("0 results: No flights available");
			log.info("********************************");
			
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
    }
    
   
		
    
    /**
     * Print Result Flight Search
     * @param result
     * @return Map<String, Float>: flights map
     */
    private Map<String, Float> printVuelos(List<ResultFlightSearchDto> result) {
    	
    	Map<String, Float> flightMap = new HashMap<>();
    	
    	log.info("********************************");
    	log.info(result.size() + " results:");
		Iterator<ResultFlightSearchDto> it = result.iterator();
		while(it.hasNext()){
			ResultFlightSearchDto resultFlightSearchDto= it.next();
			flightMap.put(resultFlightSearchDto.getCodeFlight(), resultFlightSearchDto.getPrice());
			log.info("* " +  resultFlightSearchDto.getCodeFlight() +", "+ resultFlightSearchDto.getPrice()+ " €");
		}
		log.info("********************************");
		
		return flightMap;
		
	}

	@AfterClass
    public static void onceExecuteAfterAll() throws Exception {
    	
		log.finer("@AfterClass: onceExecuteAfterAll()");

    	airPortList = null;
    	assertNull(airPortList);

    	airLineList = null;
    	assertNull(airLineList);
    	
    	flights = null;
    	assertNull(flights);
 
    }

   
	/**
	 * Add or subtract days to date
	 * @param date
	 * @param days
	 * @return Date
	 */
	private Date addSubtractDaysToDate(Date date, int days){
		
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(date); 
	      calendar.add(Calendar.DATE, days);
	      
	      return calendar.getTime(); 
	
	}
    

}
