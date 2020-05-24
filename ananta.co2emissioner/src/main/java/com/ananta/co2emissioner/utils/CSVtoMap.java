package com.ananta.co2emissioner.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.MappingIterator;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import com.ananta.co2emissioner.connection.Connection;
import com.ananta.co2emissioner.logger.Co2EmissionerLogger;
import com.ananta.co2emissioner.resource.Co2EmissionerResources;


public class CSVtoMap {

	private static Logger logger = Co2EmissionerLogger.getLogger(CSVtoMap.class.getName(),Co2EmissionerResources.class.getName());

	public static Map<String, String> readCSVToMap(File file) throws JsonProcessingException, IOException {
		Map<String, String> response = new HashMap<String, String>();
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		com.fasterxml.jackson.databind.MappingIterator<Map<String, String>> iterator = mapper.reader(Map.class)
				.with(schema)
				.readValues(file);
		while (iterator.hasNext()) {

			response = iterator.next();

		}
		return response;
	}

	public static void main(String args[]) {
		try {
			String CO2_EMISSION_CSV_FILE_PATH = System. getenv("CO2_EMISSION_CSV_FILE_PATH");

			System.out.println("CO2_EMISSION_CSV_FILE_PATH  Value:- " +CO2_EMISSION_CSV_FILE_PATH );
			Map<String, String> response =  readCSVToMap(new File(CO2_EMISSION_CSV_FILE_PATH));

			System.out.println(response);
			System.out.println(response.get("small-diesel-car"));

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
