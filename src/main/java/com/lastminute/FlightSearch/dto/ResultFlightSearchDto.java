package com.lastminute.FlightSearch.dto;

/**
 * ResultFlightSearchDto: Result Search Flight
 * @author emruiz
 *
 */
public class ResultFlightSearchDto {
	
	String codeFlight; //flight code matching the route
	Float price; //total flight price (for all passengers)
	
	
	public ResultFlightSearchDto() {
		super();
	}
	
	public ResultFlightSearchDto(String codeFlight, Float price) {
		this.codeFlight = codeFlight;
		this.price = price;
	}

	public String getCodeFlight() {
		return codeFlight;
	}
	
	public void setCodeFlight(String codeFlight) {
		this.codeFlight = codeFlight;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ResultFlightSearchDto [codeFlight=" + codeFlight + ", price=" + price + "]";
	}

	
	
}
