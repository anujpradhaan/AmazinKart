package com.amazinkart.app.convert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
@Component
@Slf4j
@Data
public class JsonConverter {

	private ObjectMapper objectMapper;

	@PostConstruct
	public void init() {
		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	}

	// DeSerialization
	public String toJson(Object data) throws IOException {
		return objectMapper.writeValueAsString(data);
	}

	public <T> T fromJson(String json, Class<T> type) throws IOException {
		return objectMapper.readValue(json, type);
	}

}
