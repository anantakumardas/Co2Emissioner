package com.ananta.co2emissioner.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ananta.co2emissioner.input.CommandLineParam;
import com.ananta.co2emissioner.utils.StringUtil;

public class Co2EmissionerCommandLineParserTest {

	String args[] = {"--start", "Hamburg","--end","Berlin","--transportation-method","small-diesel-car"};	

	@Test
	public void testParse() {
		
		CommandLineParam commandLineParam = null;
		commandLineParam = new Co2EmissionerCommandLineParser().parse(args);
		
		assertTrue(StringUtil.isNotEmptyString(commandLineParam.getStartCity()));
		assertTrue(StringUtil.isNotEmptyString(commandLineParam.getEndCity()));
		assertTrue(StringUtil.isNotEmptyString(commandLineParam.getTransportation_method()));
	}

	@Test
	public void testParseCommandLineArguments() {
		boolean b = new Co2EmissionerCommandLineParser().parseCommandLineArguments(args);
		assertEquals(b,true); 
	}
}
