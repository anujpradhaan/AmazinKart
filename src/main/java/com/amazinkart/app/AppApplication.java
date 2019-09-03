package com.amazinkart.app;

import com.amazinkart.app.convert.JsonConverter;
import com.amazinkart.app.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class AppApplication implements ApplicationRunner {

	@Autowired
	private ProductService productService;

	@Autowired
	private JsonConverter jsonConverter;

	public static void main(String[] args) {

		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Command line arguments: {}", Arrays.toString(args.getSourceArgs()));

		String promotionType = args.getOptionNames().stream().findFirst()
				.orElseThrow(() -> new UnsupportedOperationException("Not allowed to run without Promotion Type"));
		log.info("{}", jsonConverter.toJson(productService.getProductsUsingPromotionType(promotionType)));
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
