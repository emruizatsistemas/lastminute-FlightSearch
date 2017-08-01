package com.lastminute.FlightSearch.domain;

/**
 * Airline: Data Model Airline
 * @author emruiz
 *
 */
public class Airline {
	
	String codeIATA ; //IATA code Airline
	String name; //name Airline
	Float infantPrice; // infant price
	
	
	
	public Airline(String codeIATA, String name, Float infantPrice) {
		this.codeIATA = codeIATA;
		this.name = name;
		this.infantPrice = infantPrice;
	}

	public String getCodeIATA() {
		return codeIATA;
	}
	
	public void setCodeIATA(String codeIATA) {
		this.codeIATA = codeIATA;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Float getInfantPrice() {
		return infantPrice;
	}
	
	public void setInfantPrice(Float infantPrice) {
		this.infantPrice = infantPrice;
	}

	@Override
	public String toString() {
		return "Airline [codeIATA=" + codeIATA + ", name=" + name + ", infantPrice=" + infantPrice + "]";
	}
	
	

}
