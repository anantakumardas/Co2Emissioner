package com.ananta.co2emissioner.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ananta.co2emissioner.connection.Connection;
import com.ananta.co2emissioner.constants.Constants;
import com.ananta.co2emissioner.input.Co2EmissionerInputValidation;
import com.ananta.co2emissioner.input.CommandLineParam;
import com.ananta.co2emissioner.logger.Co2EmissionerLogger;
import com.ananta.co2emissioner.resource.Co2EmissionerResources;
import com.ananta.co2emissioner.utils.StringUtil;

public class Co2EmissionerCommandLineParser {

	private boolean debugMode=false;
	private static Logger logger = Co2EmissionerLogger.getLogger(Co2EmissionerCommandLineParser.class.getName(),Co2EmissionerResources.class.getName());

	
	public boolean isDebugMode() {
		return debugMode;
	}


	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	private String startCity=null;
	private String endCity = null; 
	private String transportation_method= null; 	
	private Map<String,String> co2EmissionerArguments = new HashMap<String,String>() ;
	private CommandLineParam commandLineParam = new CommandLineParam();

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


	public Map<String, String> getCo2EmissionerArguments() {
		return co2EmissionerArguments;
	}


	public void setCo2EmissionerArguments(Map<String, String> co2EmissionerArguments) {
		this.co2EmissionerArguments = co2EmissionerArguments;
	}


	private boolean checkArgSizeAndValue(int idx, List<String> argsList, String key) {
		if (argsList.size() <= idx) {
			if ( StringUtil.isNotEmptyString(key)) {
				return false;
			}
		} else if (StringUtil.isNotEmptyString(key) && argsList.get(idx).startsWith("--")) {
			return false;
		}
		return true;
	}
	
	private void setCommandLineparamFromCLI(){
        commandLineParam.setStartCity(startCity);;
        commandLineParam.setEndCity(endCity);
        commandLineParam.setTransportation_method(transportation_method);                
	}
	
	public CommandLineParam parse( String[] args)   {

		boolean status  = parseCommandLineArguments(args);
		
		if (!status)
			return commandLineParam;
                
            setCommandLineparamFromCLI();  
             
          
		return commandLineParam;

	}
	public boolean parseCommandLineArguments(String[] args)   {
		
		List<String> argList = new ArrayList<String>(Arrays.asList(args));

		Co2EmissionerLogger.log( Level.INFO, "args    = " + args,logger);

		
		for (int i = 0; i < argList.size(); i++) {
			String content = argList.get(i);
 			if ( StringUtil.isEmptyString(content))
				continue;

			if (content.equalsIgnoreCase(Constants.CO2_EMISSSIONER_START_CITY)) {
				if (!checkArgSizeAndValue(++i, argList, content))
					return false;
				startCity = argList.get(i);
 
				continue;
			}
			else if (content.equalsIgnoreCase(Constants.CO2_EMISSSIONER_END_CITY)){
				if (!checkArgSizeAndValue(++i, argList, content))
					return false;
				endCity = argList.get(i);
 
				continue;
			}else if (content.equalsIgnoreCase(Constants.CO2_EMISSSIONER_TRANSPORTATION_METHOD)){
				if (!checkArgSizeAndValue(++i, argList, content))
					return false;
				transportation_method = argList.get(i);
 
				continue;
			}else if(content.equalsIgnoreCase(Constants.DEBUG_MODE)){
					if (!checkArgSizeAndValue(++i, argList, content))
						return false;
					Co2EmissionerLogger.setDebugMode();
					continue;
			}
			else {
				commandLineParam.setArgumentParseStatus(Constants.ERROR_EXIT_STATUS);
 				return false;
			}
		}
		 
		commandLineParam.setArgumentParseStatus(Constants.DEFAULT_EXIT_STATUS);

		return true;
	}

	
}
