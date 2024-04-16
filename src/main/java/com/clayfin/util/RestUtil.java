package com.clayfin.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestUtil {
	
	
	public String getServiceName(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj;
		try {
			actualObj = mapper.readTree(jsonString);
			JsonNode jsonNode1 = actualObj.get("requestHeader").get("serviceName");
			String[] split = jsonNode1.textValue().split(":");
			if (split.length > 1) {
				return jsonNode1.textValue();
			}
			return jsonNode1.textValue() + ":general";
		} catch (IOException e) {
			throw new UnsupportedOperationException("Error");
		}
	}

	public String getTrackingId(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj;
		try {
			actualObj = mapper.readTree(jsonString);
			JsonNode jsonNode1 = actualObj.get("requestHeader").get("trackingId");
			return jsonNode1.textValue();
		} catch (IOException e) {
			throw new UnsupportedOperationException("Error");
		}
	}

}
