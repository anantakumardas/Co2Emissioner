package com.ananta.co2emissioner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonProcessingException;

import com.ananta.co2emissioner.connection.Connection;
import com.ananta.co2emissioner.constants.CO2EperKM;
import com.ananta.co2emissioner.constants.Constants;
import com.ananta.co2emissioner.input.Co2EmissionerInputValidation;
import com.ananta.co2emissioner.input.CommandLineParam;
import com.ananta.co2emissioner.logger.Co2EmissionerLogger;
import com.ananta.co2emissioner.parser.Co2EmissionerCommandLineParser;
import com.ananta.co2emissioner.parser.JSONparser;
import com.ananta.co2emissioner.resource.Co2EmissionerResources;
import com.ananta.co2emissioner.utils.CSVtoMap;

public class Co2emissioner {

	
	private static Co2EmissionerCommandLineParser cmdLineParser = null;			 
	private static CommandLineParam commandLineParam = null;
	private static Connection con = null;
	private static String layers = "";
	private static JSONparser jsonParser = new JSONparser();

	private static Logger logger = Co2EmissionerLogger.getLogger(Co2emissioner.class.getName(),Co2EmissionerResources.class.getName());
	
	
	public static void main(String args[]){

		int returnStatus = executeCo2EmissionerCalculator(args);

		System.exit(returnStatus);

	}


	private static int executeCo2EmissionerCalculator(String[] args) {
		int returnStatus =0;

		if (args == null || args.length == 0 ) {
			printHelp();
			return Constants.ERROR_EXIT_STATUS;
		}

		try {
			returnStatus = parseCommandLineArguments(args);

		} catch (Exception e) {
			System.out.println("Exception occured and error message :"+ e.getMessage());
			e.printStackTrace();
			return Constants.ERROR_EXIT_STATUS;
		}

		if(returnStatus==Constants.DEFAULT_EXIT_STATUS) {
			calculateCO2Emission(); 
		}

		return returnStatus;

	}



	private static int parseCommandLineArguments(String[] cliArgs)   { 

		Co2EmissionerLogger.log( Level.INFO, "Parsing Command Line arguments ",logger);
 		
 		String args[]= reformArgs(cliArgs);
			
		cmdLineParser = new Co2EmissionerCommandLineParser();

		if(cmdLineParser.parseCommandLineArguments(args)) {			
			commandLineParam = cmdLineParser.parse(args);
		}

		if (commandLineParam == null || (commandLineParam.getArgumentParseStatus()==Constants.ERROR_EXIT_STATUS)) {
			printHelp();
			return Constants.ERROR_EXIT_STATUS;
		}
		  if(!Co2EmissionerInputValidation.isValidInputs(commandLineParam)) {
  		
  			commandLineParam.setArgumentParseStatus(Constants.ERROR_EXIT_STATUS);
  			return Constants.ERROR_EXIT_STATUS;
  		}

		return Constants.DEFAULT_EXIT_STATUS;

	}

	private static String[] reformArgs(String[] cliArgs) {
		List<String> t = new ArrayList<String>();
		for(String s: cliArgs) {
			if(s.contains("=")) {
				t.add(s.substring(0,s.indexOf("=")));
				
				t.add(s.substring(s.indexOf("=")+1,s.length()));
			}else
				t.add(s);
		}
		Object[] array =  t.toArray();
		
 		
		String[] dest = new String[array.length];
		System.arraycopy(array, 0, dest, 0, array.length);

 		return dest;
	}


	private static void calculateCO2Emission() {

		String startCity = commandLineParam.getStartCity();
		String endCity = 		commandLineParam.getEndCity();
		String trasportation_method =  commandLineParam.getTransportation_method();


		String API_TOKEN = System. getenv("ORS_TOKEN");
		con = new Connection(API_TOKEN);

		layers = Constants.OPENROUTESERVICE_SEARCH_API_GET_QUERY_PARAM_LAYERS_VALUE;
 		
		double distance = getDistanceBetweenCities(startCity,endCity);
		double co2Calculated = co2Calculator(distance , trasportation_method);
		System.out.println("\n Your trip caused " +  co2Calculated + " kg of CO2-equivalent.\n");

	}
	private static double  co2Calculator(double distance, String trasportation_method) {
		double co2Calculated = 0.0;
		double gm = 0.0;
		Map<String, String> response;
		try {
			response = CSVtoMap.readCSVToMap(new File(System. getenv("CO2_EMISSION_CSV_FILE_PATH")));
			
			gm = Double.valueOf(response.get(trasportation_method));
			co2Calculated =  (gm * distance)/1000.00;
			
		} catch (JsonProcessingException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 
		return co2Calculated;
		
 	}


	private static double  getDistanceBetweenCities(String startCity, String endCity){
		String coordinate1 = getCoordinate(  startCity); 
		String coordinate2 = getCoordinate(  endCity);
		double distance = getDistance(coordinate1, coordinate2);
		
		return distance; 
	}

	private static String getCoordinate(String startCity) { 

		String jsonResponse  = con.httpGet(startCity, layers).readEntity(String.class);	
		String coordinate = jsonParser.getLocationCoordinates(jsonResponse);

		Co2EmissionerLogger.log( Level.INFO, "coordinate for " + coordinate + " is : " + coordinate ,logger);

		return coordinate;
	}

	private static double getDistance(String coordinate1 ,String coordinate2) {

		String jsonResponse  = con.httpPost(coordinate1, coordinate2).readEntity(String.class);	;	
		 

		double distance = jsonParser.getDistance(jsonResponse);
		
		
		Co2EmissionerLogger.log( Level.INFO, "distance:" + distance ,logger);

		
		return distance;
		
		
	}


	private static void printHelp() {

		Co2EmissionerLogger.log( Level.SEVERE, "Comamnd line arguments are not prope  ",logger);
		Co2EmissionerLogger.log( Level.INFO, "Example command : \"java -jar co2emissioner-0.0.1-SNAPSHOT-jar-with-dependencies.jar --start Hamburg --end Berlin --transportation-method medium-diesel-car\"  ",logger);
  	}

	

}
