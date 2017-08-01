package com.lastminute.FlightSearch.services;

import java.util.List;

import com.lastminute.FlightSearch.dto.FlightSearchDto;
import com.lastminute.FlightSearch.dto.ResultFlightSearchDto;

/**
 * 
 * @author emruiz
 *
 */
interface IFlightService {
	
	List<ResultFlightSearchDto> searchFlight(FlightSearchDto flightSearchDto) throws Exception;
	
}
