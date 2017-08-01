package com.lastminute.FlightSearch.utility;

import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lastminute.CsvFiles;
import com.lastminute.FlightSearch.domain.Airline;
import com.lastminute.FlightSearch.domain.Airport;
import com.lastminute.FlightSearch.domain.Flight;

/**
 * Load Data APP
 * @author emruiz
 *
 */
public class LoadDataUtility {
	
	private static final Logger log =
		        Logger.getLogger(LoadDataUtility.class.getName());
	
	public static final Path RESOURCEDIRECTORY = Paths.get("src/test/resources");
	
	public static final String CSV="Flights.csv";
	
	public static final String SEPARATOR=",";
	
	private static HashMap<String,Airline> airLineList = new HashMap<>();
	
	private static HashMap<String,Airport> airPortList = new HashMap<>();
	
	/**
	 * Load Data CVS Fligths
	 */
	public static List<Flight> readFlightsCSV(){
		
		log.info("Loading Flights CVS");
		
		 BufferedReader br = null;
		 List<Flight> flightList = new ArrayList<>();
		 List<List<String>> prices = CsvFiles.readAllRecords(RESOURCEDIRECTORY.toAbsolutePath()+"/"+CSV);	    
		 flightList = LoadDataUtility.loadFlights(prices);	
	     log.info("Load "+flightList.size() + " Flights");
	     return flightList;
	}
	
	/**
	 * Load Airlines
	 * @return Map<String, Airline>
	 */
	public static Map<String, Airline> loadAirlines() {
		
		log.info("Loading Airlines");
		
		saveAirline("IB",new Airline("IB","Iberia",Float.valueOf(10)));
		saveAirline("BA",new Airline("IB","British Airways",Float.valueOf(15)));
		saveAirline("LH",new Airline("LH","Lufthansa",Float.valueOf(7)));
		saveAirline("FR",new Airline("FR","Ryanair",Float.valueOf(20)));
		saveAirline("VY",new Airline("VY","Vueling",Float.valueOf(10)));
		saveAirline("TK",new Airline("TK","Turkish Airlines",Float.valueOf(5)));
		saveAirline("U2",new Airline("U2","Easyjet",new Float(19.90)));
		
		log.info("Load "+airLineList.size() + " Airlines");
		
		return airLineList;
		
	}
	
	/**
	 * Load AirPorts
	 * @return Map<String, Airport>
	 */
	public static Map<String, Airport> loadAirPorts(){
		

		log.info("Loading Airport");
	
		saveAirPort("MAD",new Airport("MAD", "Madrid"));
		saveAirPort("BCN",new Airport("BCN", "Barcelona"));
		saveAirPort("LHR",new Airport("LHR", "London"));
		saveAirPort("CDG",new Airport("CDG", "Paris"));
		saveAirPort("FRA",new Airport("FRA", "Frakfurt"));
		saveAirPort("IST",new Airport("IST", "Istanbul"));
		saveAirPort("AMS",new Airport("AMS", "Amsterdam"));
		saveAirPort("FCO",new Airport("FCO", "Rome"));
		saveAirPort("CPH",new Airport("CPH", "Copenhagen"));
		
		log.info("Load "+airPortList.size() + " Airports");
		
		return airPortList;
	}
	
	

	/**
	 * 
	 * @param codeIATA
	 * @param airline
	 * @param airLineList
	 */
   private static void saveAirline(String codeIATA, Airline airline){
    if (airLineList.containsKey(codeIATA)){
    	log.log(Level.WARNING, "codeIATA Airline {0} existing.",codeIATA);
    }
    else{
    	airLineList.put(codeIATA, airline);               
    }
   }
   
   /**
    * 
    * @param codeIATA
    * @param airport
    */
   private static void saveAirPort(String codeIATA, Airport airport){
	   if (airPortList.containsKey(codeIATA)){
		   log.log(Level.WARNING, "codeIATA Airport {0} existing.",codeIATA);
	    }
	    else{
	    	airPortList.put(codeIATA, airport);               
	    } 
   }

   /**
    * Set to Model Fligth
    * @param prices
    * @return List<Flight>
    */
   public static List<Flight> loadFlights(List<List<String>> prices) {
	List<Flight> flightList  = new ArrayList<>();
	
	Iterator<List<String>> it = prices.iterator();
	while (it.hasNext()){
		
		List<String> fields = it.next();
		
		String origin = fields.get(0);
		String destination=fields.get(1);
		String airline = fields.get(2).substring(0, 2);
	
		String flightCode = fields.get(2).substring(2, fields.get(2).length());
		Float price = Float.valueOf(fields.get(3));
		
		Flight flight = new Flight(origin, destination, airline, flightCode, price);
      
		flightList.add(flight);
		
	}
	
	return flightList;
}

}
