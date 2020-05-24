package com.ananta.co2emissioner.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonProcessingException;
import org.junit.Test;

public class CSVtoMapTest {

	@Test
	public void testReadCSVToMap() {
 
		try {
			String CO2_EMISSION_CSV_FILE_PATH = System. getenv("CO2_EMISSION_CSV_FILE_PATH");

 			Map<String, String> response =  CSVtoMap.readCSVToMap(new File(CO2_EMISSION_CSV_FILE_PATH));

			assertTrue(StringUtil.isNotEmptyString(response.get("small-diesel-car")));

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
