package com.lastminute.FlightSearch.dto;

import java.util.Date;

/**
 * FlightSearchDto: Search Parameters
 * @author emruiz
 *
 */
public class FlightSearchDto {
	
	String originAirPort; //airport of origin
	
	String destinationAirPort; //airport of destination
	
	Integer numAdult; //number of adult
	
	Integer numChild; // number of child
	
	Integer numInfant ; //number of infant
	
	Date departureDate; // date of departure

	
	
	public FlightSearchDto(String originAirPort, String destinationAirPort, Integer numAdult, Integer numChild,
			Integer numInfant, Date departureDate) {
		this.originAirPort = originAirPort;
		this.destinationAirPort = destinationAirPort;
		this.numAdult = numAdult;
		this.numChild = numChild;
		this.numInfant = numInfant;
		this.departureDate = departureDate;
	}

	public FlightSearchDto() {
		super();
	}

	public String getOriginAirPort() {
		return originAirPort;
	}

	public void setOriginAirPort(String originAirPort) {
		this.originAirPort = originAirPort;
	}

	public String getDestinationAirPort() {
		return destinationAirPort;
	}

	public void setDestinationAirPort(String destinationAirPort) {
		this.destinationAirPort = destinationAirPort;
	}

	public Integer getNumAdult() {
		return numAdult;
	}

	public void setNumAdult(Integer numAdult) {
		this.numAdult = numAdult;
	}

	public Integer getNumChild() {
		return numChild;
	}

	public void setNumChild(Integer numChild) {
		this.numChild = numChild;
	}

	public Integer getNumInfant() {
		return numInfant;
	}

	public void setNumInfant(Integer numInfant) {
		this.numInfant = numInfant;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	@Override
	public String toString() {
		return "FlightSearchDto [originAirPort=" + originAirPort + ", destinationAirPort=" + destinationAirPort
				+ ", numAdult=" + numAdult + ", numChild=" + numChild + ", numInfant=" + numInfant + ", departureDate="
				+ departureDate + "]";
	}
	
	
	
	

}
