package com.ananta.co2emissioner.connection;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConnectionTest {

	Connection c = new Connection(System. getenv("ORS_TOKEN"));
	
	 

	@Test
	public void testHttpPost() {
		assertEquals(c.httpPost("[11.544467,48.152126]" , "[13.40732,52.52045]").getStatus(),200);
	}

	@Test
	public void testHttpGet() {

		assertEquals(c.httpGet( "Munich", "locality").getStatus(),200);
		
	}

}
