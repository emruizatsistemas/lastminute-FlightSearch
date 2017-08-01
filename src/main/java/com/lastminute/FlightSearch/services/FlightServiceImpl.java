package com.lastminute.FlightSearch.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.lastminute.FlightSearch.domain.Airline;
import com.lastminute.FlightSearch.domain.Airport;
import com.lastminute.FlightSearch.domain.Flight;
import com.lastminute.FlightSearch.dto.FlightSearchDto;
import com.lastminute.FlightSearch.dto.ResultFlightSearchDto;
import com.lastminute.FlightSearch.exceptions.FieldMandatoryException;
import com.lastminute.FlightSearch.exceptions.InvalidAirPortException;
import com.lastminute.FlightSearch.exceptions.InvalidNumberAdultException;
import com.lastminute.FlightSearch.utility.LoadDataUtility;


/**
 * Flight Services
 * @author emruiz
 *
 */
public class FlightServiceImpl implements IFlightService {
	
	
	private static final Logger log =
	        Logger.getLogger(FlightServiceImpl.class.getName());
	
	private List<Flight> flightList;
	
	public FlightServiceImpl(){
		flightList = LoadDataUtility.readFlightsCSV();
	}
	
	/**
	 * Search Flighs and calculate total flight price (for all passengers)
	 * @param flightSearchDto : param search
	 * @return List<ResultFlightSearchDto>
	 * @throws Exception 
	 */
	public List<ResultFlightSearchDto> searchFlight(FlightSearchDto flightSearchDto) throws Exception {
		
		List<ResultFlightSearchDto> flightResultSearch=null;
		
		int numDaysPrior = calculateDaysPrior(new Date(), flightSearchDto.getDepartureDate());
		
			
		if (flightList != null && !flightList.isEmpty() && validateParamSearch(flightSearchDto)) {
			
			//Search
				
			List<Flight> results = flightList.stream().filter(f -> f.getOriginAirport().equals(flightSearchDto.getOriginAirPort()) && f.getDestinationAirport().equals(flightSearchDto.getDestinationAirPort())).collect(Collectors.toList());
			
			if (results != null){
				
				flightResultSearch = new ArrayList<>();
				
				Iterator<Flight> it = results.iterator();
				
				while(it.hasNext()){
					
					Flight flight = it.next();
					
					ResultFlightSearchDto resultFlightSearchDto = new ResultFlightSearchDto();
					resultFlightSearchDto.setCodeFlight(flight.getAirline()+flight.getFlightCode());
						
					Float price = totalFlightPrice(flight, flightSearchDto, numDaysPrior);
					resultFlightSearchDto.setPrice(price);
					
					flightResultSearch.add(resultFlightSearchDto);
				}
							
			}
		
		}
		
				
		return flightResultSearch;
	
	}
	
	/**
	 * Validate parameter Search
	 * @param flightSearchDto
	 * @return boolean true if parameter search valid
	 * @throws Exception
	 */
	private boolean validateParamSearch(FlightSearchDto flightSearchDto) throws Exception {
		boolean valid = true;
		
		String originAirPort = flightSearchDto.getOriginAirPort();
		String destinationAirPort = flightSearchDto.getDestinationAirPort();
		
		Map<String,Airport> airPortList = LoadDataUtility.loadAirPorts();
		
		if (originAirPort == null || destinationAirPort == null){
			//originAirPort and destinationAirPort  it's mandatory
			throw new FieldMandatoryException("AirPort origin and destination are mandatory");
		} else {
			//Validate
			Airport validateOriginAirPort= airPortList.get(originAirPort);
			if (validateOriginAirPort == null){
				//Invalid  origin AirPort
				throw new InvalidAirPortException("Invalid  origin AirPort");
			}
			Airport validatedestinationAirPort = airPortList.get(destinationAirPort);
			if (validatedestinationAirPort == null){
				//Invalid  destination AirPort
				throw new InvalidAirPortException("Invalid  destination AirPort");
			}
		}
		
		
		
		Date departureDate = flightSearchDto.getDepartureDate();
		if (departureDate == null){
			//departureDate it's mandatory
			throw new FieldMandatoryException("Date of departure is mandatory");
		}
		
		Integer numAdult = flightSearchDto.getNumAdult();
		if (numAdult == null ) {
			throw new FieldMandatoryException("Number of adult is mandatory");
			
		} else if (numAdult < 1){
			//Invalid number adult
			throw new InvalidNumberAdultException("Invalid number adult");
		}
		
		return valid;
	}

