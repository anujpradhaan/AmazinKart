package com.amazinkart.app;

import com.amazinkart.app.convert.JsonConverter;
import com.amazinkart.app.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class AppApplication implements ApplicationRunner {

	private final ProductService productService;

	private final JsonConverter jsonConverter;

	@Autowired
	public AppApplication(ProductService productService, JsonConverter jsonConverter) {
		this.productService = productService;
		this.jsonConverter = jsonConverter;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Command line arguments: {}", Arrays.toString(args.getSourceArgs()));

		String promotionType = args.getOptionNames().stream().findFirst()
				.orElse("");
		log.info("Final Json after applying all the discounts {}", jsonConverter.toJson(productService.getProductsUsingPromotionType(promotionType)));
	}ss
}
