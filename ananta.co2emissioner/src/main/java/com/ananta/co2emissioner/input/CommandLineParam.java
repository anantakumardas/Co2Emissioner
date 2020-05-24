package com.ananta.co2emissioner.input;

import com.ananta.co2emissioner.constants.Constants;

public class CommandLineParam {
	
	private String startCity=null;
	private String endCity = null; 
	private String transportation_method= null; 	
    private int argumentParseStatus = Constants.ERROR_EXIT_STATUS;

    
	public int getArgumentParseStatus() {
		return argumentParseStatus;
	}
	public void setArgumentParseStatus(int argumentParseStatus) {
		this.argumentParseStatus = argumentParseStatus;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	public String getTransportation_method() {
		return transportation_method;
	}
	public void setTransportation_method(String transportation_method) {
		this.transportation_method = transportation_method;
	}


}