	/**
	 * Calculate total flight price (for all passengers) by the flight
	 * @param fligth
	 * @param flightSearchDto
	 * @param numDaysPrior
	 * @return Float: total flight price (for all passengers)
	 */
	private Float totalFlightPrice(Flight fligth, FlightSearchDto flightSearchDto, int numDaysPrior){
		Float totalFlightPrice = (float) 0.0;
		
		
		int numAdult = flightSearchDto.getNumAdult();
		if (numAdult > 0){
			totalFlightPrice = totalFlightPrice + (this.adultFlightPrice(fligth, numDaysPrior)* numAdult);
		}
		
		int numChild = flightSearchDto.getNumChild();
		if (numChild > 0){
			totalFlightPrice = totalFlightPrice + (this.childFlightPrice(fligth, numDaysPrior)* numChild);
		}
		
		int numInfant = flightSearchDto.getNumInfant();
		if (numInfant > 0){
			totalFlightPrice = totalFlightPrice + (this.infantFlightPrice(fligth)* numInfant);
		}
			
		totalFlightPrice = (float) (Math.round(totalFlightPrice * 100d) / 100d);
		log.finer("Total Flight Price:" + totalFlightPrice);
		return totalFlightPrice;
	}
	

	/**
	 *  Diff dates
	 * @param fromDate
	 * @param toDate
	 * @return int: num days
	 */
	private int calculateDaysPrior(Date fromDate, Date toDate) {
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fromDateString = df.format(fromDate);
        try {
        	fromDate = df.parse(fromDateString);
        } catch (ParseException ex) {
        }

        String toDateString = df.format(toDate);
        try {
        	toDate = df.parse(toDateString);
        } catch (ParseException ex) {
        }

        long inicialDateMs = fromDate.getTime();
        long finalDateMs = toDate.getTime();
        long diff = finalDateMs - inicialDateMs;
        double days = Math.floor(diff / (1000 * 60 * 60 * 24));
        
        log.info("DaysPrior: " + days);
        return ((int) days);
		
	}
	
	/**
	 * Calculate adult fligth price
	 * @param fligth
	 * @param numDaysPrior
	 * @return Float: price adult fligth
	 */
	private Float adultFlightPrice(Flight fligth, int numDaysPrior){
		Float  adultPrice = (float) 0.0;
		
		if (numDaysPrior >= 31){
			adultPrice =  (80 * fligth.getPrice()/100);
		}
		
		if (numDaysPrior >= 16 && numDaysPrior<=30){
			adultPrice = fligth.getPrice();
		}
		
		if (numDaysPrior>=3 && numDaysPrior <=15){
			adultPrice =  (120 * fligth.getPrice()/100);
		}
		
		if (numDaysPrior <= 2){
			adultPrice =  (150 * fligth.getPrice()/100);
		}
		
		return  adultPrice;
	}
	
	
	/**
	 * Calculate child flight price
	 * @param fligth
	 * @param numDaysPrior
	 * @return Float : price infant child
	 */
	private Float childFlightPrice(Flight fligth, int numDaysPrior){
		Float childPrice;
		childPrice = ((100-33) * adultFlightPrice(fligth, numDaysPrior)/100);
		
		return childPrice;
	}
	
	
	/**
	 * Calculate infant flight price
	 * @param fligth
	 * @return Float : price infant fligth
	 */
	private Float infantFlightPrice(Flight fligth){
		Float infantPrice = (float) 0.0;
		
		String nameAirline = fligth.getAirline();
		
		Map<String,Airline> airLineList = LoadDataUtility.loadAirlines();
		Airline  airline=  airLineList.get(nameAirline);
		if (airline != null){
			infantPrice = airline.getInfantPrice();
		} 
		
		return infantPrice;
		
	}
	
}
