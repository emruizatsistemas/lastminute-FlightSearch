package com.lastminute.FlightSearch.domain;

/**
 *  Flight: Data Model Flight
 * @author emruiz
 *
 */
public class Flight {

	String originAirport; //IATA code airport of origin
	String destinationAirport;  //IATA code airport of destination
	String airline;  //IATA code airline
	String flightCode;
	Float price; //base price (€)
	
	
	
	
	public Flight(String origin, String destination, String airline, String flightCode, Float price) {
		this.originAirport = origin;
		this.destinationAirport = destination;
		this.airline = airline;
		this.flightCode = flightCode;
		this.price = price;
	}

	
	public String getOriginAirport() {
		return originAirport;
	}



	public void setOriginAirport(String originAirport) {
		this.originAirport = originAirport;
	}



	public String getDestinationAirport() {
		return destinationAirport;
	}



	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}



	public String getAirline() {
		return airline;
	}
	
	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	public String getFlightCode() {
		return flightCode;
	}
	
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Flight [originAirport=" + originAirport + ", destinationAirport=" + destinationAirport + ", airline="
				+ airline + ", flightCode=" + flightCode + ", price=" + price + "]";
	}
	
	
	
	
}
