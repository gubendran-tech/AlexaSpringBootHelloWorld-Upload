package com.alexa.springboot;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonHelper {
	
	public String getJSONString(Object request) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		// mapper.setSerializationInclusion(Include.NON_NULL);
		return  mapper.writeValueAsString(request);
	}
	public Object parseJsonString(String request, Class<?> toClass) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return	mapper.readValue(request, toClass);		
	}
	
	public Object parseJsonObject(Object request, Class<?> toClass) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return	mapper.convertValue(request, toClass);		
	}
}
