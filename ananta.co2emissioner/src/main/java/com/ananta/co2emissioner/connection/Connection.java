package com.ananta.co2emissioner.connection;
 
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
 
import com.ananta.co2emissioner.constants.Constants;
import com.ananta.co2emissioner.logger.Co2EmissionerLogger;
import com.ananta.co2emissioner.resource.Co2EmissionerResources;

public class Connection {

	static String location="Berlin";
	static String layers ="locality";
	private Client client = null;
	private String API_TOKEN = null;

	private static Logger logger = Co2EmissionerLogger.getLogger(Connection.class.getName(),Co2EmissionerResources.class.getName());

	
	public Connection(String API_TOKEN){
		client = ClientBuilder.newClient();
		this.API_TOKEN=API_TOKEN;
	}

	public Response  httpPost(String coordinates1 , String coordinates2 ) {

		String payload_str = "{"+ Constants.OPENROUTESERVICE_DISTANCE_API_POST_BODY_LOCATION_KEY +":[" + coordinates1 + "," + coordinates2 +"]," 
				+ Constants.OPENROUTESERVICE_DISTANCE_API_POST_PARAM_METRICS_KEY +":[" + Constants.OPENROUTESERVICE_DISTANCE_API_POST_PARAM_METRICS_VALUE +"],"+ Constants.OPENROUTESERVICE_DISTANCE_API_POST_PARAM_UNITS_KEY + ":"+ Constants.OPENROUTESERVICE_DISTANCE_API_POST_PARAM_UNITS_VALUE + "}";

		Response response = null;

		Entity<String> payload = Entity.json(payload_str);
		try {
		response = client.target( Constants.OPENROUTESERVICE_DISTANCE_API_POST_URI)
				.request()
				.header(Constants.OPENROUTESERVICE_DISTANCE_API_REQUEST_HEADER_AUTHORIZATION_KEY,API_TOKEN )
				.header(Constants.OPENROUTESERVICE_REQUEST_HEADER_ACCEPT_KEY,Constants.OPENROUTESERVICE_REQUEST_HEADER_ACCEPT_VALUE)
				.header(Constants.OPENROUTESERVICE_REQUEST_HEADER_CONTENT_TYPE_KEY,Constants.OPENROUTESERVICE_REQUEST_HEADER_CONTENT_TYPE_VALUE)
				.post(payload);
		
		}catch(Exception e) {
			Co2EmissionerLogger.log( Level.SEVERE, "httpPost status: " + response.getStatus(),logger);
			e.printStackTrace();
		}
		 
		Co2EmissionerLogger.log( Level.INFO, "httpPost status: " + response.getStatus(),logger);
 

		return response;
	}


	public Response httpGet(String location, String layers) {
		Response response = null;
		try {

			
			String uri = Constants.OPENROUTESERVICE_SEARCH_API_GET_URI+ "?"+ Constants.OPENROUTESERVICE_SEARCH_API_GET_API_KEY + "=" + API_TOKEN + "&"+ Constants.OPENROUTESERVICE_SEARCH_API_GET_QUERY_PARAM_TEXT_KEY+"=" +  location  +"&"+ Constants.OPENROUTESERVICE_SEARCH_API_GET_QUERY_PARAM_LAYERS_KEY + "=" + layers;

 			
			uri= uri.replaceAll("\\s", "+");
			
 					
			response = client.target(uri)
					.request(MediaType.TEXT_PLAIN_TYPE)
					.header(Constants.OPENROUTESERVICE_REQUEST_HEADER_ACCEPT_KEY,Constants.OPENROUTESERVICE_REQUEST_HEADER_ACCEPT_VALUE)
					.get();

 			if(response!=null)
 				 
			Co2EmissionerLogger.log( Level.INFO, "httpGet status: " + response.getStatus(),logger);

		}catch(Exception e) {
			if(response!=null)
			Co2EmissionerLogger.log( Level.SEVERE, "httpGet status: " + response.getStatus(),logger);

			e.printStackTrace();
		}
		return    response;
	}


	 
	 
	
}