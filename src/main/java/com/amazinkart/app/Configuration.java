package com.amazinkart.app;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-04}
 */
@org.springframework.context.annotation.Configuration
public class Configuration {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
