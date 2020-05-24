package com.ananta.co2emissioner.utils;

 
public class StringUtil {

	public static boolean isEmptyString(String str) {
		if(str == null || str.trim().equalsIgnoreCase("")  )
			return true ;
		return false;
	}
	public static boolean isNotEmptyString(String str) {
		return !isEmptyString(str);
	}
	
	
}

