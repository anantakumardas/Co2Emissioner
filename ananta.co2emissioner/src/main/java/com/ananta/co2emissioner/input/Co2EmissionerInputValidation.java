package com.ananta.co2emissioner.input;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ananta.co2emissioner.logger.Co2EmissionerLogger;
import com.ananta.co2emissioner.resource.Co2EmissionerResources;
import com.ananta.co2emissioner.utils.StringUtil;

public class Co2EmissionerInputValidation {

 
	private static Logger logger = Co2EmissionerLogger.getLogger(Co2EmissionerInputValidation.class.getName(),Co2EmissionerResources.class.getName());
 	
	public static boolean isValidInputs(CommandLineParam commandLineParam){
		
		Co2EmissionerLogger.log( Level.INFO,"Entering isValidInputs...",logger);  
		
		if(StringUtil.isEmptyString(commandLineParam.getStartCity()) || StringUtil.isEmptyString(commandLineParam.getEndCity()) || StringUtil.isEmptyString(commandLineParam.getTransportation_method())) {
			
 			Co2EmissionerLogger.log( Level.SEVERE, "One or more input paramaeter is wrong",logger);
			return false;
		}
		
		
		return true;
	}

 


}
