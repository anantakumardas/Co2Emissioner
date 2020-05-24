package com.ananta.co2emissioner.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

 import com.ananta.co2emissioner.logger.Co2EmissionerLogger;
import com.ananta.co2emissioner.resource.Co2EmissionerResources;

public class JSONparser {

	private static Logger logger = Co2EmissionerLogger.getLogger(JSONparser.class.getName(),Co2EmissionerResources.class.getName());

	
	public  double getDistance(String jsonString){
		String  distance = "";
		HashMap<String, ArrayList<ArrayList<Object>>> map = new HashMap<String,  ArrayList<ArrayList<Object>>>();

		ObjectMapper mapper = new ObjectMapper();
		try
		{
 			map = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});			 
					
			Co2EmissionerLogger.log( Level.INFO, "Distance  key = " + map.keySet().toString(),logger);
			
			distance =  map.get("distances").get(0).get(1).toString()  ;
			
			Co2EmissionerLogger.log( Level.INFO, "Distance  = " + distance,logger);

  			
		} 
		catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Double.valueOf(distance);
	}
	
	public String getLocationCoordinates(String jsonString){
		String coordinates = "";
		HashMap<String, ArrayList<HashMap>> map = new HashMap<String,  ArrayList<HashMap>>();

		ObjectMapper mapper = new ObjectMapper();
		try
		{
 			map = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});			 

 			Co2EmissionerLogger.log( Level.INFO, "Distance  key = " + map.keySet().toString(),logger);

 
			HashMap<String,Object> m = (HashMap<String,Object>) map.get("features").get(0).get("geometry");
 			
			coordinates =  m.get("coordinates").toString();
 			
			Co2EmissionerLogger.log( Level.INFO, "Coordinates  = " + coordinates,logger);

		} 
		catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return coordinates;
	}

}
