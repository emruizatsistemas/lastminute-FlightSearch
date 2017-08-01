package com.lastminute.FlightSearch.domain;

/**
 * Airport: Data Model Airport
 * @author emruiz
 *
 */
public class Airport {
	
	String codeIATA ; //IATA code AirPort
	String city;
	
	
	public Airport(String codeIATA, String city) {
		this.codeIATA = codeIATA;
		this.city = city;
	}

	public String getCodeIATA() {
		return codeIATA;
	}
	
	public void setCodeIATA(String codeIATA) {
		this.codeIATA = codeIATA;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "Airports [codeIATA=" + codeIATA + ", city=" + city + "]";
	}
	
	

}
