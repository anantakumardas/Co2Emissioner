package com.ananta.co2emissioner.constants;

 
public class Constants {
	
	public static final String OPENROUTESERVICE_SEARCH_API_GET_URI = "https://api.openrouteservice.org/geocode/search";	
	public   final static String OPENROUTESERVICE_DISTANCE_API_POST_URI = "https://api.openrouteservice.org/v2/matrix/driving-car";

	public static final String OPENROUTESERVICE_REQUEST_HEADER_CONTENT_TYPE_KEY="Content-Type";
	public static final String OPENROUTESERVICE_REQUEST_HEADER_CONTENT_TYPE_VALUE="application/json; charset=utf-8";
	
	public static final String OPENROUTESERVICE_REQUEST_HEADER_ACCEPT_KEY="Accept";
	public static final String OPENROUTESERVICE_REQUEST_HEADER_ACCEPT_VALUE="application/json; charset=utf-8";

	public static final String OPENROUTESERVICE_SEARCH_API_GET_API_KEY="api_key";
	public static final String OPENROUTESERVICE_SEARCH_API_GET_QUERY_PARAM_TEXT_KEY="text";
	public static final String OPENROUTESERVICE_SEARCH_API_GET_QUERY_PARAM_LAYERS_KEY="layers";
	public static final String OPENROUTESERVICE_SEARCH_API_GET_QUERY_PARAM_LAYERS_VALUE="locality";
	
	public static final String OPENROUTESERVICE_DISTANCE_API_POST_BODY_LOCATION_KEY="\"locations\"";
	
	public static final String OPENROUTESERVICE_DISTANCE_API_POST_PARAM_METRICS_KEY="\"metrics\"";
	public static final String OPENROUTESERVICE_DISTANCE_API_POST_PARAM_METRICS_VALUE="\"distance\"";
	
	public static final String OPENROUTESERVICE_DISTANCE_API_POST_PARAM_UNITS_KEY="\"units\"";
	public static final String OPENROUTESERVICE_DISTANCE_API_POST_PARAM_UNITS_VALUE="\"km\"";
	
	
	/*The value for OPENROUTESERVICE_REQUEST_AUTHORIZATION_KEY is ORS_TOKEN*/
	public static final String OPENROUTESERVICE_DISTANCE_API_REQUEST_HEADER_AUTHORIZATION_KEY="Authorization"; 
	
	public static final String location="Berlin";
	public static final String layers ="locality";
	
	public static final String CO2_EMISSSIONER_START_CITY="--start";
	public static final String CO2_EMISSSIONER_END_CITY="--end";
	public static final String CO2_EMISSSIONER_TRANSPORTATION_METHOD="--transportation-method"; 
	
	  public static final int ERROR_EXIT_STATUS = -1 ;
	  public static final int DEFAULT_EXIT_STATUS = 0 ; 
	  
	  public static final String DEBUG_MODE = "--debug" ; 
	
}
