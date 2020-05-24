package com.ananta.co2emissioner.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Co2EmissionerLogger {
	
	private static final String  LOG_DEFAULT_PRIORITY = "FINE";
	
	private static boolean  DEBUG_MODE = false;
	

	public static void setDebugMode() {
		DEBUG_MODE = true;
	}
	public static void resetDebugMode() {
		DEBUG_MODE = false;
	}
	
	public static void log( Level level, String message , Logger logger) {
		if(DEBUG_MODE) {
			logger.log( level,message,logger); 
		}
	}
	
	public static Logger getLogger(String  name,String resourceBundleName){		
	
		Logger logger = Logger.getLogger(name, resourceBundleName);
		logger.setLevel(Level.parse(LOG_DEFAULT_PRIORITY));
		
		return logger;
	}
}
