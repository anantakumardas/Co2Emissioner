package com.ananta.co2emissioner.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ananta.co2emissioner.connection.Connection;

public class JSONparserTest {
	
	String API_TOKEN = System. getenv("ORS_TOKEN");
	Connection con = new Connection(API_TOKEN);
	
	@Test
	public void testGetDistance() {
		 
		String jsonResponse  = con.httpPost("[11.544467,48.152126]" , "[13.40732,52.52045]").readEntity(String.class);	;	
 		double distance = new JSONparser().getDistance(jsonResponse);
 		assertTrue(distance > 0.0); 
 	}

	@Test
	public void testGetLocationCoordinates() {
		String coordinates = null;
		String jsonResponse = con.httpGet( "Munich", "locality").readEntity(String.class);
		coordinates= new JSONparser().getLocationCoordinates(jsonResponse);
		assertNotNull(coordinates);
				
	}

}
