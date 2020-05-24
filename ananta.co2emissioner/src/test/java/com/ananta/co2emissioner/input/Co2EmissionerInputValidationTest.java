package com.ananta.co2emissioner.input;

import static org.junit.Assert.*;

import org.junit.Test;

public class Co2EmissionerInputValidationTest {

	@Test
	public void testIsValidInputs() {
	
		CommandLineParam c = new CommandLineParam();
		c.setStartCity("Berlin");
		c.setEndCity("Munich");
		c.setTransportation_method("medium-diesel-car");
	
		assertEquals(   Co2EmissionerInputValidation.isValidInputs(c),true);

		c.setStartCity("Berlin");
		c.setEndCity("");
		c.setTransportation_method("medium-diesel-car");
	
		assertEquals(   Co2EmissionerInputValidation.isValidInputs(c),false);
		
 	}

	 

}
